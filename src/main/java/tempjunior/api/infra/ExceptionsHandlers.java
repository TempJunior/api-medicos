package tempjunior.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandlers {

    /*Passar qual a classe da Exception que quer tratar(Exception que sera capturada) e fazer o tratamento adequado*/
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseException> tratarErro404(){
        ErrorResponseException error = new ErrorResponseException("Usuario não encontrado", "404");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
