package luong.lit.api;

import luong.lit.entity.Project;
import luong.lit.response.DataResponse;
import luong.lit.security.entity.User;
import luong.lit.security.repository.UserRepository;
import luong.lit.security.service.FileService;
import luong.lit.security.service.UserDetailsImpl;
import luong.lit.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private FileService fileService;

    @GetMapping()
    public DataResponse all() {
        return new DataResponse(userRepository.findAll());
    }

    @GetMapping("/{id}/un-invite")
    public DataResponse unInviteUsers(@Valid @PathVariable("id") Long projectId) {
        Project project = projectService.show(projectId);
        List<Long> ids = project
                .getUsers().stream().map(User::getId).collect(Collectors.toList());

        List<User> users = (List<User>) userRepository.findAllByIdNotIn(ids);

        return new DataResponse(users);
    }

    @GetMapping("/info")
    public DataResponse getUserInfo() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        return new DataResponse(userDetails);
    }

    @PostMapping("/info")
    public void updateUserInfo(
        @RequestParam("file") MultipartFile file,
        @NotNull @RequestParam("name") String name,
        @NotNull @RequestParam("username") String username
    ) {
        fileService.uploadFile(file);
    }
}
