package luong.lit.response;

import luong.lit.constant.RequestConstant;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@ResponseBody
public class ErrorMessageResponse {
    public String error;
    public UUID id = RequestConstant.uuid;

    public ErrorMessageResponse(String error) {
        this.error = error;
    }
}
