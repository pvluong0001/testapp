package luong.lit.service;

import luong.lit.entity.Scenario;
import luong.lit.entity.Tag;
import luong.lit.repository.TagRepository;
import luong.lit.request.project.CreateScenarioRequest;
import luong.lit.request.project.CreateTagRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {
    @Autowired
    TagRepository tagRepository;

    public Iterable<Tag> getAll() {
        return tagRepository.findAll();
    }

    public Tag store(Tag resource) {
        return tagRepository.save(resource);
    }

    public Tag show(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(IndexOutOfBoundsException::new);
    }

    public Tag update(Long id, CreateTagRequest request) {
        Tag tag = tagRepository.findById(id).orElseThrow(IndexOutOfBoundsException::new);

        tag.setName(request.name);
        tagRepository.save(tag);

        return tag;
    }

    public void delete(Long id) {
        tagRepository.deleteById(id);
    }
}
