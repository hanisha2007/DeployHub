package com.deployhub.service;

import com.deployhub.model.Project;
import com.deployhub.repository.ProjectRepository;
import com.deployhub.repository.ProjectMemberRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ProjectMemberRepository memberRepository;

    @InjectMocks
    private ProjectService projectService;

    @Test
    void testGetProjects() {

        List<Project> projects = List.of(
                mock(Project.class),
                mock(Project.class)
        );

        when(projectRepository.findAll()).thenReturn(projects);

        List<Project> result = projectService.getProjects();

        assertEquals(2, result.size());

        verify(projectRepository).findAll();
    }

    @Test
    void testGetProjectByIdNotFound() {

        when(projectRepository.findById(100L))
                .thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            projectService.getProjectById(100L);
        });

        verify(projectRepository).findById(100L);
    }

    @Test
    void testGetProjectById() {

        Project project = mock(Project.class);

        when(projectRepository.findById(1L))
                .thenReturn(Optional.of(project));

        Project result = projectService.getProjectById(1L);

        assertNotNull(result);
        assertEquals(project, result);

        verify(projectRepository).findById(1L);
    }
}
