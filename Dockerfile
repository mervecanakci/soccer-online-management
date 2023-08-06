FROM maven:3.9.3-amazoncorretto-17
WORKDIR /app
COPY . /app/
RUN mvn clean package -DskipTests
CMD ["mvn","spring-boot:run"]