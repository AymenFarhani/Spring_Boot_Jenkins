package com.demo.web;

import com.demo.domain.Project;
import com.demo.persistence.ProjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class ProjectController {

    private final ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping("/projects")
    public ResponseEntity<List<Project>>  getProjects() {
        return ResponseEntity.status(HttpStatus.OK).body(projectRepository.findAll());
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable(name = "id") Long id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found exception!"));
        return ResponseEntity.status(HttpStatus.OK).body(project);
    }

    @PostMapping("/project")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectRepository.save(project));
    }

    @DeleteMapping("/project/{id}")
    public ResponseEntity deleteProjectById(@PathVariable(name = "id") Long id) {
        projectRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
