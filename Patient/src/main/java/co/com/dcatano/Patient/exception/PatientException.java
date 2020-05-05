package co.com.dcatano.Patient.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PatientException extends Exception {
    private static final Logger logger = LoggerFactory.getLogger(PatientException.class);

    public PatientException(){}

    public PatientException(String message) {
        super(message);
    }

    @ExceptionHandler(PatientException.class)
    public ResponseEntity<String> handlePatientException(PatientException p) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(p.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error");
    }
}
