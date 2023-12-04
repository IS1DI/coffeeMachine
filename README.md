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
Переходи по ссылке, устанавливай Docker и переходи к следующему шагу\
Ты до сих пор тут, ну что ж, ладно...

***
* **Шаг 1.2** Docker установлен
Для сборки проекта нужно ввести следующую команду
```shell
./gradlew clean build -x=test
```
Для запуска:
```shell
docker-compose -f ./docker-compose.yml -e ./.env up -d
```

***

