package luong.lit.repository;

import luong.lit.entity.Tag;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {
    public Optional<Tag> findByName(String name);

    @EntityGraph(attributePaths = {"scenarios"})
    public Iterable<Tag> findAll();
    public Iterable<Tag> findAllByIdIn(Collection<Long> ids);
    public Iterable<Tag> findByNameContainingIgnoreCase(String name);

    Boolean existsTagByName(String name);
}
