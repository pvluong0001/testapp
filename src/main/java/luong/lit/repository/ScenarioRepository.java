package luong.lit.repository;

import luong.lit.entity.Scenario;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScenarioRepository extends CrudRepository<Scenario, Long> {
    @EntityGraph(attributePaths = {"tags"})
    List<Scenario> findAll();

    public Iterable<Scenario> findAllByIdIn(List<Long> ids);
}
