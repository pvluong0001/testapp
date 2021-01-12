package luong.lit.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="tags")
@Data
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String name;

    public Tag(String name) {
        this.name = name;
    }
}
