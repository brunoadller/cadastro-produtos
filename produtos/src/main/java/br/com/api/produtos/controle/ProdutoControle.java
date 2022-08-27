package br.com.api.produtos.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.produtos.modelo.ProdutoModelo;
import br.com.api.produtos.modelo.RespostaModelo;
import br.com.api.produtos.servico.ProdutoServico;

//responsavel pelas rotas
//contribui para nao dar erro de CORS
//"http://localhost:300" ou * 
@RestController
@CrossOrigin(origins = "*")
public class ProdutoControle{

    //criando o objet do tipo rodut servico para pegar os dados do servico
    @Autowired
    private ProdutoServico ps;
    //DELETANDO PRODUTO
    //como sera removido um produto comid especifico colaca-se mais uma arra para fazer o delete
    @DeleteMapping("/remover/{codigo}")
    public ResponseEntity<RespostaModelo> remover(@PathVariable long codigo){
        return ps.remover(codigo);
    }


    //ALTERA PRODUTO
    @PutMapping("/alterar")//putMapping indica que faz o update
    public ResponseEntity<?> alterar(@RequestBody ProdutoModelo pm){
        return  ps.cadastrarAlterar(pm, "alterar");
    }

    //CADASTRA PRODUTO
    //fazendo a rota do cadastro
    @PostMapping("/cadastrar")//tipo post
    //para capturar os valores usa-se a anotation requestbody
    public ResponseEntity<?> cadastrar(@RequestBody ProdutoModelo pm){
        //chamando o metodo de servicos
        return ps.cadastrarAlterar(pm, "cadastrar");
    }

    //LISTA PRODUTOS
    @GetMapping("/listar")//tipo select
    //tipo de dado que o metodo listar irá usar
    public Iterable<ProdutoModelo> listar(){
        //chamando o metodo listar de servico
        return ps.listar();
    }
    //EXISTE VARIOS METODOS NO JPA. ESTE, ESTÁ FAZENDO O GET
    @GetMapping("/")
    public String rota(){
        return "API DE PRODUTOS FUNCIONANDO";
    }
}
