package com.demo.init;

import com.demo.domain.Project;
import com.demo.persistence.ProjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class StartupData implements CommandLineRunner {
    private final ProjectRepository projectRepository;

    public StartupData(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        projectRepository.saveAll(List.of(new Project("Spring Core", "Spring Project", LocalDate.now(), BigDecimal.valueOf(1500)),
                                  new Project("Spring Boot", "Spring Boot Project", LocalDate.now(), BigDecimal.valueOf(1600)),
                                  new Project("Spring Batch", "Spring Batch Project", LocalDate.now(), BigDecimal.valueOf(1800))));
    }
}
