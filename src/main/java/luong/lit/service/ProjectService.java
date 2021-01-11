package luong.lit.service;

import luong.lit.entity.Project;
import luong.lit.repository.ProjectRepository;
import luong.lit.request.project.CreateProjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;

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

        project.setName(request.name);
        projectRepository.save(project);

        return project;
    }

    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}
