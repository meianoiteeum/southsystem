/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.southsytem.batch.writer;

import br.com.southsytem.model.Cliente;
import br.com.southsytem.model.ModelWriter;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.*;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemWriterBuilder;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author guilherme.costa
 */
@Configuration
public class WriterConfig {

    @Value("${HOMEPATHOUT}")
    private String dataOut;

    @Bean
    public MultiResourceItemWriter<ModelWriter> writer(){
        return new MultiResourceItemWriterBuilder<ModelWriter>()
                .name("writer")
                .resource(new FileSystemResource(dataOut.concat("Teste")))
                .itemCountLimitPerResource(1)
                .resourceSuffixCreator(suffixCreator())
                .delegate(delegate())
                .build();
    }

    private FlatFileItemWriter<ModelWriter> delegate() {
        return new FlatFileItemWriterBuilder<ModelWriter>()
                .name("delegate")
                .resource(new FileSystemResource(dataOut.concat("teste.dat")))
                .lineAggregator(lineAggregator())
                .headerCallback(header())
                .build();
                
    }

    private FlatFileHeaderCallback header() {
        return new FlatFileHeaderCallback() {
            @Override
            public void writeHeader(Writer writer) throws IOException {
                writer.append(String.format("\t\t\t\t Desenvolvedor: Guilherme Costa\n"));
                writer.append(String.format("\t\t\t\t E-mail: tfguilherme.costa@gmail.com\n"));
                writer.append(String.format("\t\t\t\t Linkedin: in/guilherme-scosta\n"));
                writer.append(String.format("\t\t\t\t Github: https://github.com/meianoiteeum\n"));
            }
        };
    }

    private LineAggregator<ModelWriter> lineAggregator() {
        return modelWriter -> {
            StringBuilder writer = new StringBuilder();
            writer.append(String.format("Quantidade de Cliente: %d\n",modelWriter.getClientsQtde()));
            writer.append(String.format("Quantidade de Vendedores: %d\n",modelWriter.getVendedorQtde()));
            writer.append(String.format("Id da venda mais cara: %d\n",modelWriter.getSaleMostExpensive()));
            writer.append(String.format("Pior Vendedor: %s\n",modelWriter.getWorstSeller().getName()));
            return writer.toString();
        };
    }

    private ResourceSuffixCreator suffixCreator() {
        return new ResourceSuffixCreator() {
            @Override
            public String getSuffix(int index) {
                return index + ".done.dat";
            }
        };
    }
//    public ItemWriter leituraArquivoLarguraFixaWriter() {
//        return items -> items.forEach(System.out::println);
//    }
}
