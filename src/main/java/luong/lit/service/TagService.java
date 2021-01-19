package luong.lit.service;

import luong.lit.entity.Tag;
import luong.lit.exception.UniqueDataException;
import luong.lit.repository.TagRepository;
import luong.lit.request.tag.CreateTagRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {
    @Autowired
    TagRepository tagRepository;

    public Iterable<Tag> getAll() {
        return tagRepository.findAll();
    }

    public Tag store(CreateTagRequest resource) throws UniqueDataException {
        String tagName = resource.getName();
        if (tagRepository.existsTagByName(tagName)) {
            throw new UniqueDataException("name");
        }

        Tag tag = new Tag();
        tag.setName(tagName);

        return tagRepository.save(tag);
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

    public Iterable<Tag> searchByName(String name) {
        return tagRepository.findByNameContainingIgnoreCase(name);
    }
}
