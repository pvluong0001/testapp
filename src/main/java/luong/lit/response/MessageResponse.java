package luong.lit.response;

import luong.lit.constant.RequestConstant;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@ResponseBody
public class MessageResponse {
    public String message;
    public UUID id = RequestConstant.uuid;

    public MessageResponse(String message) {
        this.message = message;
    }

    public MessageResponse() {
        this.message = "OK";
    }
}
