docker run -it --rm --name present-messenger -p 5672:5672 -p 15672:15672 -e RABBITMQ_DEFAULT_USER=present -e RABBITMQ_DEFAULT_PASS=adcc8dde99sen rabbitmq:3.12-management