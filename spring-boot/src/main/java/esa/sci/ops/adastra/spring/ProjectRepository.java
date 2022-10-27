package esa.sci.ops.adastra.spring;

import esa.sci.ops.adastra.spring.generated.model.Project;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectRepository extends ReactiveCrudRepository<Project, Integer> { }
