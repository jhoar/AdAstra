package esa.sci.ops.adastra.spring.dto;

import javax.persistence.*;

@Entity
@Table(name = "project")
public class ProjectDTO {
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

    @Column(name = "project_id", nullable = false, unique = true)
    private String projectId;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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

    // Fluent setter classes
    public ProjectDTO id(Long id) { this.id = id; return this; }

    public ProjectDTO projectId(String projectId) { this.projectId = projectId; return this; }

    public ProjectDTO title(String title) { this.title = title; return this; }

    public ProjectDTO description(String description) { this.description = description; return this; }

    @Override
    public String toString() {
        return "ProjectDTO{" +
                "id=" + id +
                ", projectId='" + projectId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @SuppressWarnings("unused")
    public String toJSON() {
        return "{ " +
                "\"id\" : " + id + "," +
                "\"projectId\" : \"" + projectId + "\" ," +
                "\"title\" : \"" +  title + "\" ," +
                "\"description\" : \"" + description + "\"}";
    }


}