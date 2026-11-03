package com.deployhub.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="deployments")
public class Deployment {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String status;
    private LocalDateTime createdAt=LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;
    public Deployment(){

    }

    public Deployment(Long id,String status,LocalDateTime createdAt,Project project){
        this.id=id;
        this.status=status;
        this.createdAt=createdAt;
        this.project=project;
    }

    public Long getId(){
        return id;
    }
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
    public String getStatus(){
        return status;
    }
    public Project getProject(){
        return project;
    }
    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt=createdAt;
    }
    public void setStatus(String status){
        this.status=status;
    }
    public void setId(Long id){
        this.id=id;
    }
    public void setProject(Project project){this.project=project;}
}
