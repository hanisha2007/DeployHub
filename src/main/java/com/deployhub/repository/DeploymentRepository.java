package com.deployhub.repository;

import com.deployhub.model.Deployment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DeploymentRepository extends JpaRepository<Deployment,Long>{
     List<Deployment>findByProject_Id(Long projectId);
}
