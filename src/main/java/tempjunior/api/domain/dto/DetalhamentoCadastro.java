package tempjunior.api.domain.dto;

import tempjunior.api.domain.models.Endereco;
import tempjunior.api.domain.models.Medico;

public record DetalhamentoCadastro(Long id, String nome, String email, String telefone, String crm, Especialidade especialidade, Endereco endereco) {

    public DetalhamentoCadastro(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }
}
