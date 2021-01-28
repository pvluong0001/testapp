package luong.lit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import luong.lit.service.Slug;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="projects")
@Data
@SQLDelete(sql = "UPDATE projects SET deleted_at=CURRENT_TIMESTAMP() WHERE id=?")
@Where(clause = "deleted_at is null")
@DynamicUpdate
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String name;

    @Column()
    private String slug;

    @Column()
    private String description;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;

    @PrePersist
    public void prePersist() {
        slug = Slug.makeSlug(name);
    }

    @PreUpdate
    public void preUpdate() {
        slug = Slug.makeSlug(name);
    }

    @ManyToMany
    @JoinTable(
            name = "project_scenario",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "scenario_id")
    )
    private List<Scenario> scenarios;

    @OneToMany(mappedBy = "project")
    @JsonIgnore
    private Set<ProjectScenario> projectScenarios;
}
