package com.deployhub.controller;

import com.deployhub.model.Deployment;
import com.deployhub.service.DeploymentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/projects")
@SecurityRequirement(name="bearerAuth")
public class DeploymentController {
  private final DeploymentService deploymentService;

  public DeploymentController(DeploymentService deploymentService){
      this.deploymentService=deploymentService;
  }

  @PostMapping("/{projectId}/deployments")
  public Deployment createDeployment(@PathVariable Long projectId){
      return deploymentService.createDeployment(projectId);
  }

  @GetMapping("/{projectId}/deployments")
  public List<Deployment> getDeploymentsByProjectId(@PathVariable Long projectId){
      return deploymentService.getDeploymentsByProjectId(projectId);
  }
}
