// 65130500100 Chinnaphat Lohasangsuwan
package sit.int204.final_100.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import sit.int204.final_100.exceptions.DuplicateValueException;
import sit.int204.final_100.exceptions.ErrorResponse;
import sit.int204.final_100.exceptions.InvalidTotalAmountException;
import sit.int204.final_100.exceptions.NotFoundException;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Validation error. Check 'errors' field for details. addOrderDto",
                request.getDescription(false)
        );

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();

        fieldErrors.forEach(fieldError ->
                errorResponse.addValidationError(fieldError.getField(), fieldError.getDefaultMessage())
        );

        globalErrors.forEach(objectError ->
                errorResponse.addValidationError(objectError.getObjectName(), objectError.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler({InvalidTotalAmountException.class, DuplicateValueException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleInvalidTotalAmountException(InvalidTotalAmountException exception, WebRequest request) {
        return buildErrorResponse(exception, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException exception, WebRequest request) {
        return buildErrorResponse(exception, HttpStatus.NOT_FOUND, request);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(Exception exception, HttpStatus httpStatus, WebRequest request) {
        return buildErrorResponse(exception.getMessage(), httpStatus, request);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(String message, HttpStatus httpStatus, WebRequest request) {
        int statusCode = httpStatus.value();
        String requestUri = ((ServletWebRequest) request).getRequest().getRequestURI();

        ErrorResponse errorResponse = new ErrorResponse(statusCode, message, requestUri);
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}
