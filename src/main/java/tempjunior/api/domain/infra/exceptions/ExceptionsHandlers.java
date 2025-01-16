package tempjunior.api.domain.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(erros.stream().map(DetalhamentoErro404::new).toList());
    }

    private record DetalhamentoErro404(String erro, String mensagem){
        public DetalhamentoErro404(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
