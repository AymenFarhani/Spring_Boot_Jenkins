package com.demo.web;

import com.demo.domain.Project;
import com.demo.persistence.ProjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Page<Project>>  getProjects(@RequestParam(name="page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "2") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Project> projects = projectRepository.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(projects);
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable(name = "id") Long id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found!"));
        return ResponseEntity.status(HttpStatus.OK).body(project);
    }

    @PostMapping("/project")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectRepository.save(project));
    }

    @PatchMapping("/project/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable(name="id") Long id, @RequestBody Project project) {
        Project projectInDb = projectRepository.findById(id).orElseThrow(()-> new RuntimeException("Project not found!"));
        projectInDb.setName(project.getName());
        projectInDb.setDescription(project.getDescription());
        projectInDb.setBudget(project.getBudget());
        projectInDb.setStartedDate(project.getStartedDate());
        return ResponseEntity.status(HttpStatus.OK).body(projectRepository.save(projectInDb));
    }

    @DeleteMapping("/project/{id}")
    public ResponseEntity<Void> deleteProjectById(@PathVariable(name = "id") Long id) {
        projectRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
