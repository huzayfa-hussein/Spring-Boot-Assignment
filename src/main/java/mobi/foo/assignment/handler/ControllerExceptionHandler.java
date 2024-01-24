package mobi.foo.assignment.handler;

import mobi.foo.assignment.Constants;
import mobi.foo.assignment.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception handler <a href="https://www.bezkoder.com/spring-boot-controlleradvice-exceptionhandler/">Reference</a>
 */

@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleFieldsValidation(MethodArgumentNotValidException exception) {
        String status = Constants.STATUS_FAILED;
        // default message in case exception message is null
        String message = Constants.DEFAULT_BAD_REQUEST_MESSAGE;
        if (exception.getFieldError() != null) {
            message = exception.getFieldError().getDefaultMessage();
        }
        return (new ApiResponse(status, message));
    }

    // example with responseEntity (REMOVE @RESPONSE BODY ANNOTATION)
//    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<ApiResponse> handleFieldsValidation(MethodArgumentNotValidException exception) {
//        String status = Constants.STATUS_FAILED;
//        // default message in case exception message is null
//        String message = Constants.DEFAULT_BAD_REQUEST_MESSAGE;
//        if (exception.getFieldError() != null) {
//            message = exception.getFieldError().getDefaultMessage();
//        }
//        return ResponseEntity.badRequest().body(new ApiResponse(status, message));
//    }
}
