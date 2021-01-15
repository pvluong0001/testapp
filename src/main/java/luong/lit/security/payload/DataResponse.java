package luong.lit.security.payload;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class DataResponse {
    public Object data;
    public final String message = "OK";
    public DataResponse(Object object) {
        this.data = object;
    }

    public DataResponse() {
    }
}
