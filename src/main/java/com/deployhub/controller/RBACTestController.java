package com.deployhub.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/rbac")
@SecurityRequirement(name="bearerAuth")
public class RBACTestController {

    @GetMapping("/viewer")
    public String viewerAccess() {
        return "Success! You have VIEWER access.";
    }

    @GetMapping("/owner")
    public String ownerAccess() {
        return "Success! You have OWNER access.";
    }

    @GetMapping("/editor")
    public String editorAccess(){
        return "Success! You have EDITOR access.";
    }
}
