package tempjunior.api.domain.models;

import jakarta.persistence.*;
import lombok.*;
import tempjunior.api.domain.dto.DadosAtualizarMedico;
import tempjunior.api.domain.dto.DadosCadastroMedico;
import tempjunior.api.domain.dto.Especialidade;

@Entity(name = "Medico")
@Table(name = "tb_medicos")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    //Não esquecer de todos os campos precisam ser exatamente como vem no JSON
    public Medico(DadosCadastroMedico dados){
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.ativo = true;
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAtualizarMedico dados){
        if (dados.nome() != null){
            this.nome = dados.nome();
        }
        if (dados.telefone() != null){
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null){
            this.endereco.atualizarEndereco(dados.endereco());
        }
    }

    public void excluirPorId(Long id) {
        this.ativo = false;
    }
}
