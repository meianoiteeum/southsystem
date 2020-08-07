/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.southsytem.batch.step;

import br.com.southsytem.model.ModelWriter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author guilherme.costa
 */
@Configuration
public class StepConfig {
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step leituraArquivoLarguraFixaStep(MultiResourceItemReader multipleReader,
                                              ItemWriter leituraArquivoMultiplosFormatosItemWriter) {
        return stepBuilderFactory
                .get("leituraArquivoLarguraFixaStep")
                .chunk(1)
                .reader(multipleReader)
                .writer(leituraArquivoMultiplosFormatosItemWriter)
                .build();
    }
}
