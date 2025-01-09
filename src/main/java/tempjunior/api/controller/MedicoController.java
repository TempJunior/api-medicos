package tempjunior.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import tempjunior.api.domain.dto.DadosAtualizarMedico;
import tempjunior.api.domain.dto.DadosCadastroMedico;
import tempjunior.api.domain.dto.DadosListagemMedicos;
import tempjunior.api.domain.dto.DetalhamentoCadastro;
import tempjunior.api.domain.models.Medico;
import tempjunior.api.domain.models.MedicoRepository;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    //@Autowired é usado para injeção de dependencias
    @Autowired
    private MedicoRepository repository;

    /*receber uma string no parâmetro do método cadastrar do controller e
    anotá-la com @RequestBody, o Spring exibirá o corpo todo do JSON e passará para essa string.

    Se quisermos receber os campos separados, não podemos receber como string. Será necessário criarmos uma
    classe, e nela declarar os atributos com os mesmos nomes que estão sendo recebidos pelo JSON.*/

    /*A Declaração da anotação @Transactional é usada para operações que precisa de abertura
    * no banco de dados, caso for apenas fazer um processamento, não é necessario*/

    /*Quando realizamos um cadastro, devemos retornar o código HTTP 201 (Created) para indicar que o recurso foi criado com sucesso. Além disso, o corpo da resposta deve conter os dados do novo registro no formato JSON para que o cliente possa validar as informações.

    Para atender aos padrões do protocolo HTTP, também incluímos no cabeçalho da resposta o Location Header, que aponta para o endereço onde o recurso recém-criado pode ser acessado. Esse cabeçalho é essencial para o front-end ou aplicativos móveis obterem a URL do recurso.
    Implementação no Spring Boot:

    Código HTTP: 201.
    Formato do Corpo da Resposta: JSON com os dados do novo registro.
    Cabeçalho Location: Contém a URL para acessar o recurso criado.*/
    @PostMapping
    @Transactional
    public ResponseEntity<DetalhamentoCadastro> cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder){
        var medico = new Medico(dados);
        repository.save(medico);
        URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhamentoCadastro(medico));
    }

    /**
     * Retorna uma página de objetos {@link DadosListagemMedicos} com base na paginação fornecida.
     *
     * Este método utiliza o repositório para buscar todos os médicos e transforma cada
     * instância de {@link Medico} em uma instância de {@link DadosListagemMedicos} usando
     * a operação de mapeamento.
     *
     * @param paginacao A página e o tamanho da página para a consulta, encapsulados em um
     *                  objeto {@link Pageable}.
     * @return Uma página de objetos {@link DadosListagemMedicos} que contêm as informações
     *         dos médicos de acordo com a paginação fornecida.
     */

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicos>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        /*Essa Stream transforma um dado, pois se for olhar no Repository
        * Ele chama no parametro e devolve a classe Medico. Fazemos o Casting de DadosListagemMedicos
        * com Strem.Map que passando a expressão de :: instancia um novo Medico e retorna o dados
        * transformado e armazena em uma lista.
        *
        * METODO ANTIGO
        *         return repository.findAll(paginacao).stream().map(DadosListagemMedicos::new).toList();
         */


        //Requisição personalizada de paginas localhost:8080/medicos?size=1&page=2
        //passando tamanho da lista e a pagina

        //para ordenação passamos no proprio parametro da URL passando localhost:8080/medicos?sort=nome
        var page =  repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedicos::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetalhamentoCadastro> atualizar(@RequestBody @Valid DadosAtualizarMedico dadosAtualizacao){
        var medico = repository.getReferenceById(dadosAtualizacao.id());
        medico.atualizarInformacoes(dadosAtualizacao);

        //Usando um DTO para devolver um dado detalhado de atualização
        return ResponseEntity.ok(new DetalhamentoCadastro(medico));
    }

    //@Deprecated
    /*Exclusão do banco de dados
    * Para usar, só preciso passar a URL do controller + /id da pessoa que deseja excluir*/
    //@DeleteMapping("/{id}")
    //@Transactional
    //public void delete(@PathVariable Long id){//PathVariable é para o Spring pegar o id do parametro do DeleteMapping
        //repository.deleteById(id);
    //}

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Medico> deleteLogical(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluirPorId(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhamentoCadastro> detalhamentoMedico(@PathVariable Long id){
        var medico = repository.getReferenceById(id);

        return ResponseEntity.ok(new DetalhamentoCadastro(medico));
    }
}
