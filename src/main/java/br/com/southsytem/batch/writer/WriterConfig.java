/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.southsytem.batch.writer;

import br.com.southsytem.model.Cliente;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author guilherme.costa
 */
@Configuration
public class WriterConfig {
    @Bean
    public ItemWriter leituraArquivoLarguraFixaWriter() {
        return items -> items.forEach(System.out::println);
    }
}
