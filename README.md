# Rest Api Star Wars
Tecnologias utilizadas:

*Java 8
*Spring Boot
*MongoDB
*IDE Eclipse 
*Postman


Informações Importantes para uso da API:
- Para utilizar a API é necessário configurar o servidor do MongoDB.

Arquivo de configuração application.properties do Spring:
spring.data.mongodb.database=restapi


EndPoints da API :
- Listar todos os planetas Salvos : (GET) http://localhost:8080/planets

- Salvar um planeta : (POST) http://localhost:8080/planets
    Informando um JSON:
    
     {
        "name": "Alderaan",
        "climate": "temperate",
        "terrain": "grasslands, mountains"
    }
  
- Buscar um planeta por id : (GET) http://localhost:8080/planets/{id}
- Busca um planeta por nome : (GET) http://localhost:8080/planets/name/{nome}
- Remover planeta : (DELETE) http://localhost:8080/planets/{id}


Consumindo a API de terceiro: https://swapi.co/ :

Ao salvar planeta, ele se comunica com API do Star Wars, obtem a quantidade de aparições em filmes e finaliza o processo.

