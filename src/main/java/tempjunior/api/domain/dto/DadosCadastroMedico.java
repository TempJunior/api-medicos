package tempjunior.api.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroMedico(
        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefone,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")/*Expressão regular para digitos, \\d para dizer que é numero
                e de 4 a 6 digitos*/
        String crm,

        @NotNull//Não usando @NotBlank porque não é uma String
        Especialidade especialidade,

        @NotNull
        @Valid/*Como Endereço é um outro DTO, precisamos passar essa anotação de @Valid
        para ele tambem validar esse outro atributo. Tambem precisamos mapear essa Classe*/
        EnderecoDTO endereco) {
}
