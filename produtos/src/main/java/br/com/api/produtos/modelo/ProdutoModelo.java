package br.com.api.produtos.modelo;
//criando as anotações

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

//DIZENDO QUE É UMA ENTIDADE
@Entity
//dando nome a tabela
@Table(name = "produtos")
//criando getters e setters com lombok
@Getter
@Setter
public class ProdutoModelo {
    //criando o id da tabela e dizendo seu tipo
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String nome;
    private String marca;
}
