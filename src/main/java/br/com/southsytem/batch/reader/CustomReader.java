package br.com.southsytem.batch.reader;

import br.com.southsytem.model.*;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
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

public class CustomReader implements ItemStreamReader<ModelWriter>, ResourceAwareItemReaderItemStream<ModelWriter> {
    private Object objAtual;
    private FlatFileItemReader<Object> delegate;
    private Map<String,Vendedor> vendedorMap = new HashMap<>();
    private String SALEMOSTEXPENSIVE = "saleMostExpensive";
    private String WORSTSELLER = "worstSeller";

    public CustomReader(FlatFileItemReader<Object> delegate) {
        this.delegate = delegate;
    }

    @Override
    public ModelWriter read() throws Exception {
        if(objAtual == null)
            objAtual = delegate.read();

        ModelWriter modelWriter;
        List<Cliente> clientes = new ArrayList<>();
        List<Vendas> vendas = new ArrayList<>();
        List<Vendedor> vendedores = new ArrayList<>();

        while(objAtual != null){
            if(objAtual instanceof Cliente)
                clientes.add(getCliente(objAtual));
            else if(objAtual instanceof Vendas)
                vendas.add(getVendas(objAtual));
            else if(objAtual instanceof Vendedor)
                vendedores.add(getVendedor(objAtual));
            peek();
        }

        modelWriter = getModel(clientes.size(), vendedores.size(), vendas);
        if(modelWriter.getWorstSeller() != null)
            return modelWriter;

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

    @Override
    public void setResource(Resource resource) {
        delegate.setResource(resource);
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
            itens.add(new Item(Integer.parseInt(objeto[0]),Integer.parseInt(objeto[1]),Double.parseDouble(objeto[2])));
        }
        return itens;
    }

    private ModelWriter getModel(Integer clientsQtde, Integer vendedorQtde, List<Vendas> vendas){
        ModelWriter modelWriter = new ModelWriter();
        modelWriter.setClientsQtde(clientsQtde);
        modelWriter.setVendedorQtde(vendedorQtde);

        Map<String,Object> mapper = getSaleMostExpensive(vendas);

        modelWriter.setSaleMostExpensive((Integer) mapper.get(SALEMOSTEXPENSIVE));
        modelWriter.setWorstSeller((Vendedor) mapper.get(WORSTSELLER));
        return modelWriter;
    }

    private Map<String, Object> getSaleMostExpensive(List<Vendas> vendas){
        Map<String, Object> mapper = new HashMap<>();

        int vendaId = 0;
        double maiorVenda = 0;
        Vendedor piorVendendor = null;

        for(Vendas venda : vendas){
            double vendaAtual = 0;
            for(Item item : venda.getItens()){
                vendaAtual += item.getPrice();
            }

            if(vendaAtual > maiorVenda){
                maiorVenda = vendaAtual;
                vendaId = venda.getSaleId();
            }else{
                piorVendendor = venda.getVendedor();
            }
        }

        mapper.put(SALEMOSTEXPENSIVE, vendaId);
        mapper.put(WORSTSELLER, piorVendendor);

        return mapper;
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


}
