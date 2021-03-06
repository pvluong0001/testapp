package luong.lit.request.tag;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateTagRequest {
    @Length(max = 255)
    @NotBlank
    @NotNull
    public String name;
}
