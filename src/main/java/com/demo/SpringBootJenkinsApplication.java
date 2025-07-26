package com.demo;

import com.demo.domain.Project;
import com.demo.persistence.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class SpringBootJenkinsApplication implements CommandLineRunner {

    @Autowired
    private ProjectRepository projectRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJenkinsApplication.class, args);
    }

    public void run(String ... args) {
       projectRepository.save(new Project("Java Project", "A new Java project", LocalDate.of(2002, 10,23), BigDecimal.valueOf(1500)));

    }

}
