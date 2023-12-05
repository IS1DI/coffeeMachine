# Сервис для удаленного управления кофе-машиной
***
### Стэк
* **Build-tool** - Gradle 8.5 (wrapper)
* **Spring** - Boot 3.2
* **Docker**
* **Docker-compose**
* **Java** - 21
* **Postgresql**
* **Virtual Threads**
***
### Quick start
* **Шаг 1.1** Docker не установлен\
Переходи по [ссылке](https://www.docker.com/products/docker-desktop/), устанавливай Docker и переходи к следующему шагу\

***
* **Шаг 1.2** Docker установлен
Для сборки проекта нужно ввести следующую команду
```shell
./gradlew clean build -x=test
```
Для запуска:
```shell
docker-compose -f ./docker-compose.yml up -d
```
Или
Можно запустить docker compose с уже собранным образом
```shell
docker-compose -f ./docker-compose-builded.yml up -d
```
[swagger url](http://localhost/swagger-ui/index.html)
***

