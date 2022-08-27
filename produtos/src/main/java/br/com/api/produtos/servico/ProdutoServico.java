package br.com.api.produtos.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import br.com.api.produtos.repositorio.ProdutoRepositorio;
import br.com.api.produtos.modelo.*;

//utilizado para fazer o servico de chamada e envio
@Service
public class ProdutoServico {
    //intancia a interface que vai administrar o produto
    @Autowired
    private ProdutoRepositorio pr;

    //chama o resposta modelo para fazer verificação
    @Autowired
    private RespostaModelo rm;

    //Metodo para listar todos os produtos

    //Foi utilizado o interable pq ele usa metodos de requisição ex findall()
    //para pegar todos os elementos do BD
    //TEM O TIPO PRODUTO MODELO
    public Iterable<ProdutoModelo> listar(){
        //ira chamar o produtorepositori e o metodo utilizado para resgatar os dados
        return pr.findAll();
    }



    //METODO PARA CADASTRR PRODUTO OU ALTERÁ-LO

    //usa o metodo para  fazer o cadastro tipo responseEntity por causa do tipo
    public ResponseEntity<?> cadastrarAlterar(ProdutoModelo pm, String acao){// tipo de acao que fara, cadastrar ou alterar
        //faz a verificação do cadastro de produtos
         if(pm.getNome().equals("")){
            //se os valores do produdoModelo forem vazio
            //chama o rm (respostaModelo) para dar a resposta
            rm.setMensagem("O nome do produto é obigatório!");
            //do tipo do metodo retor do resposta modelo um erro de requisição
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
            //chama a marca do banco e verifica se ta vazia
        }else if(pm.getMarca().equals("")){
            rm.setMensagem("O nome da marca é obrigatorio!");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);

         }else{
            if(acao.equals("Cadastrar")){
                //salvar o cadastro
                //o metodo save tanto cadastra quanto altera a informação
                return new ResponseEntity<ProdutoModelo>(pr.save(pm), HttpStatus.CREATED);
            } else{
                //verifica para poder alterar e retorna ok
                return new ResponseEntity<ProdutoModelo>(pr.save(pm), HttpStatus.OK);
            }
         }
    }

    //METODO PARA REMOVER PRODUTO (DELETE)
    //retorno do tipo responseEmtity
    //id como parametro para deletar um determinado prduto com id tal
    public ResponseEntity<RespostaModelo> remover(long codigo){
        pr.deleteById(codigo);//deleta por id
        rm.setMensagem("O produto foi removido com sucesso");
        return new ResponseEntity<RespostaModelo>(rm, HttpStatus.OK);
    }

}
