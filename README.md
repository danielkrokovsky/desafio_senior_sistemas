# Desafio Senior Sistemas (ERPx) - Daniel Krokovsky

# Passos para configuração do projeto

1. - Deve-se ter instalado na máquina os seguintes programas:  

        ```Git, Maven, Docker, Docker Compose```  


2. - Deve-se baixar o projeto do repositório git utilizando no terminal comando (caso tenha o git instalado na máquina)  

        ```https://github.com/danielkrokovsky/desafio_senior_sistemas.git```  

ou é possível fazer o download do projeto diretamente pelo site  

3. - Acessar pelo terminal a pasta onde se encontra o projeto (BackEnd), e executar o comando ```docker-compose up```. Nesse momento começará o download e instalação do banco de dados Postgresql.  

4. - Após o serviço do postgres inicializar, deve-se executar o comando abaixo, para baixar as depenências, e criar as classes para as querydsl.  

        
        ```mvn clean package -DskipTests``` 
        

# Passos para execução do projeto


1. - Deve-se acessar a pasta **desafio_senior_sistemas** e executar o comando para executar o backend. Uma lista de Produtos serão cadastradas automaticamentes.

        
        ```mvn spring-boot:run```


2. - Para cadastro de novos cadastros pode utilizar o **Postman**.


3. - Para cadastro de novos produtos, segue o exemplo de json:

```
{
 "valor":1.5,
 "isServico":true,
 "ativo":true
}
```


4. - Para cadastro de novos Pedidos, segue o exemplo de json:

```
{
   "ativo":true,	
   "produtos":[
      {
         "id":1,
         "nome":"Smart TV 32´ Samsung, 2 HDMI, 1 USB, Wi-Fi - LH32B",
         "valor":1156.84,
         "servico":false,
         "ativo":true
      },
      {
         "id":3,
         "nome":"iPhone 11 Branco, 64GB - MWLU2",
         "valor":4599,
         "servico":false,
         "ativo":true
      },
      {
         "id":4,
         "nome":"Monitor LG LED 29´ Ultrawide, IPS, HDMI, FreeSync",
         "valor":1799.9,
         "servico":false,
         "ativo":true
      }
   ]
}
```


5. - Para validar adição um produto desativado em um pedido, segue o exemplo de json:

```
{
   "ativo":true,
   "produtos":[
      {
         "id":2,
         "nome":"Smart TV 32´ Samsung, 2 HDMI, 1 USB, Wi-Fi - LH32B",
         "valor":1156.84,
         "servico":false,
         "ativo":true
      }
   ]
}
```

6. - Para validar aplicação de desconto em um pedido fechado, segue o exemplo de json:

```
{
   "ativo": false,
   "produtos":[
      {
         "id":1,
         "nome":"Smart TV 32´ Samsung, 2 HDMI, 1 USB, Wi-Fi - LH32B",
         "valor":1156.84,
         "servico":false,
         "ativo":true
      }
   ]
}
```

7. - Para validar remoção de um produto/serviço associado a algum pedido, segue o exemplo de json:

```
    Deve-se realidar uma requisição DELETE passando o id do produto

        http://localhost:9001/api/produto/3

```

8. - Para validar percentual de desconto aplicato apenas ao produtos, segue o exemplo de json:

```
{
	"ativo":true,
	
   "produtos":[
      {
        "id": 4,
        "nome": "Monitor LG LED 29´ Ultrawide, IPS, HDMI, FreeSync",
        "valor": 1799.9,
        "servico": false,
        "ativo": true
    },
    {
        "id": 6,
        "nome": "Mouse Gamer Redragon 10000DPI Chroma Cobra M711",
        "valor": 270.47,
        "servico": false,
        "ativo": true
    },
    {
        "id": 7,
        "nome": "Instalação de internet",
        "valor": 250,
        "servico": true,
        "ativo": true
    }
   ]
}

```

9. - Para validar filtragem usando QueryDSL, segue o exemplo do comando:

Realizar uma requisição GET, passando os atributos da classe:

```
http://localhost:9001/api/produto?ativo=true

```
