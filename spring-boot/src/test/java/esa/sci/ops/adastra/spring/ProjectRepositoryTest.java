package esa.sci.ops.adastra.spring;

import esa.sci.ops.adastra.spring.dto.ProjectDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository repository;

    @Test
    void createProject() {
        ProjectDTO p1 = repository.save(new ProjectDTO().projectId("35").title("Euclid SOC").description("Euclid Science Operations"));
        assertNotNull(p1);
        Long id = p1.getId();

        Optional<ProjectDTO> p2 = repository.findById(id);
        assertTrue(p2.isPresent());
        assertEquals("35", p2.get().getProjectId());
    }

    @Test
    void deleteProject() {
        ProjectDTO p1 = repository.save(new ProjectDTO().projectId("35").title("Euclid SOC").description("Euclid Science Operations"));

        assertNotNull(p1);
        Long id = p1.getId();

        Optional<ProjectDTO> p2 = repository.findById(id);
        assertTrue(p2.isPresent());
        assertEquals(id, p2.get().getId());

        repository.delete(p1);

        Optional<ProjectDTO> p3 = repository.findById(id);
        assertTrue(p3.isEmpty());

    }

    @Test
    void getProjectFindAll() {
        repository.save(new ProjectDTO().projectId("35").title("Euclid SOC").description("Euclid Science Operations"));
        repository.save(new ProjectDTO().projectId("36").title("Euclid SOC").description("Euclid Science Operations"));

        int count = 0;
        for (ProjectDTO ignored : repository.findAll()) {
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
        assertTrue(byId.isPresent());

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
        p1.setTitle("Updated");

        ProjectDTO p2 = repository.save(p1);

        assertNotNull(p2);
        assertEquals("Updated", p2.getTitle());

        Optional<ProjectDTO> byId = repository.findById(p2.getId());
        assertTrue(byId.isPresent());

        ProjectDTO projectDTO = byId.get();
        assertNotNull(projectDTO);
        assertEquals(p1.getId(), projectDTO.getId());
        assertEquals("Updated", projectDTO.getTitle());

    }
}