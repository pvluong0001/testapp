package luong.lit.api;

import luong.lit.response.DataResponse;
import luong.lit.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public DataResponse all() throws InterruptedException {
        return new DataResponse(userRepository.findAll());
    }
}
