package com.deployhub.repository;

import com.deployhub.model.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember,Long> {
    List<ProjectMember> findByProjectId(Long projectId);

    boolean existsByProject_IdAndUser_Id(Long projectId,Long userId);
}
