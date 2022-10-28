package esa.sci.ops.adastra.spring;

import esa.sci.ops.adastra.spring.generated.api.ProjectApiDelegate;
import esa.sci.ops.adastra.spring.generated.model.CreateProjectRequest;
import esa.sci.ops.adastra.spring.generated.model.Project;
import esa.sci.ops.adastra.spring.generated.model.WorkPackage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public class ProjectApiImplementation implements ProjectApiDelegate {

    private boolean projectFilter(Project dbRecord, Optional<Long> id, Optional<String> projectId, Optional<String> title, Optional<String> description) {
        boolean idMatch = id.isPresent() && id.get().equals(dbRecord.getId());
        boolean projectIdMatch = projectId.isPresent() && projectId.get().equals(dbRecord.getProjectId());
        boolean titleMatch = title.isPresent() && title.get().equals(dbRecord.getTitle());
        boolean descriptionMatch = description.isPresent() && description.get().equals(dbRecord.getDescription());

        return idMatch && projectIdMatch && titleMatch && descriptionMatch;
    }

    @Override
    public Mono<ResponseEntity<Flux<Project>>> createProject(Mono<CreateProjectRequest> createProjectRequest, ServerWebExchange exchange) {
        return ProjectApiDelegate.super.createProject(createProjectRequest, exchange);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteProject(Long id, ServerWebExchange exchange) {
        return ProjectApiDelegate.super.deleteProject(id, exchange);
    }

    @Override
    public Mono<ResponseEntity<Flux<Project>>> getProject(Optional<Long> id, Optional<String> projectId, Optional<String> title, Optional<String> description, ServerWebExchange exchange) {
        return ProjectApiDelegate.super.getProject(id, projectId, title, description, exchange);
    }

    @Override
    public Mono<ResponseEntity<Flux<Project>>> getProjectById(Long id, ServerWebExchange exchange) {
        return ProjectApiDelegate.super.getProjectById(id, exchange);
    }

    @Override
    public Mono<ResponseEntity<Flux<WorkPackage>>> getProjectWPs(Long id, Optional<Long> id2, Optional<String> title, Optional<String> wpId, Optional<String> schemaId, Optional<String> description, ServerWebExchange exchange) {
        return ProjectApiDelegate.super.getProjectWPs(id, id2, title, wpId, schemaId, description, exchange);
    }

    @Override
    public Mono<ResponseEntity<Flux<Project>>> updateProject(Long id, Optional<String> title, Optional<String> projectId, Optional<String> description, ServerWebExchange exchange) {
        return ProjectApiDelegate.super.updateProject(id, title, projectId, description, exchange);
    }
}
