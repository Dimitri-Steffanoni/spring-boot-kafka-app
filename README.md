# A Little Guide to Integrating Spring Boot and Kafka

Hey! I wanted to try and put together a small guide on how to make a Spring Boot application talk to Apache Kafka. Kafka is a bit complex, but it can be useful for making applications work better.

To follow along, I think it helps to have a basic idea of Java and Spring Boot. Then, you'll need to have the Java Development Kit (JDK) installed and an IDE like IntelliJ or Eclipse. Lastly, you need to have Apache Kafka installed and running on your computer.

First off, you need to create a new Spring Boot project and add two important "pieces": `spring-kafka` and `spring-boot-starter-web`. You can add these using a website like Spring Initializr or by editing the `pom.xml` (for Maven) or `build.gradle` (for Gradle) files.

Then, you need to tell Spring Boot where to find Kafka and how to "group" the things it reads. This is done by editing the `application.properties` file and adding these lines:

spring.kafka.bootstrap-servers=localhost:9092

spring.kafka.consumer.group-id=my-group-id


To send messages to Kafka, I tried to create a "producer." There are some configuration files and then another file that takes the message and sends it to Kafka.

Similarly, to read messages from Kafka, I created a "consumer." Here too, there are configurations and a file that "listens" for messages arriving from Kafka.

To see if everything works, I created a simple web page. If you send a "POST" request to this address: `http://localhost:8080/send?message=Hello_Kafka`, the message should reach Kafka and then be read by the consumer, which will show it in the console.

I hope this little guide can be helpful to someone who's just starting to see how this Spring Boot and Kafka thing works!

**A little tip (if you use Docker):** To get a Kafka instance running quickly, I found this `docker-compose.yml` file that seems to work:

```yaml
version: '2'
services:
  zookeeper:
    image: confluentinc/cp-zookeeper:7.4.4
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
      ZOOKEEPER_TICK_TIME: 2000
    ports:
      - 22181:2181
  kafka:
    image: confluentinc/cp-kafka:7.4.4
    depends_on:
      - zookeeper
    ports:
      - 29092:29092
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka:9092,PLAINTEXT_HOST://localhost:29092
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT
      KAFKA_INTER_BROKER_LISTENER_NAME: PLAINTEXT
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
