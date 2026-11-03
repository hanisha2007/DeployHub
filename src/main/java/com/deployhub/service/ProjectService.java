package com.deployhub.service;

import com.deployhub.model.Project;
import com.deployhub.repository.ProjectRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import com.deployhub.dto.ProjectRequest;
import java.util.List;

import com.deployhub.model.User;
import com.deployhub.model.ProjectMember;
import com.deployhub.model.ProjectRole;
import com.deployhub.repository.ProjectMemberRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository memberRepository;

    public ProjectService(ProjectRepository projectRepository,ProjectMemberRepository memberRepository) {
        this.projectRepository = projectRepository;
        this.memberRepository=memberRepository;
    }

    private static final Logger logger=LoggerFactory.getLogger(ProjectService.class);

    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id){
        logger.debug("Fetching project with ID: {}",id);
        return projectRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Project not found with ID: {}", id);

                    return new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Project not found"
                    );
                });
    }

    @Transactional
    public Project createProject(ProjectRequest projectRequest){
        logger.info("Creating a new project");
        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            Project project = new Project();
            project.setName(projectRequest.getName());
            project.setStatus(projectRequest.getStatus());
            Project savedProject = projectRepository.save(project);

            ProjectMember member = new ProjectMember();
            member.setProject(savedProject);
            member.setUser(user);
            member.setRole(ProjectRole.OWNER);
            memberRepository.save(member);
            logger.info("Project created successfully with ID: {}", savedProject.getId());
            return savedProject;
        }catch (RuntimeException e){
            logger.error("Failed to create project",e);
            throw e;
        }
    }

    public Project updateProject(Long id , Project project){
        Project existingProject = getProjectById(id);
        existingProject.setStatus(project.getStatus());
        existingProject.setName(project.getName());
        return projectRepository.save(existingProject);
    }

    public void delete(Long id){
        Project existingProject = getProjectById(id);
        projectRepository.delete(existingProject);
    }

    public Page<Project> getProjects(int page,int size,String sortBy,String direction){
        Sort sort =direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable= PageRequest.of(page,size,sort);
        return projectRepository.findAll(pageable);
    }

    public List<Project> searchProjects(String keyword){
        return projectRepository.findByNameContainingIgnoreCase(keyword);
    }
}
