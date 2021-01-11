package luong.lit.service;

import luong.lit.entity.Scenario;
import luong.lit.repository.ScenarioRepository;
import luong.lit.request.project.CreateScenarioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScenarioService {
    @Autowired
    ScenarioRepository scenarioRepository;

    public Iterable<Scenario> getAll() {
        return scenarioRepository.findAll();
    }

    public Scenario store(Scenario resource) {
        return scenarioRepository.save(resource);
    }

    public Scenario show(Long id) {
        return scenarioRepository.findById(id)
                .orElseThrow(IndexOutOfBoundsException::new);
    }

    public Scenario update(Long id, CreateScenarioRequest request) {
        Scenario Scenario = scenarioRepository.findById(id).orElseThrow(IndexOutOfBoundsException::new);

        Scenario.setName(request.name);
        Scenario.setDescription(request.name);
        scenarioRepository.save(Scenario);

        return Scenario;
    }

    public void delete(Long id) {
        scenarioRepository.deleteById(id);
    }
}
