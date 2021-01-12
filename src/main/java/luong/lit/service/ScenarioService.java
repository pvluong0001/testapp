package luong.lit.service;

import luong.lit.api.ProjectController;
import luong.lit.entity.Scenario;
import luong.lit.entity.Tag;
import luong.lit.repository.ScenarioRepository;
import luong.lit.repository.TagRepository;
import luong.lit.request.project.CreateScenarioRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScenarioService {
    @Autowired
    ScenarioRepository scenarioRepository;
    @Autowired
    TagRepository tagRepository;
    @Autowired
    ModelMapper modelMapper;

    public Iterable<Scenario> getAll() {
        return scenarioRepository.findAll();
    }

    public Scenario store(CreateScenarioRequest createScenarioRequest) {
        Scenario scenario = new Scenario();
        scenario.setName(createScenarioRequest.getName());
        scenario.setDescription(createScenarioRequest.getDescription());

        List<Tag> tags = (List<Tag>) tagRepository.findAllById(createScenarioRequest.getTags());

        scenario.setTags(tags);

        return scenarioRepository.save(scenario);
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
