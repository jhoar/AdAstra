package esa.sci.ops.adastra.spring;

import esa.sci.ops.adastra.spring.generated.api.WpApiDelegate;
import esa.sci.ops.adastra.spring.generated.model.CreateWorkPackageRequest;
import esa.sci.ops.adastra.spring.generated.model.WorkPackage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class WpApiImplementation implements WpApiDelegate {

    @Override
    public Mono<ResponseEntity<Flux<WorkPackage>>> createWorkPackage(Mono<CreateWorkPackageRequest> createWorkPackageRequest, ServerWebExchange exchange) {
        return WpApiDelegate.super.createWorkPackage(createWorkPackageRequest, exchange);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteWorkPackage(Integer id, ServerWebExchange exchange) {
        return WpApiDelegate.super.deleteWorkPackage(id, exchange);
    }

    @Override
    public Mono<ResponseEntity<Flux<WorkPackage>>> getWorkPackage(Optional<Integer> id, Optional<String> title, Optional<String> wpId, Optional<String> projectId, Optional<String> schemaId, Optional<String> description, ServerWebExchange exchange) {
        return WpApiDelegate.super.getWorkPackage(id, title, wpId, projectId, schemaId, description, exchange);
    }

    @Override
    public Mono<ResponseEntity<Flux<WorkPackage>>> getWorkPackageById(Integer id, ServerWebExchange exchange) {
        return WpApiDelegate.super.getWorkPackageById(id, exchange);
    }

    @Override
    public Mono<ResponseEntity<Flux<WorkPackage>>> updateWorkPackage(Integer id, Optional<String> title, Optional<String> wpId, Optional<String> projectId, Optional<String> schemaId, Optional<String> description, ServerWebExchange exchange) {
        return WpApiDelegate.super.updateWorkPackage(id, title, wpId, projectId, schemaId, description, exchange);
    }

}
