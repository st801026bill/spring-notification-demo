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
step 1 : pull docker image
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
https://console.twilio.com  
**2. Mailtrap:**  
https://mailtrap.io  
**3. Telegram:**  
https://medium.com/%E7%A8%8B%E5%BC%8F%E8%A3%A1%E6%9C%89%E8%9F%B2/telegram-bot-%E7%AC%AC%E4%B8%80%E6%AC%A1%E9%96%8B%E7%99%BC%E5%B0%B1%E4%B8%8A%E6%89%8B-f8e93a05f26c  
**4. Add application-dev.yml & set parameter**  
spring-notification-producer  
```sql
kafka:
  server: 127.0.0.1:9092
```
spring-notification-server  
```sql
twilio:
  accountSid: ${accountSid}
  authToken: ${authToken}
  sender: ${sender}

mailtrap:
  host: ${host}
  port: ${port}
  userId: ${userId}
  password: ${password}
  starttls: ${starttls}
  auth: ${auth}
  from: ${from}

telegram:
  url: ${url}
  apiToken: ${apiToken}
  id: ${id}

kafka:
  server: 127.0.0.1:9092
```

**C. Demo**  
---
**1. Run Application**    
```spring-notification-producer```  
```spring-notification-server```   
**2. Open Api:**  
```http://localhost:8888/spring-notification-producer/swagger-ui/index.html```  
![image](https://github.com/st801026bill/spring-notification-demo/blob/master/image/openapi.png)
send otp code & verify!
