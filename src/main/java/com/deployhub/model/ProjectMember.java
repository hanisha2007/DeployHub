package com.deployhub.model;

import jakarta.persistence.*;

@Entity
@Table(name="project_members")
public class ProjectMember {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long id;

   @ManyToOne
   @JoinColumn(name="project_id",nullable = false)
   private Project project;

   @ManyToOne
   @JoinColumn(name="user_id",nullable = false)
   private User user;

   @Enumerated(EnumType.STRING)
   @Column(name="project_role",nullable=false)
   private ProjectRole role;

    public ProjectMember() {
    }

    public Long getId() {
        return id;
    }

    public Project getProject() {
        return project;
    }

    public User getUser() {
        return user;
    }

    public ProjectRole getRole() {
        return role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRole(ProjectRole role) {
        this.role = role;
    }
}
