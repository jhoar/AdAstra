package esa.sci.ops.adastra.spring.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "work_schema_dto")
public class WorkSchemaDTO {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "schema_id", nullable = false, unique = true)
    private String schemaId;

    public String getSchemaId() {
        return schemaId;
    }

    public void setSchemaId(String schemaId) {
        this.schemaId = schemaId;
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

    public WorkSchemaDTO id(Long id) {
        this.id = id;
        return this;
    }

    public WorkSchemaDTO title(String title) {
        this.title = title;
        return this;
    }

    public WorkSchemaDTO description(String description) {
        this.description = description;
        return this;
    }

    public WorkSchemaDTO schemaId(String schemaId) {
        this.schemaId = schemaId;
        return this;
    }

    @Override
    public String toString() {
        return "WorkSchemaDTO{" +
                "id=" + id +
                ", schemaId='" + schemaId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}