# South System
Projeto que lê arquivos .dat, processa as informações e salva em formato .dat.

## Bibliotecas usadas
* MySQL - v8.0.21
* Log4J2
* Lombok
* Spring Web

### Como usar o projeto
1. Criar um database no MySQL spring_batch ou outro nome.
2. Adicionar credenciais do banco no application.properties.
3. Criar pasta no caminho %homepath% como o nome data.
4. Criar subpastas no diretório data com o nome _in_ e _out_.
    - _in_: adicionar arquivos no formato .dat.
    - _out_: diretótio onde vai ser salvo os arquivos com o tratamento.

### Formato dos arquivos para serem lidos
* Dados do vendedor
Os dados do vendedor têm o formato id 001 e a linha terá o seguinte formato: 
001çCPFçNameçSalary

* Dados do cliente
Os dados do cliente têm o formato id 002 e a linha terá o seguinte formato: 
002çCNPJçNameçBusiness Area

* Dados de vendas
Os dados de vendas têm o formato id 003. Dentro da linha de vendas, existe a lista de itens, que é envolto por colchetes [].
A linha terá o seguinte formato: 
003çSaleIDç[Item ID-Item Quantity-Item Price]çSalesman name

-------------------------------------------------------------------------------------------------------------------

## Checklist do teste

### Análise de dados
- [x] Sistema deve ler dados do diretório padrão, localizado em %HOMEPATH%/data/in.
- [x] Sistema deve ler somente arquivos .dat.
- [x] Depois de processar todos os arquivos dentro do diretório padrão de entrada, o sistema deve criar um arquivo dentro do diretório de saída padrão, localizado em %HOMEPATH%/data/out.
- [ ] Nome do arquivo deve seguir o padrão, {flat_file_name} .done.dat.
### Conteúdo do arquivo de saída deve resumir os seguintes dados:
- [x] Quantidade de clientes no arquivo de entrada
- [x] Quantidade de vendedor no arquivo de entrada
- [x] ID da venda mais cara
- [x] O pior vendedor
- [ ] O sistema deve estar funcionando o tempo todo.
- [x] Todos os arquivos novos estar disponível, tudo deve ser executado
