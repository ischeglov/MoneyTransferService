# MoneyTransferService
Курсовой проект «Сервис перевода денег»

## Описание проекта
REST-сервис предоставляет интерфейс для перевода денег с одной карты на другую по заранее описанной спецификации.
Заранее подготовленное веб-приложение (FRONT) подключается к разработанному сервису без доработок и использует его функционал для перевода денег.

## Описание реализации

- Приложение разработано с использованием Spring Boot;
- Использован сборщик пакетов maven;
- Для запуска используется Docker, Docker-Compose;
- Код размещен на github;
- Код покрыт юнит-тестами с использованием mockito;
- Добавлены интеграционные тесты с использованием testcontainers.
- Логгирование всех операция производится в файл 

## Запуск приложения

- Скачать [FRONT](https://github.com/serp-ya/card-transfer), выполнить инструкции из него

Для запуска приложения: 
- необходимо склонировать репозиторий, подключить приложение Docker, 
запустить файл docker-compose
- либо найти файл MoneyTransferServiceApplication и запустить его





