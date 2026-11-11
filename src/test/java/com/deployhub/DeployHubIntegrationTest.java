package com.deployhub;

import com.deployhub.model.Project;
import com.deployhub.repository.ProjectRepository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class DeployHubIntegrationTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testSaveAndRetrieveProject() {

        // Create a project
        Project project = new Project();
        project.setName("DeployHub");
        project.setStatus("Active");

        // Save project into H2 database
        Project savedProject = projectRepository.save(project);

        // Retrieve project using generated ID
        Project result = projectRepository.findById(savedProject.getId())
                .orElse(null);

        // Verify results
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("DeployHub", result.getName());
        assertEquals("Active", result.getStatus());
    }
}
