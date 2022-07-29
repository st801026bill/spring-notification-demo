# spring-notification-demo
* use docker & kafka to send notification
* support twilio、mailtrap、telegram notification sending
* use redis to verify otp code

**A. Enviroment Setting**  
---
**1. Docker Redis**  
https://marcus116.blogspot.com/2019/02/how-to-run-redis-in-docker.html  
step 1 : pull docker image
```sql
> docker pull redis
```
step 2 : run redis container
```sql
> docker run --name redis-notification -p 6379:6379 -d redis
```
step 3 : exec redis cli
```sql
> docker ps
> docker exec -it e819 bash
> redis-cli
> Ping //Pong
```

**2. Docker Kafka**  
step 1 : Pull Image
```sql
# docker image pull
> docker pull wurstmeister/kafka
> docker pull wurstmeister/zookeeper
```  
step 2 : Create docker-compose.yml
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
step 3 : Run Docker-Compose:
```sql
# run docker-compose
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
* Add application-dev.yml & set parameter
```sql
twilio:
  accountSid: 
  authToken: 
  sender: 

mailtrap:
  host: 
  port: 
  userId: 
  password: 
  starttls: 
  auth: 
  from: 

telegram:
  url: 
  apiToken: 
  id: 

kafka:
  server: 127.0.0.1:9092
```
