package esa.sci.ops.adastra.spring;

import esa.sci.ops.adastra.spring.dto.ProjectDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProjectApiImplementationTest {

    @Autowired
    private ProjectRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        repository.save(new ProjectDTO().projectId("34").title("Euclid SOC").description("Euclid Science Operations"));
    }

    @Test
    void createProject() {
    }

    @Test
    void deleteProject() {
    }

    @Test
    void getProject() {
        int count = 0;
        for (ProjectDTO customer : repository.findAll()) {
            count++;
        }

        assertEquals(1, count);
    }

    @Test
    void getProjectById() {
    }

    @Test
    void getProjectWPs() {
    }

    @Test
    void updateProject() {
    }
}