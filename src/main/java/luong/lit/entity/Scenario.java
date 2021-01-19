package luong.lit.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "scenario")
@Data
public class Scenario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String name;

    @Column()
    private String description;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tag_scenario",
            joinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "scenario_id", referencedColumnName = "id")
    )
    private List<Tag> tags = new ArrayList<>();
}
