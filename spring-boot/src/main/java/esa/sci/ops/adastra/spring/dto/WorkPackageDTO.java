package esa.sci.ops.adastra.spring.dto;

import javax.persistence.*;

@Entity
@Table(name = "work_package_dto")
public class WorkPackageDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "wp_id", nullable = false, unique = true)
    private String wpId;

    @SuppressWarnings("unused")
    public String getWpId() {
        return wpId;
    }

    @SuppressWarnings("unused")
    public void setWpId(String wpId) {
        this.wpId = wpId;
    }

    @Column(name = "title", nullable = false)
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "project_id")
    private String projectId;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Column(name = "schema_id")
    private String schemaId;

    public String getSchemaId() {
        return schemaId;
    }

    public void setSchemaId(String schemaId) {
        this.schemaId = schemaId;
    }

    public WorkPackageDTO id(Long id) {
        this.id = id;
        return this;
    }

    public WorkPackageDTO title(String title) {
        this.title = title;
        return this;
    }

    public WorkPackageDTO description(String description) {
        this.description = description;
        return this;
    }

    @SuppressWarnings("unused")
    public WorkPackageDTO wpId(String wpId) {
        this.wpId = wpId;
        return this;
    }

    public WorkPackageDTO projectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public WorkPackageDTO schemaId(String schemaId) {
        this.schemaId = schemaId;
        return this;
    }

    @Override
    public String toString() {
        return "WorkPackageDTO{" +
                "id=" + id +
                ", wpId='" + wpId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", projectId='" + projectId + '\'' +
                ", schemaId='" + schemaId + '\'' +
                '}';
    }

}