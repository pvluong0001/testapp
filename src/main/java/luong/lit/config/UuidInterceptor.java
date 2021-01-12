package luong.lit.config;

import luong.lit.constant.RequestConstant;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
public class UuidInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RequestConstant.uuid = UUID.randomUUID();
        MDC.put("uuid", RequestConstant.uuid.toString());
        return true;
    }
}
