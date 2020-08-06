/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.southsytem.batch.reader;

import br.com.southsytem.model.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 *
 * @author guilherme.costa
 */

@Configuration
public class ReaderConfig {

    @StepScope
    @Bean
    public FlatFileItemReader leituraArquivoLarguraFixaReader(
            LineMapper lineMapper){
        return new FlatFileItemReaderBuilder<Cliente>()
                .name("leituraArquivoLarguraFixaReader")
                .encoding("utf-8")
                .resource(new ClassPathResource("%homepath%/data/in"))
                .lineMapper(lineMapper)
                .build();
    }
}
