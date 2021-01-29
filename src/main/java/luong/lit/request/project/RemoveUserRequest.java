package luong.lit.request.project;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RemoveUserRequest {
    @NotNull
    private Long id;
}
