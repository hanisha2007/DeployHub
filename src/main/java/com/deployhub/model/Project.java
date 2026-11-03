package com.deployhub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name="projects")
public class Project {
      @Id
      @GeneratedValue(strategy=GenerationType.IDENTITY)
      private Long id;
      private String name,status;

      public Project(){

      }

      public Project(Long id,String name,String status){
          this.id=id;
          this.name=name;
          this.status=status;
      }

      public Long getId(){
          return id;
      }
      public String getName(){
          return name;
      }
      public String getStatus(){
          return status;
      }

      public void setName(String name){
          this.name=name;
      }
      public void setStatus(String status){
          this.status=status;
      }
      public void setId(Long id){
          this.id=id;
      }
}
