package com.deployhub.controller;

import com.deployhub.model.Project;
import com.deployhub.service.ProjectService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import com.deployhub.dto.ProjectRequest;

import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@ExtendWith(MockitoExtension.class)
class ProjectControllerTest {

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(projectController)
                .build();
    }

    @Test
    void testGetProjects() throws Exception {

        when(projectService.getProjects()).thenReturn(List.of());

        mockMvc.perform(get("/api/projects"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(projectService).getProjects();
    }


    @Test
    void testGetProjectById() throws Exception {

        Project project = new Project();
        project.setId(1L);
        project.setName("DeployHub");

        when(projectService.getProjectById(1L))
                .thenReturn(project);

        mockMvc.perform(get("/api/projects/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("DeployHub"));

        verify(projectService).getProjectById(1L);
    }


    @Test
    void testCreateProject() throws Exception {

        Project project = new Project();
        project.setId(1L);
        project.setName("DeployHub");

        when(projectService.createProject(any(ProjectRequest.class)))
                .thenReturn(project);

        mockMvc.perform(post("/api/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"DeployHub\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("DeployHub"));

        verify(projectService).createProject(any(ProjectRequest.class));
    }


    @Test
    void testUpdateProject() throws Exception {

        Project project = new Project();
        project.setId(1L);
        project.setName("Updated DeployHub");

        when(projectService.updateProject(eq(1L), any(Project.class)))
                .thenReturn(project);

        mockMvc.perform(put("/api/projects/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated DeployHub\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated DeployHub"));

        verify(projectService).updateProject(eq(1L), any(Project.class));
    }


    @Test
    void testDeleteProject() throws Exception {

        doNothing().when(projectService).delete(1L);

        mockMvc.perform(delete("/api/projects/1"))
                .andExpect(status().isOk());

        verify(projectService).delete(1L);
    }


    @Test
    void testGetProjectsPaged() throws Exception {

        Page<Project> page = new PageImpl<>(
                List.of(),
                PageRequest.of(0, 10, Sort.by("name").descending()),
                0
        );

        when(projectService.getProjects(0, 10, "name", "desc"))
                .thenReturn(page);

        mockMvc.perform(get("/api/projects/paged")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sortBy", "name")
                        .param("direction", "desc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(0));

        verify(projectService).getProjects(0, 10, "name", "desc");
    }


    @Test
    void testSearchProjects() throws Exception {

        Project project = new Project();
        project.setId(1L);
        project.setName("DeployHub");

        when(projectService.searchProjects("Deploy"))
                .thenReturn(List.of(project));

        mockMvc.perform(get("/api/projects/search")
                        .param("keyword", "Deploy"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("DeployHub"));

        verify(projectService).searchProjects("Deploy");
    }
}
