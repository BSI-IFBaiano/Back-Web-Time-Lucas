# Etapa 1: Compilar a aplicação usando Maven com JDK 17 (que é a versão LTS mais próxima)
FROM maven:3.8.6-openjdk-17 AS build

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo pom.xml e as dependências para o contêiner
COPY pom.xml ./
COPY src ./src

# Executa o Maven para compilar a aplicação
RUN mvn clean package -DskipTests

# Etapa 2: Construir a imagem final com o JAR gerado
FROM openjdk:21-jdk

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo JAR gerado na etapa de build para a nova imagem
COPY --from=build /app/target/*.jar /app/app.jar

# Comando para executar o aplicativo
ENTRYPOINT ["java", "-jar", "/app/app.jar"]


