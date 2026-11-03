package com.deployhub.dto;

import com.deployhub.model.ProjectRole;

public class AddProjectMemberRequest {
    private Long userId;
    private ProjectRole role;

    public Long getUserId(){ return userId;}
    public ProjectRole getRole(){ return role;}

    public void setUserId(Long userId){ this.userId=userId;}
    public void setRole(ProjectRole role){ this.role=role;}
}
