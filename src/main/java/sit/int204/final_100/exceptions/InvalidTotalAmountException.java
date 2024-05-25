// 65130500100 Chinnaphat Lohasangsuwan
package sit.int204.final_100.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidTotalAmountException extends RuntimeException {
    public InvalidTotalAmountException(String message) {
        super(message);
    }
}
