package br.com.southsytem.batch.reader;

import br.com.southsytem.model.Cliente;
import br.com.southsytem.model.Item;
import br.com.southsytem.model.Vendas;
import br.com.southsytem.model.Vendedor;
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomVendasReader implements ItemStreamReader, ResourceAwareItemReaderItemStream {
    private Object objAtual;
    private FlatFileItemReader<Object> delegate;
    private Map<String,Vendedor> vendedorMap = new HashMap<>();

    public CustomVendasReader(FlatFileItemReader<Object> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object read() throws Exception {
        objAtual = peek();

        if(objAtual instanceof Cliente)
            return getCliente(objAtual);
        else if(objAtual instanceof Vendas)
            return getVendas(objAtual);
        else if(objAtual instanceof Vendedor)
            return getVendedor(objAtual);

        return null;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        delegate.open(executionContext);
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        delegate.update(executionContext);
    }

    @Override
    public void close() throws ItemStreamException {
        delegate.close();
    }

    private Cliente getCliente(Object objeto){
        return (Cliente) objeto;
    }

    private Vendas getVendas(Object objeto){
        Vendas vendas = (Vendas) objeto;
        vendas.setVendedor(vendedorMap.get(vendas.getSalesManName()));
        vendas.setItens(getItens(vendas.getListItens()));
        return vendas;
    }

    private List<Item> getItens(String listItens){
        List<Item> itens = new ArrayList<>();
        String patternString1 = "[0-9]*-[0-9]*-(?:[0-9]*\\.?[0-9]*)";

        Pattern pattern = Pattern.compile(patternString1);
        Matcher matcher = pattern.matcher(listItens);

        while(matcher.find()) {
            String[] objeto = matcher.group(0).split("-");
            itens.add(new Item(objeto[0],objeto[1],objeto[2]));
        }
        return itens;
    }

    private Vendedor getVendedor(Object objeto){
        Vendedor vendedor = (Vendedor) objeto;
        vendedorMap.put(vendedor.getName(), vendedor);
        return vendedor;
    }

    private Object peek() throws Exception {
        objAtual = delegate.read();//lê
        return objAtual;
    }

    @Override
    public void setResource(Resource resource) {
        delegate.setResource(resource);
    }
}
