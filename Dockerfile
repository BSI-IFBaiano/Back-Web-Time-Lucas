# Use a imagem base do OpenJDK 21
FROM openjdk:21-jdk

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo JAR do aplicativo para o diretório de trabalho
COPY target/your-app.jar /app/app.jar

# Comando para executar o aplicativo
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
