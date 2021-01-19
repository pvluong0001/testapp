package luong.lit.handler;

import luong.lit.exception.FieldErrorException;
import luong.lit.exception.UniqueDataException;
import luong.lit.response.DataResponse;
import luong.lit.response.ErrorMessageResponse;
import luong.lit.response.MessageResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageResponse notFoundException() {
        return new MessageResponse("Resource not existed!");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MessageResponse notValidArgument() {
        return new MessageResponse("Argument is not valid!");
    }

    @ExceptionHandler(UniqueDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageResponse handleUniqueDataException(UniqueDataException exception) {
        return new ErrorMessageResponse(String.format("Field `%s` is already existed!", exception.getMessage()));
    }

    // error handle for @Valid
    @Override
    protected ResponseEntity<Object>
    handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                 HttpHeaders headers,
                                 HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        //Get all fields errors
        HashMap<String, String> errors = new HashMap<>();

        for (FieldError err : ex.getBindingResult().getFieldErrors()) {
            errors.put(err.getField(), err.getDefaultMessage());
        }

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);
    }
}
