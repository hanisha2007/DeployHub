package com.deployhub.dto;

public class ProjectMemberResponse {
    private Long userId;
    private String name,role;

    public ProjectMemberResponse(Long userId,String name,String role){
        this.userId=userId;
        this.name=name;
        this.role=role;
    }

    public Long getUserId(){
        return userId;
    }
    public String getName(){ return name;}
    public String getRole(){ return role;}
}
