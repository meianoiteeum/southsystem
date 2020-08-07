package br.com.southsytem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private Integer id;
    private Integer quatity;
    private Double price;

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", quatity='" + quatity + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
