package br.com.southsytem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vendas {
    private String saleId;
    private List<Item> itens = new ArrayList<>();
    private String listItens;
    private String salesManName;
    private Vendedor vendedor;

    @Override
    public String toString() {
        return "Vendas{" +
                "saleId='" + saleId + '\'' +
                ", listItem='" + itens + '\'' +
                ", vendedor='" + vendedor + '\'' +
                '}';
    }
}
