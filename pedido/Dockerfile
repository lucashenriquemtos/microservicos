# Use uma imagem base do Maven para o build do projeto
FROM maven:3.8.5-openjdk-17 AS build

# Diretório de trabalho dentro do container
WORKDIR /app

# Copie o arquivo pom.xml e o diretório src para o container
COPY pom.xml .
COPY src ./src

# Execute o comando Maven para construir o projeto
RUN mvn clean package -DskipTests

# Use uma imagem base do OpenJDK JRE para rodar o aplicativo
FROM openjdk:17-jdk-slim

# Diretório de trabalho dentro do container
WORKDIR /app

# Copie o JAR construído do estágio anterior
COPY --from=build /app/target/pedido-0.0.1-SNAPSHOT.jar /app/pedido.jar

# Expõe a porta que o serviço Pedido irá rodar
EXPOSE 8082

# Define o comando para rodar o aplicativo
CMD ["java", "-jar", "pedido.jar"]
