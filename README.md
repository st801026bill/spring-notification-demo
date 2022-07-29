# spring-notification-demo
* use docker & kafka to send notification
* support twilio、mailtrap、telegram notification sending
* use redis to verify otp code

**A. Enviroment Setting**  
---
**1. Docker Redis**  
```sql
# https://marcus116.blogspot.com/2019/02/how-to-run-redis-in-docker.html
# 1. pull docker image
> docker pull redis

# 2. run redis container
> docker run --name redis-notification -p 6379:6379 -d redis

# 3. exec redis cli
> docker ps
> docker exec -it e819 bash
> redis-cli
> Ping //Pong
```

**2. Docker Kafka**  
a. Pull Image
```sql
# docker image pull
> docker pull wurstmeister/kafka
> docker pull wurstmeister/zookeeper
```  
b. Create docker-compose.yml
```sql
version: '3'
services:
  zookeeper:
    image: wurstmeister/zookeeper
    ports:
    - "2181:2181"
  kafka:
    image: wurstmeister/kafka
    ports:
    - "9092:9092"
    environment:
      KAFKA_ADVERTISED_HOST_NAME: 127.0.0.1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_AUTO_CREATE_TOPICS_ENABLE: 'false'
```
c. Run Redis & Docker-Compose:
```sql
#1. run docker-compose
> docker-compose up -d
```

**B. Notification Service**  
---
**1. Twilio:**  

**2. Mailtrap:**  

**3. Telegram:**  


**C. Demo**
---

**2. Open Api:**  
* SMS: 
* Mail:
* Telegram:
