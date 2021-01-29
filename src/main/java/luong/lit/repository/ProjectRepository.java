package luong.lit.repository;

import luong.lit.entity.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
    public Optional<Project> getFirstBySlug(String slug);
}
