package br.com.southsytem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vendedor {
    private String cpf;
    private String name;
    private String salary;

    @Override
    public String toString() {
        return "Vendedor{" +
                "cpf='" + cpf + '\'' +
                ", name='" + name + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }
}
