package com.deployhub.controller;

import com.deployhub.model.Project;
import com.deployhub.dto.ProjectRequest;
import com.deployhub.service.ProjectService;
import org.springframework.web.bind.annotation.*;//also has requestParam in this

import java.util.List;

import org.springframework.data.domain.Page;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name="bearerAuth")
public class ProjectController {
       private final ProjectService projectService;

       public ProjectController(ProjectService projectService){
           this.projectService=projectService;
       }

       @GetMapping("/api/projects")
       public List<Project> getProjects(){
           return projectService.getProjects();
       }

       @GetMapping("/api/projects/{id}")
       public Project getProjectById(@PathVariable Long id){
           return projectService.getProjectById(id);
       }

       @PostMapping("/api/projects")
       public Project createProject(@RequestBody ProjectRequest projectRequest){
           return projectService.createProject(projectRequest);
       }

       @PutMapping("/api/projects/{id}")
       public Project updateProject(@PathVariable Long id , @RequestBody Project project){
           return projectService.updateProject(id, project);
       }

       @DeleteMapping("/api/projects/{id}")
       public void delete(@PathVariable Long id){
           projectService.delete(id);
       }

       @GetMapping("/api/projects/paged")
       public Page<Project> getProjects(@RequestParam(defaultValue = "0")int page,
                                        @RequestParam(defaultValue = "10")int size,
                                        @RequestParam(defaultValue = "name")String sortBy,
                                        @RequestParam(defaultValue = "desc")String direction){
           return projectService.getProjects(page,size,sortBy,direction);
       }

       @GetMapping("/api/projects/search")
       public List<Project> searchProjects(@RequestParam String keyword){
           return projectService.searchProjects(keyword);
       }
}
