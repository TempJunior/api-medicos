package tempjunior.api.domain.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ErrorResponseException {
    @JsonProperty("mensagem")
    String message;

    @JsonProperty("codigo")
    String error;

    public ErrorResponseException(String message, String error){
        this.message = message;
        this.error = error;
    }
}
