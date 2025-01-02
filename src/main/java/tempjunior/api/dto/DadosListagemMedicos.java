package tempjunior.api.dto;

import tempjunior.api.models.Medico;

public record DadosListagemMedicos(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {
    /*Precisa passar o proprio construtor do Record e pode contar mais de 1 construtor, porem, todos precisam
    * chamar o construtor principal do Record.*/

    public DadosListagemMedicos(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
