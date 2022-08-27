package br.com.api.produtos.modelo;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

//utilizado para  injetar dependencias
@Component
// assim mensagem ou qualquer atriuto ja possui um metodo getter an setter
@Getter
@Setter
public class RespostaModelo {
    private String mensagem;
}
