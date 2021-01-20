package luong.lit.service;

import luong.lit.entity.Scenario;
import luong.lit.entity.Tag;
import luong.lit.exception.UniqueDataException;
import luong.lit.repository.ScenarioRepository;
import luong.lit.repository.TagRepository;
import luong.lit.request.scenario.CreateScenarioRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Scenario store(CreateScenarioRequest createScenarioRequest) throws UniqueDataException {
        Scenario scenario = new Scenario();
        scenario.setName(createScenarioRequest.getName());
        scenario.setDescription(createScenarioRequest.getDescription());

        List<Tag> tags = (List<Tag>) tagRepository.findAllByIdIn(createScenarioRequest.getTags());
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
