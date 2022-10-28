package esa.sci.ops.adastra.spring;

import esa.sci.ops.adastra.spring.dto.DTOMapper;
import esa.sci.ops.adastra.spring.dto.ProjectDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
       ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(ProjectRepository repository) {
        return (args) -> {
            // save a few customers
            repository.save(new ProjectDTO().projectId("34").title("Euclid SOC").description("Euclid Science Operations"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (ProjectDTO customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Optional<ProjectDTO> project = repository.findById(1L);
            log.info("ProjectDTO found with findById(1L):");
            log.info("--------------------------------");
            log.info(project.get().toString());
            log.info("");

            log.info("Project from ProjectDTO found with findById(1L):");
            log.info("--------------------------------");
            log.info(DTOMapper.dtoToProject(project.get()).toString());
            log.info("");

        };
    }

}