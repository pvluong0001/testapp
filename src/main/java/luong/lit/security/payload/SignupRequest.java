package luong.lit.security.payload;

import lombok.Data;

import java.util.Set;

import javax.validation.constraints.*;

@Data
public class SignupRequest {
    @NotBlank
    @Size(min = 3)
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 3)
    private String name;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
}
