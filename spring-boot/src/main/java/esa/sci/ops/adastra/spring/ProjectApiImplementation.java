package esa.sci.ops.adastra.spring;

import esa.sci.ops.adastra.spring.generated.api.ProjectApiDelegate;
import esa.sci.ops.adastra.spring.generated.model.CreateProjectRequest;
import esa.sci.ops.adastra.spring.generated.model.Project;
import esa.sci.ops.adastra.spring.generated.model.WorkPackage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public class ProjectApiImplementation implements ProjectApiDelegate {

    private final ProjectRepository projectRepository;

    ProjectApiImplementation(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Mono<ResponseEntity<Flux<Project>>> g(Optional<Integer> id, Optional<String> projectId, Optional<String> title, Optional<String> description, ServerWebExchange exchange) {

        Flux<Project> records;
        if (id.isPresent()) {
            records = Flux.from(projectRepository.findById(id.get()));
        } else {
            records = projectRepository.findAll();
        }

        Flux<Project> filtered = records.filter(project -> projectFilter(project, id, projectId, title, description));

        return Mono.just(new ResponseEntity<>(filtered, HttpStatus.OK));

    }

    private boolean projectFilter(Project dbRecord, Optional<Integer> id, Optional<String> projectId, Optional<String> title, Optional<String> description) {
        boolean idMatch = id.isPresent() && id.get() == dbRecord.getId();
        boolean projectIdMatch = projectId.isPresent() && projectId.get() == dbRecord.getProjectId();
        boolean titleMatch = title.isPresent() && title.get() == dbRecord.getTitle();
        boolean descriptionMatch = description.isPresent() && description.get() == dbRecord.getDescription();

        return idMatch && projectIdMatch && titleMatch && descriptionMatch;
    }
}
