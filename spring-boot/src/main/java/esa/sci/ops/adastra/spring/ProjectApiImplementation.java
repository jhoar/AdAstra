package esa.sci.ops.adastra.spring;

import esa.sci.ops.adastra.spring.dto.DTOMapper;
import esa.sci.ops.adastra.spring.dto.ProjectDTO;
import esa.sci.ops.adastra.spring.generated.api.ProjectApiDelegate;
import esa.sci.ops.adastra.spring.generated.model.CreateProjectRequest;
import esa.sci.ops.adastra.spring.generated.model.Project;
import esa.sci.ops.adastra.spring.generated.model.WorkPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class ProjectApiImplementation implements ProjectApiDelegate {

    private static final Logger log = LoggerFactory.getLogger(ProjectApiImplementation.class);

    @Autowired
    private ProjectRepository repository;

    @Override
    public ResponseEntity<List<Project>> getProject(Long id, String projectId, String title, String description) {

        log.info("getProject: id = " + id + ", projectId = " + projectId + ", title = " + title + ", description = " + description);

        try {
            List<Project> collect = StreamSupport.stream(repository.findAll().spliterator(), false)
                    .filter(rec -> projectFilter(rec, id, projectId, title, description))
                    .map(DTOMapper::dtoToProject)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(collect, HttpStatus.OK);
        } catch (DataAccessException e) {
            log.error("getProject: Error on search", e);
            return new ResponseEntity<>(List.of(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<List<Project>> getProjectById(Long id) {
        log.info("getProjectById: id = " + id);
        return getProject(id, null, null, null);
    }

    @Override
    public ResponseEntity<List<WorkPackage>> getProjectWPs(Long id, Long id2, String title, String wpId, String schemaId, String description) {
// FIXME: 04/11/2022
        return ProjectApiDelegate.super.getProjectWPs(id, id2, title, wpId, schemaId, description);
    }

    @Override
    public ResponseEntity<List<Project>> updateProject(Long id, String title, String projectId, String description) {

        log.info("updateProject: id = " + id + ", projectId = " + projectId + ", title = " + title + ", description = " + description);

        Optional<ProjectDTO> OptProjectDTO = repository.findById(id);
        if (OptProjectDTO.isPresent()) {
            ProjectDTO projectDTO = OptProjectDTO.get();
            log.info("updateProject: Record value " + projectDTO);
            if (title != null) {
                projectDTO.title(title);
            }
            if (projectId != null) {
                projectDTO.projectId(projectId);
            }
            if (description != null) {
                projectDTO.description(description);
            }
            log.info("updateProject: Updating record " + projectDTO);

            try {
                ProjectDTO save = repository.save(projectDTO);
                return new ResponseEntity<>(List.of(DTOMapper.dtoToProject(save)), HttpStatus.OK);
            } catch (DataAccessException e) {
                log.error("updateProject: Error on update", e);
                return new ResponseEntity<>(List.of(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(List.of(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Project>> createProject(CreateProjectRequest createProjectRequest) {

        if (createProjectRequest.getProjectId() == null || createProjectRequest.getTitle() == null) {
            log.error("createProject: Mandatory info missing: " +
                    "projectId: " + createProjectRequest.getProjectId() +
                    ", title: " + createProjectRequest.getTitle());
            return new ResponseEntity<>(List.of(), HttpStatus.BAD_REQUEST);
        }

        ProjectDTO dto = new ProjectDTO()
                .projectId(createProjectRequest.getProjectId())
                .title(createProjectRequest.getTitle())
                .description(createProjectRequest.getDescription());

        log.info("createProject: Creating record " + dto);

        try {
            ProjectDTO save = repository.save(dto);
            return new ResponseEntity<>(List.of(DTOMapper.dtoToProject(save)), HttpStatus.OK);
        } catch (DataAccessException e) {
            log.error("createProject: Error on create", e);
            return new ResponseEntity<>(List.of(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<Void> deleteProject(Long id) {
        log.info("deleteProject: Deleting record " + id);
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException e) {
            log.error("deleteProject: Error on delete", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean projectFilter(ProjectDTO dbRecord, Long id, String projectId, String title, String description) {
        boolean idMatch = id == null || id.equals(dbRecord.getId());
        boolean projectIdMatch = projectId == null || projectId.equals(dbRecord.getProjectId());
        boolean titleMatch = title == null || title.equals(dbRecord.getTitle());
        boolean descriptionMatch = description == null || description.equals(dbRecord.getDescription());

        return idMatch && projectIdMatch && titleMatch && descriptionMatch;
    }

}
