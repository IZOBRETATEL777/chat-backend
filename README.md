# :Chat! messenger

<img src="docs/сhat-logo.svg" alt="сhat-logo" style="zoom: 10%;" />

------

***Backend for :Chat! messaging service***

***https://ex-chat.tech/***

------

## Setup

#### MySQL database

Run Docker with MySQL:
`docker run -d --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=some_password -e MYSQL_DATABASE=chat-db mysql`

#### Environmental variables

Please, set environmental variables:

For mailbox that is used for sending OTP, activation page, so on.

- `SPRING_MAIL_LOGIN`

- `SPRING_MAIL_PASSWORD `

  Location of your activation page

- `SPRING_MAIL_ACTIVATION_PAGE`

For database. 

- `SPRING_DATASOURCE_URL`

- `SPRING_DATASOURCE_USERNAME`

- `SPRING_DATASOURCE_PASSWORD`

For local machines with Dockerized MySQL like in example username will be root, password will be 123456789 and database URL will be `jdbc:mysql://localhost:3306/chat-db`.

Secrets that used for JWT generation. Key validity defined in milliseconds.

- `JWT_SECRET_KEY`
- `JWT_KEY_VALIDITY`

You can do it in one command before running this application using -D key like:
`gradle -DSPRING_DATASOURCE_USERNAME=root and_so_on bootRun`.

### Usage

#### All requests:

http://some_ip_address:8081/chat/

#### Swagger UI:

http://some_ip_address:8081/chat/swagger-ui/index.html

#### API docs:

http://some_ip_address:8081/chat/v3/api-docs



If you are running this application on local machine, `some_ip_address` can be replaced with `localhost` or `127.0.0.1`:

http://localhost:8081/chat/swagger-ui/index.html
