package luong.lit.api;

import luong.lit.entity.Tag;
import luong.lit.request.project.CreateTagRequest;
import luong.lit.request.project.CreateTagRequest;
import luong.lit.response.DataResponse;
import luong.lit.response.MessageResponse;
import luong.lit.service.TagService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("tag")
public class TagController {
    @Autowired
    TagService tagService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping()
    public DataResponse all() {
        return new DataResponse(tagService.getAll());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public DataResponse create(@Valid @RequestBody CreateTagRequest request) {
        Tag tag = tagService.store(request);

        return new DataResponse(tag);
    }

    @GetMapping("/{id}")
    public DataResponse show(@Valid @PathVariable("id") Long projectId) {
        return new DataResponse(tagService.show(projectId));
    }

    @DeleteMapping("/{id}")
    public MessageResponse delete(@PathVariable("id") Long projectId) {
        tagService.delete(projectId);

        return new MessageResponse("Delete success");
    }
}
