package luong.lit.repository;

import luong.lit.entity.Tag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {
    public Optional<Tag> findByName(String name);

    public Iterable<Tag> findByNameContainingIgnoreCase(String name);
}
