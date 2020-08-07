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
    private String id;
    private String quatity;
    private String price;

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", quatity='" + quatity + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
