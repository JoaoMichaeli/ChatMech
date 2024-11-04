package br.com.chatmech.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Mecanica {
    private Integer idMecanica;
    private String nome;
    private String descricao;
    private String imagem;
}
