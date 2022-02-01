package in.hemanth.trainingprojectsb4.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class ApiExceptionController {

    @ExceptionHandler(value = {CustomIdNotFoundException.class})
    public ResponseEntity<Object> handleIdNotFound(CustomIdNotFoundException e){
        HttpStatus status=HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(
                new ApiException(e.getMessage(),status, LocalDate.now(),e),
                status
        );
    }
}
