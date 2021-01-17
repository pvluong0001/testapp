package luong.lit.api;

import luong.lit.entity.Project;
import luong.lit.request.project.CreateProjectRequest;
import luong.lit.response.DataResponse;
import luong.lit.response.MessageResponse;
import luong.lit.service.ProjectService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("project")
public class ProjectController {
    @Autowired
    ProjectService projectService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping()
    public DataResponse all() throws InterruptedException {
        return new DataResponse(projectService.getAll());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public DataResponse create(@Valid @RequestBody CreateProjectRequest request) {
        Project data = modelMapper.map(request, Project.class);

        Project project = projectService.store(data);

        return new DataResponse(project);
    }

    @GetMapping("/{id}")
    public DataResponse show(@Valid @PathVariable("id") Long projectId) {
        return new DataResponse(projectService.show(projectId));
    }

    @RequestMapping(path = "/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    public DataResponse update(
        @PathVariable("id") Long projectId,
        @Valid @RequestBody CreateProjectRequest request
    ) {
        Project project = projectService.update(projectId, request);

        return new DataResponse(project);
    }

    @DeleteMapping("/{id}")
    public MessageResponse delete(@PathVariable("id") Long projectId) {
        projectService.delete(projectId);

        return new MessageResponse("Delete success");
    }
}
