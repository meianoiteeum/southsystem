/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.southsytem.batch.job;

import br.com.southsytem.batch.listener.ListenerConfig;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author guilherme.costa
 */
@EnableBatchProcessing
@Configuration
public class JobConfig {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job arquivoLarguraFixaJob(ListenerConfig listenerConfig, Step leituraArquivoLarguraFixaStep) {
        return jobBuilderFactory
                .get("arquivoLarguraFixaJob")
                .start(leituraArquivoLarguraFixaStep)
                .incrementer(new RunIdIncrementer())
                .listener(listenerConfig)
                .build();
    }
}
