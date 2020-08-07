package br.com.southsytem.batch.reader;

import br.com.southsytem.model.Cliente;
import br.com.southsytem.model.Vendas;
import br.com.southsytem.model.Vendedor;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class LineMapperConfig {
    @Bean
    public PatternMatchingCompositeLineMapper lineMapper(){
        PatternMatchingCompositeLineMapper lineMapper = new PatternMatchingCompositeLineMapper();
        lineMapper.setTokenizers(tokenizers());
        lineMapper.setFieldSetMappers(fieldSetMappers());
        return lineMapper;
    }

    private Map<String, FieldSetMapper> fieldSetMappers() {
        Map<String, FieldSetMapper> fieldSetMappers = new HashMap<>();
        fieldSetMappers.put("001*",fieldSetMapper(Vendedor.class));
        fieldSetMappers.put("002*",fieldSetMapper(Cliente.class));
        fieldSetMappers.put("003*",fieldSetMapper(Vendas.class));
        return fieldSetMappers;
    }

    private FieldSetMapper fieldSetMapper(Class classe) {
        BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(classe);
        return fieldSetMapper;
    }

    private Map<String, LineTokenizer> tokenizers() {
        Map<String, LineTokenizer> tokenizers = new HashMap<>();
        tokenizers.put("001*",vendedorLineTokenizer());
        tokenizers.put("002*",clienteLineTokenizer());
        tokenizers.put("003*",vendasLineTokenizer());
        return tokenizers;
    }

    private LineTokenizer vendedorLineTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter("ç");
        lineTokenizer.setNames("cpf","name","salary");
        lineTokenizer.setIncludedFields(1,2,3);
        return lineTokenizer;
    }

    private LineTokenizer clienteLineTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter("ç");
        lineTokenizer.setNames("cnpj","name","businessArea");
        lineTokenizer.setIncludedFields(1,2,3);
        return lineTokenizer;
    }

    private LineTokenizer vendasLineTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter("ç");
        lineTokenizer.setNames("saleId","listItens","salesManName");
        lineTokenizer.setIncludedFields(1,2,3);
        return lineTokenizer;
    }
}
