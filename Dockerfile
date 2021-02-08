#builder
FROM maven:3-jdk-8 AS builder
WORKDIR /project
COPY . .
RUN mvn package


FROM openjdk:8-jdk-alpine

WORKDIR /app
#viene copiato l'artefatto costruito dal builder nell'immagine docker
COPY --from=builder /project/target/fake-products-0.0.1-SNAPSHOT.jar ./fake-products.jar
ENTRYPOINT ["/bin/sh", "-c"]
CMD [ "java -jar fake-products.jar" ]