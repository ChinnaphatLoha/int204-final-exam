// 65130500100 Chinnaphat Lohasangsuwan
package sit.int204.final_100.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateValueException extends RuntimeException{
    public DuplicateValueException(String message) {
        super(message);
    }
}
