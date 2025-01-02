package tempjunior.api.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarMedico(
        @NotNull
        Long id,

        String nome,
        String telefone,
        EnderecoDTO endereco
) {
}
