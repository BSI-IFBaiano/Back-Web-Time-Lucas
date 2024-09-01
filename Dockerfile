# Etapa 1: Compilar a aplicação usando Temurin JDK 17 e Maven
FROM eclipse-temurin:17-jdk AS build

# Instala Maven
RUN apt-get update && \
    apt-get install -y wget && \
    wget https://archive.apache.org/dist/maven/maven-3/3.8.6/binaries/apache-maven-3.8.6-bin.tar.gz && \
    tar -xzf apache-maven-3.8.6-bin.tar.gz -C /opt && \
    ln -s /opt/apache-maven-3.8.6 /usr/local/maven && \
    rm apache-maven-3.8.6-bin.tar.gz

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Configura o Maven
ENV MAVEN_HOME /usr/local/maven
ENV PATH $MAVEN_HOME/bin:$PATH

# Copia o arquivo pom.xml e as dependências para o contêiner
COPY pom.xml ./
COPY src ./src

# Executa o Maven para compilar a aplicação
RUN mvn clean package -DskipTests

# Etapa 2: Construir a imagem final com o JAR gerado
FROM eclipse-temurin:17-jdk

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo JAR gerado na etapa de build para a nova imagem
COPY --from=build /app/target/*.jar /app/app.jar

# Adiciona o arquivo .env, se necessário
COPY .env /app/.env

# Comando para executar o aplicativo
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

