package br.com.chatmech.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Veiculo {
    private String placa;
    private String imagem;
    private String modelo;
    private String fabricante;
    private Integer ano;
    private Double quilometragemAtual;

}
