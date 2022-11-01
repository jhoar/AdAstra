package esa.sci.ops.adastra.spring;

import esa.sci.ops.adastra.spring.dto.ProjectDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProjectApiImplementationTest {

    @Autowired
    private ProjectRepository repository;

    @Test
    void createProject() {
        ProjectDTO p1 = repository.save(new ProjectDTO().projectId("35").title("Euclid SOC").description("Euclid Science Operations"));
        assertNotNull(p1);
        Long id = p1.getId();

        Optional<ProjectDTO> p2 = repository.findById(id);
        assertEquals(true, p2.isPresent());
        assertEquals("35", p2.get().getProjectId());
    }

    @Test
    void deleteProject() {
        ProjectDTO p1 = repository.save(new ProjectDTO().projectId("35").title("Euclid SOC").description("Euclid Science Operations"));

        assertNotNull(p1);
        Long id = p1.getId();

        Optional<ProjectDTO> p2 = repository.findById(id);
        assertEquals(true, p2.isPresent());
        assertEquals(id, p2.get().getId());

        repository.delete(p1);

        Optional<ProjectDTO> p3 = repository.findById(id);
        assertTrue(p3.isEmpty());

    }

    @Test
    void getProjectFindAll() {
        ProjectDTO p1 = repository.save(new ProjectDTO().projectId("35").title("Euclid SOC").description("Euclid Science Operations"));
        ProjectDTO p2 = repository.save(new ProjectDTO().projectId("36").title("Euclid SOC").description("Euclid Science Operations"));

        int count = 0;
        for (ProjectDTO customer : repository.findAll()) {
            count++;
        }
        assertEquals(2, count);
    }

    @Test
    void getProjectById() {
        ProjectDTO p1 = repository.save(new ProjectDTO().projectId("35").title("Euclid SOC").description("Euclid Science Operations"));

        assertNotNull(p1);
        Long id = p1.getId();

        Optional<ProjectDTO> byId = repository.findById(id);
        assertEquals(true, byId.isPresent());

        ProjectDTO projectDTO = byId.get();
        assertNotNull(projectDTO);
        assertEquals(id, projectDTO.getId());
    }

    @Test
    void getProjectWPs() {
    }

    @Test
    void updateProject() {

        ProjectDTO p1 = repository.save(new ProjectDTO().projectId("35").title("Euclid SOC").description("Euclid Science Operations"));

        assertNotNull(p1);
        Long id = p1.getId();

        p1.setTitle("Updated");

        ProjectDTO p2 = repository.save(p1);

        assertNotNull(p2);
        assertEquals("Updated", p2.getTitle());

        Optional<ProjectDTO> byId = repository.findById(p2.getId());
        assertEquals(true, byId.isPresent());

        ProjectDTO projectDTO = byId.get();
        assertNotNull(projectDTO);
        assertEquals(p1.getId(), projectDTO.getId());
        assertEquals("Updated", projectDTO.getTitle());

    }
}