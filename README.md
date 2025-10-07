# Projeto - Cidades ESGInteligentes

## Como executar localmente com Docker
1. Clone este repositório.
2. Certifique-se de ter o Docker e o Docker Compose instalados.
3. Na raiz do projeto, execute o comando: `docker-compose up --build`.
  (cd esg-monitor
  docker-compose up --build)
4. A aplicação estará disponível em `http://localhost:8080`.

## Pipeline CI/CD
- **Ferramenta:** GitHub Actions.
- **Etapas:**
  1. **Build and Test:** Compila o código Java e executa os testes unitários a cada push ou pull request na branch `main`.
  2. **Deploy to Staging:** Realiza o deploy automático no ambiente de homologação após o sucesso do build.
  3. **Deploy to Production:** Realiza o deploy automático no ambiente de produção somente após um push bem-sucedido na branch `main`.

## Containerização
O `Dockerfile` utiliza uma estratégia de build multi-stage para otimizar o tamanho da imagem final. O primeiro estágio usa uma imagem Maven para compilar a aplicação, e o segundo estágio copia apenas o artefato `.jar` para uma imagem JRE leve.

**Conteúdo do Dockerfile:**
```docker
# Estágio de Build
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests
# Estágio de Produção
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

## Prints do funcionamento
print:
https://prnt.sc/DnJgI4mUhn2r

link do repositório:
https://github.com/thiago742/ci-cd-esg-sprint-docker-compose

## Tecnologias utilizadas
- Java 17
- Spring Boot
- Maven
- Oracle DB
- Docker & Docker Compose
- GitHub Actions

## Checklist de Entrega

| Item | OK |
| :--- | :--: |
| Projeto compactado em .ZIP com estrutura organizada | ☑ |
| Dockerfile funcional | ☑ |
| docker-compose.yml ou arquivos Kubernetes | ☑ |
| Pipeline com etapas de build, teste e deploy | ☑ |
| README.md com instruções e prints | ☑ |
| Documentação técnica com evidências (PDF ou PPT) | ☑ |
| Deploy realizado nos ambientes staging e produção | ☑ |