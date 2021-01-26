package luong.lit.service;

import luong.lit.entity.Project;
import luong.lit.entity.Scenario;
import luong.lit.repository.ProjectRepository;
import luong.lit.repository.ScenarioRepository;
import luong.lit.request.project.CreateProjectRequest;
import luong.lit.request.project.UpdateScenarioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ScenarioRepository scenarioRepository;

    public Iterable<Project> getAll() {
        return projectRepository.findAll();
    }

    public Project store(Project resource) {
        return projectRepository.save(resource);
    }

    public Project show(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(IndexOutOfBoundsException::new);
    }

    public Project update(Long id, CreateProjectRequest request) {
        Project project = projectRepository.findById(id).orElseThrow(IndexOutOfBoundsException::new);

        project.setName(request.getName());
        project.setDescription(request.getDescription());
        projectRepository.save(project);

        return project;
    }

    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    public Project updateScenario(Long projectId, UpdateScenarioRequest request) {
        Project project = projectRepository.findById(projectId).orElseThrow(IndexOutOfBoundsException::new);
        List<Scenario> scenarios = (List<Scenario>) scenarioRepository.findAllByIdIn(request.getScenarios());

        project.setScenarios(scenarios);
        projectRepository.save(project);
        return project;
    }
}
