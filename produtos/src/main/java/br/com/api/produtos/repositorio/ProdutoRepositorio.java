package br.com.api.produtos.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.api.produtos.modelo.*;

//indeica que é um repositorio
@Repository
//crudrepository chama o modelo e o tipo do id da tabela que será usada para
//fazer o update, delete, e etc.
public interface ProdutoRepositorio extends CrudRepository<ProdutoModelo, Long>{
    
}
