package br.com.southsytem.batch.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

@Configuration
public class MultipleReaderConfig {

    @Value("${HOMEPATHIN}")
    private Resource[] inputResources;

    @Bean
    @StepScope
    public MultiResourceItemReader multipleReader(FlatFileItemReader multipleFilesReader) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        inputResources = resolver.getResources("*.dat");
        return new MultiResourceItemReaderBuilder<>()
                .name("multipleReader")
                .resources(inputResources)
                .delegate(new CustomReader(multipleFilesReader))
                .build();
    }
}
