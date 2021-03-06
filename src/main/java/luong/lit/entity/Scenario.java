package luong.lit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "scenarios")
@Data
@SQLDelete(sql = "UPDATE scenarios SET deleted_at=CURRENT_TIMESTAMP() WHERE id=?")
@Where(clause = "deleted_at is null")
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

    @Column(name = "deleted_at")
    private Date deletedAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tag_scenario",
            joinColumns = @JoinColumn(name = "scenario_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id")
    )
    @JsonIgnoreProperties("scenarios")
    private List<Tag> tags = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "scenario_testcase",
            joinColumns = @JoinColumn(name = "scenario_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "testcase_id", referencedColumnName = "id")
    )
    @JsonIgnoreProperties("scenarios")
    private List<Testcase> testcases = new ArrayList<>();
}
