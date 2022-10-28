package esa.sci.ops.adastra.spring.dto;

import esa.sci.ops.adastra.spring.generated.model.Project;
import esa.sci.ops.adastra.spring.generated.model.WorkPackage;
import esa.sci.ops.adastra.spring.generated.model.WorkSchema;
import org.modelmapper.ModelMapper;

public class DTOMapper {

    private static ModelMapper modelMapper = new ModelMapper();

    public static ProjectDTO projectToDTO(Project project) {
        return modelMapper.map(project, ProjectDTO.class);
    }

    public static Project dtoToProject(ProjectDTO projectDTO) {
        return modelMapper.map(projectDTO, Project.class);
    }

    public static WorkPackageDTO workPackageToDTO(WorkPackage workPackage) {
        return modelMapper.map(workPackage, WorkPackageDTO.class);
    }

    public static WorkPackage dtoToWorkPackage(WorkPackageDTO workPackageDTO) {
        return modelMapper.map(workPackageDTO, WorkPackage.class);
    }

    public static WorkSchemaDTO workSchemaToDTO(WorkSchema workSchema) {
        return modelMapper.map(workSchema, WorkSchemaDTO.class);
    }

    public static WorkSchema dtoToWorkSchema(WorkSchemaDTO workSchemaDTO) {
        return modelMapper.map(workSchemaDTO, WorkSchema.class);
    }

}
