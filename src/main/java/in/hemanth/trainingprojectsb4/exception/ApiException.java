package in.hemanth.trainingprojectsb4.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDate;

public class ApiException {

    private final String type="CUSTOM_EXCEPTION";
    private String message;
    private HttpStatus httpStatus;

    @Override
    public String toString() {
        return "ApiException{" +
                "type='" + type + '\'' +
                ", message='" + message + '\'' +
                ", httpStatus=" + httpStatus +
                ", timestamp=" + timestamp +
                ", throwable=" + throwable +
                '}';
    }

    //    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate timestamp;
    private Throwable throwable;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public String getType() {
        return type;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public ApiException(String message, HttpStatus httpStatus, LocalDate timestamp, Throwable throwable) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
        this.throwable = throwable;
    }
}
