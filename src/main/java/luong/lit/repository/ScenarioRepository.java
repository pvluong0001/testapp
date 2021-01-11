package luong.lit.repository;

import luong.lit.entity.Project;
import luong.lit.entity.Scenario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScenarioRepository extends CrudRepository<Scenario, Long> {
}
