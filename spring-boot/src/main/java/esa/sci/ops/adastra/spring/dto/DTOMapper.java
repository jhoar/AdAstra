package esa.sci.ops.adastra.spring.dto;

import esa.sci.ops.adastra.spring.generated.model.Project;
import esa.sci.ops.adastra.spring.generated.model.WorkPackage;
import esa.sci.ops.adastra.spring.generated.model.WorkSchema;
import org.modelmapper.ModelMapper;

public class DTOMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    @SuppressWarnings("unused")
    public static ProjectDTO projectToDTO(Project project) {
        return modelMapper.map(project, ProjectDTO.class);
    }

    public static Project dtoToProject(ProjectDTO projectDTO) {
        return modelMapper.map(projectDTO, Project.class);
    }

    @SuppressWarnings("unused")
    public static WorkPackageDTO workPackageToDTO(WorkPackage workPackage) {
        return modelMapper.map(workPackage, WorkPackageDTO.class);
    }

    @SuppressWarnings("unused")
    public static WorkPackage dtoToWorkPackage(WorkPackageDTO workPackageDTO) {
        return modelMapper.map(workPackageDTO, WorkPackage.class);
    }

    @SuppressWarnings("unused")
    public static WorkSchemaDTO workSchemaToDTO(WorkSchema workSchema) {
        return modelMapper.map(workSchema, WorkSchemaDTO.class);
    }

    @SuppressWarnings("unused")
    public static WorkSchema dtoToWorkSchema(WorkSchemaDTO workSchemaDTO) {
        return modelMapper.map(workSchemaDTO, WorkSchema.class);
    }

}
