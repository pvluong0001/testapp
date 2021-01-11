package luong.lit.exception;

import lombok.Data;

@Data
public class FieldErrorException {
    public FieldErrorException(String field, String message) {
    }

    public FieldErrorException(String field) {
    }
}
