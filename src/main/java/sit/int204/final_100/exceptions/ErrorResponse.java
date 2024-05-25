// 65130500100 Chinnaphat Lohasangsuwan
package sit.int204.final_100.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private final int status;
    private final String message;
    private final String instance;
    private List<ValidationError> errors;

    public ErrorResponse(int status, String message, String instance) {
        this.status = status;
        this.message = message;
        this.instance = "uri=" + instance;
    }

    public void addValidationError(String field, String message) {
        if (Objects.isNull(errors)) {
            errors = new ArrayList<>();
        }
        errors.add(new ValidationError(field, message));
    }

    private record ValidationError(String field, String message) {
    }
}
