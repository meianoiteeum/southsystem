/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.southsytem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author guilherme.costa
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    private String cnpj;
    private String name;
    private String businessArea;

    @Override
    public String toString() {
        return "Cliente{" +
                "cnpj='" + cnpj + "'" +
                ", name='" + name + "'" +
                ", business area='" + businessArea + "'" +
                '}';
    }
}
