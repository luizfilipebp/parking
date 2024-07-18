# Parking

Parking é uma api Spring Boot para gestão de estacionamentos de uma cidade.


## Features



## Tecnologias usadas

- Spring Boot
- Spring Web
- JPA
- H2
- Lombok

## Requisitos

- JDK 17 ou superior
- Maven

## Como rodar

1. Clonar repositório:

    ```bash
    git clone 
    ```

2. Ter a porta 8080 default disponível ou alterar no `application.properties`:

    ```properties
    server.port=8080
    ```

3. Para rodar sem ide:

    ```bash
    cd pastaDoProjeto
    mvn spring-boot:run
    ```

4. Realizar chamadas get disponíveis, 

5. Curls dos endpoints disponíveis para importação no postman:

    ```curl 1
   curl --location 'localhost:8080/endpoint/'
    ```
