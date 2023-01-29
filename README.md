# Flash-Sale-System-backend

- Designed the “Flash Sale System” project. 
- Improving performance from 500 QPS to 10,000 QPS by employing Redis and RocketMQ.

- Functions: commodity; promotion -> order -> pay;
- Adopted DDD(Domain Driving Design): api - application - domain - infrastructure
    - Split order service into promotion and order microservices according to DDD(Domain Driving Design) principles.
- high-concurrency:
    - Implemented cache preheating for active promotions. Completed lock and revert inventory service through Redis and Lua script.
    - Peak-load shifting order service stress by asynchronously create order using RocketMQ. Implemented "Pay-Check&Cancel" by consuming delay message. And optimized optimistic-lock of updating inventory in MySQL database.
- test: Postman; Jmeter
- workflow: JIRA (canary mode)


## Version1

1. MySQL lock、Pessimistic Locking、 Optimistic Locking，to prevent oversell
2. Database Event - Transaction， SpringBoot event management
3. PostMan: API-testing tool
4. Jmeter: concurrency testing tool

## Version2

1. Redis + Lua script to achieve event,  Redis cache preheat
2. RocketMQ

    Idempotency, delay message, etc.
    
3. Junit5， Mockito: unit test

## Version3 (WIP)

1. Distrubuted Transaction
2. Redis: distributed lock
3. SnoFlake to generate OrderId
4. Microservice find and register center: Consul，Feign declare， SpringCloud-Gateway

## Tech Stack
### Languages: 
Java; SQL; JavaScript, HTML, CSS, Ajax, Axios; 

### Framework & Database: 
Spring Boot, Spring Cloud, Restful API; Mircroservice, Consul, Sentinel(, Hystrix) ; JOOQ, (JPA, Hibernate, Mybatis); MySQL, Redis, Lua, RocketMQ, Eventual Consistency, Idempotent Message; 

### Tools & Others: 
JIRA, Confluence, Bitbucket, Git/Github; Junit5, Mockito, Jmeter; Maven, Postman, MysqlWorkbench, RedisInsight, RocketMQ Dashboard; OOD, OOP, SOLID, Design Patterns, DDD; Agile, XML, JSON.

