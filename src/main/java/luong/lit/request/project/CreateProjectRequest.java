package luong.lit.request.project;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateProjectRequest {
    @Length(max = 255)
    @NotBlank
    @NotNull
    private String name;
    private String description;
}
