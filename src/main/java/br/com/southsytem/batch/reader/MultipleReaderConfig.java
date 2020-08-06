package br.com.southsytem.batch.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class MultipleReaderConfig {

    @Bean
    public MultiResourceItemReader multipleReader(FlatFileItemReader multipleFilesReader){
        return new MultiResourceItemReaderBuilder<>()
                .name("multipleReader")
                .resources(new ClassPathResource(System.getProperty("user.home").concat("data/in").concat("/*.dat")))
                .delegate(new CustomVendasReader(multipleFilesReader))
                .build();
    }
}
