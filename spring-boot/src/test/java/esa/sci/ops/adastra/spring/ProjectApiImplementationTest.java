package esa.sci.ops.adastra.spring;

import esa.sci.ops.adastra.spring.dto.ProjectDTO;
import esa.sci.ops.adastra.spring.generated.model.Project;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProjectApiImplementationTest {

    @Autowired
    private ProjectApiImplementation controller;

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ProjectRepository repository;

    @AfterEach
    public void clean() {
        repository.deleteAll();
    }

    ParameterizedTypeReference<List<Project>> typeRef = new ParameterizedTypeReference<>() {};

    private List<Project> getProjectsFromResponse(WebTestClient.ResponseSpec exchange) {
        WebTestClient.BodySpec<List<Project>, ?> listBodySpec = exchange.expectBody(typeRef);
        EntityExchangeResult<List<Project>> listEntityExchangeResult = listBodySpec.returnResult();
        return listEntityExchangeResult.getResponseBody();
    }

    @Test
    void createProject_valid() {

        Project newProject = new Project().projectId("36").title("Test");

        WebTestClient.ResponseSpec exchange = webTestClient.post()
                .uri("/project")
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(newProject), Project.class)
                .exchange();

        exchange.expectStatus().is2xxSuccessful();

        List<Project> projects = getProjectsFromResponse(exchange);
        assertEquals(1, projects.size());
        assertEquals("36", projects.get(0).getProjectId());

        boolean found = false;
        for (ProjectDTO dto: repository.findAll()) {
            if (dto.getProjectId().equals("36") && dto.getTitle().equals("Test")) {
                found = true;
                break;
            }
        }

        assertTrue(found);

    }

    @Test
    void createProject_clash() {
        ProjectDTO test_again = repository.save(new ProjectDTO().projectId("41").title("Test again"));
        Optional<ProjectDTO> check = repository.findById(test_again.getId());
        assertTrue(check.isPresent());

        Project newProject = new Project().projectId("41").title("Test");

        WebTestClient.ResponseSpec exchange = webTestClient.post()
                .uri("/project")
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(newProject), Project.class)
                .exchange();

        exchange.expectStatus().is5xxServerError();

        List<Project> projects = getProjectsFromResponse(exchange);
        assertEquals(0, projects.size());

    }

    @Test
    void createProject_missingId() {

        Project newProject = new Project().title("Test");

        WebTestClient.ResponseSpec exchange = webTestClient.post()
                .uri("/project")
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(newProject), Project.class)
                .exchange();

        exchange.expectStatus().is4xxClientError();

    }


    @Test
    void deleteProject() {

        ProjectDTO test_again = repository.save(new ProjectDTO().projectId("37").title("Test again"));
        Optional<ProjectDTO> check = repository.findById(test_again.getId());
        assertTrue(check.isPresent());

        webTestClient.delete()
                .uri("/project/" + test_again.getId() )
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .exchange();

        Optional<ProjectDTO> byId = repository.findById(test_again.getId());
        assertTrue(byId.isEmpty());

    }

    @Test
    void getProject_noFilter() {

        ProjectDTO test_again = repository.save(new ProjectDTO().projectId("41").title("Test again"));
        Optional<ProjectDTO> check = repository.findById(test_again.getId());
        assertTrue(check.isPresent());

        ProjectDTO test_again1 = repository.save(new ProjectDTO().projectId("42").title("Test again"));
        Optional<ProjectDTO> check1 = repository.findById(test_again1.getId());
        assertTrue(check1.isPresent());

        WebTestClient.ResponseSpec exchange = webTestClient.get()
                .uri("/project/")
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .exchange();

        exchange.expectStatus().is2xxSuccessful();

        assertEquals(2, getProjectsFromResponse(exchange).size());
    }

    @Test
    void getProject_noResults() {

        ProjectDTO test_again = repository.save(new ProjectDTO().projectId("39").title("Test again"));
        Optional<ProjectDTO> check = repository.findById(test_again.getId());
        assertTrue(check.isPresent());

        ProjectDTO test_again1 = repository.save(new ProjectDTO().projectId("40").title("Test again"));
        Optional<ProjectDTO> check1 = repository.findById(test_again1.getId());
        assertTrue(check1.isPresent());

        WebTestClient.ResponseSpec exchange = webTestClient.get()
                .uri("/project/?id=500")
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .exchange();

        exchange.expectStatus().is2xxSuccessful();

        assertEquals(0, getProjectsFromResponse(exchange).size());
    }

    @Test
    void getProject_byId() {

        ProjectDTO test_again = repository.save(new ProjectDTO().projectId("38").title("Test again"));
        Optional<ProjectDTO> check = repository.findById(test_again.getId());
        assertTrue(check.isPresent());

        WebTestClient.ResponseSpec exchange = webTestClient.get()
                .uri("/project/?id=" + test_again.getId() )
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .exchange();

        exchange.expectStatus().is2xxSuccessful();

        assertEquals(1, getProjectsFromResponse(exchange).size());
    }


    @Test
    void getProjectById() {
        ProjectDTO test_again = repository.save(new ProjectDTO().projectId("38").title("Test again"));
        Optional<ProjectDTO> check = repository.findById(test_again.getId());
        assertTrue(check.isPresent());

        WebTestClient.ResponseSpec exchange = webTestClient.get()
                .uri("/project/" + test_again.getId() )
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .exchange();

        exchange.expectStatus().is2xxSuccessful();

        assertEquals(1, getProjectsFromResponse(exchange).size());
    }

    @Test
    void getProjectById_fail() {
        ProjectDTO test_again = repository.save(new ProjectDTO().projectId("38").title("Test again"));
        Optional<ProjectDTO> check = repository.findById(test_again.getId());
        assertTrue(check.isPresent());

        WebTestClient.ResponseSpec exchange = webTestClient.get()
                .uri("/project/500" + test_again.getId() )
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .exchange();

        exchange.expectStatus().is2xxSuccessful();

        assertEquals(0, getProjectsFromResponse(exchange).size());

    }


    @Test
    void getProjectWPs() {
    }

    @Test
    void updateProject() {
        ProjectDTO test_again = repository.save(new ProjectDTO().projectId("38").title("Test again"));
        Optional<ProjectDTO> check = repository.findById(test_again.getId());
        assertTrue(check.isPresent());

        WebTestClient.ResponseSpec exchange = webTestClient.patch()
                .uri("/project/" + test_again.getId() + "/?projectId=42" )
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .exchange();

        exchange.expectStatus().is2xxSuccessful();

        List<Project> projects = getProjectsFromResponse(exchange);
        assertEquals(1, projects.size());
        assertEquals("42", projects.get(0).getProjectId());

    }

    @Test
    void updateProject_clash() {
        ProjectDTO test_again = repository.save(new ProjectDTO().projectId("41").title("Test again"));
        Optional<ProjectDTO> check = repository.findById(test_again.getId());
        assertTrue(check.isPresent());

        ProjectDTO test_again1 = repository.save(new ProjectDTO().projectId("42").title("Test again"));
        Optional<ProjectDTO> check1 = repository.findById(test_again1.getId());
        assertTrue(check1.isPresent());

        WebTestClient.ResponseSpec exchange = webTestClient.patch()
                .uri("/project/" + test_again.getId() + "/?projectId=42" )
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .exchange();

        exchange.expectStatus().is5xxServerError();

        List<Project> projects = getProjectsFromResponse(exchange);
        assertEquals(0, projects.size());

    }

}