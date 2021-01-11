package luong.lit.response;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class DataResponse extends MessageResponse {
    public Object data;

    public DataResponse(String message, JsonNode node) {
        this.message = message;
        this.data = node;
    }

    public DataResponse(Object object) {
        this.data = object;
    }

    public DataResponse() {
        this.message = "Ok from entity";
    }
}
