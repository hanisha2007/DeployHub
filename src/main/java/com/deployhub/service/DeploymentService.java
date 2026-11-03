package com.deployhub.service;

import com.deployhub.model.Deployment;
import com.deployhub.model.Project;
import com.deployhub.repository.ProjectRepository;
import com.deployhub.repository.DeploymentRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeploymentService {
   private final DeploymentRepository deploymentRepository;
   private final ProjectRepository projectRepository;

   public DeploymentService(ProjectRepository projectRepository,DeploymentRepository deploymentRepository){
       this.deploymentRepository=deploymentRepository;
       this.projectRepository=projectRepository;
   }

   public Deployment createDeployment(Long projectId){
       Project project=projectRepository.findById(projectId)
               .orElseThrow(() -> new RuntimeException("Project not found"));
       Deployment deployment=new Deployment();
       deployment.setProject(project);
       deployment.setStatus("PENDING");
       deployment.setCreatedAt(LocalDateTime.now());
       return deploymentRepository.save(deployment);
   }

   public List<Deployment> getDeploymentsByProjectId(Long projectId){
       return deploymentRepository.findByProject_Id(projectId);
   }
}
