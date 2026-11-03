package com.deployhub.service;

import com.deployhub.dto.ProjectMemberResponse;
import com.deployhub.model.*;
import com.deployhub.repository.ProjectMemberRepository;
import com.deployhub.repository.ProjectRepository;
import com.deployhub.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProjectMemberService {
    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository memberRepository;
    private final UserRepository userRepository;

    public ProjectMemberService(ProjectMemberRepository memberRepository,ProjectRepository projectRepository,
                 UserRepository userRepository){
        this.memberRepository=memberRepository;
        this.projectRepository=projectRepository;
        this.userRepository=userRepository;
    }

    private void verifyProjectOwner(Long projectId,User requester){
        ProjectMember membership=memberRepository.findByProjectId(projectId).stream()
                .filter(member->member.getUser().getId().equals(requester.getId()))
                .findFirst()
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.FORBIDDEN,"You are not a member of this project"
                ));
        if (membership.getRole()!=ProjectRole.OWNER){
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,"Only project owner can add members"
            );
        }
    }

    public ProjectMember addMember(Long projectId,Long userId,ProjectRole role,User requester){
        verifyProjectOwner(projectId,requester);
        if(role==ProjectRole.OWNER){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,"Cannot assign OWNER through member addition"
            );
        }
        Project project=projectRepository.findById(projectId)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Project not found"));
        User user=userRepository.findById(userId)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"User not found"));

        if(memberRepository.existsByProject_IdAndUser_Id(projectId,userId)){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,"User is already a project member");
        }

        ProjectMember member=new ProjectMember();
        member.setProject(project);
        member.setUser(user);
        member.setRole(role);
        return memberRepository.save(member);
    }

    public List<ProjectMemberResponse> getProjectMembers(Long projectId){

        return memberRepository.findByProjectId(projectId)
                .stream()
                .map(member->new ProjectMemberResponse(
                        member.getUser().getId(),
                        member.getUser().getName(),
                        member.getRole().name()
                ))
                .toList();
    }
}
