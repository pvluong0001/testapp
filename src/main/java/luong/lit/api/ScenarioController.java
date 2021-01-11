package luong.lit.api;

import luong.lit.entity.Scenario;
import luong.lit.request.project.CreateScenarioRequest;
import luong.lit.response.DataResponse;
import luong.lit.response.MessageResponse;
import luong.lit.service.ScenarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("scenario")
public class ScenarioController {
    @Autowired
    ScenarioService scenarioService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping()
    public DataResponse all() {
        return new DataResponse(scenarioService.getAll());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public DataResponse create(@Valid @RequestBody CreateScenarioRequest request) {
        Scenario data = modelMapper.map(request, Scenario.class);

        Scenario scenario = scenarioService.store(data);

        return new DataResponse(scenario);
    }

    @GetMapping("/{id}")
    public DataResponse show(@Valid @PathVariable("id") Long projectId) {
        return new DataResponse(scenarioService.show(projectId));
    }

    @DeleteMapping("/{id}")
    public MessageResponse delete(@PathVariable("id") Long projectId) {
        scenarioService.delete(projectId);

        return new MessageResponse("Delete success");
    }
}
