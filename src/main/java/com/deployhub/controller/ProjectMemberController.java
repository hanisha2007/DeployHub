package com.deployhub.controller;

import com.deployhub.dto.AddProjectMemberRequest;
import com.deployhub.dto.ProjectMemberResponse;
import com.deployhub.model.User;
import com.deployhub.service.ProjectMemberService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@RestController
@RequestMapping("/api/projects/{projectId}/members")
@SecurityRequirement(name="bearerAuth")
public class ProjectMemberController {

    private final ProjectMemberService memberService;

    public ProjectMemberController(
            ProjectMemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ProjectMemberResponse addMember(
            @PathVariable Long projectId,
            @RequestBody AddProjectMemberRequest request) {

        User requester = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        var member = memberService.addMember(
                projectId,
                request.getUserId(),
                request.getRole(),
                requester
        );

        return new ProjectMemberResponse(
                member.getUser().getId(),
                member.getUser().getName(),
                member.getRole().name()
        );
    }

    @GetMapping
    public List<ProjectMemberResponse> getMembers(
            @PathVariable Long projectId) {
        return memberService.getProjectMembers(projectId);
    }
}
