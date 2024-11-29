### 1. Setup RabbitMQ image in docker
> first install docker into your local
> 
> docker pull ***rabbitmq:4.0.4-management***
### 2. Run RabbitMQ docker image in cmd prompt
>docker run --rm -it -p 15672:15672 -p 5672:5672 rabbitmq:4.0.4-management


