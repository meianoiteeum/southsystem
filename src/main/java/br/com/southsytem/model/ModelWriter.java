package br.com.southsytem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModelWriter {
    private Integer clientsQtde;
    private Integer vendedorQtde;
    private Integer saleMostExpensive;
    private Vendedor worstSeller;

    @Override
    public String toString() {
        return "ModelWriter{" +
                "Quantidade de Clientes=" + clientsQtde +
                ", Quantidade de Vendedores=" + vendedorQtde +
                ", Id da venda mais cara=" + saleMostExpensive +
                ", Pior vendedor=" + worstSeller +
                '}';
    }
}
