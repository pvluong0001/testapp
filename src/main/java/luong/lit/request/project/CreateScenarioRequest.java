package luong.lit.request.project;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateScenarioRequest {
    @Length(max = 255)
    @NotBlank
    @NotNull
    public String name;

    @NotNull
    public String description;

    public List<Long> tags;
}
