# Simple Personal Banking API

## Описание
Простое приложение для управления банковскими счетами, включающее функции:
- Регистрация и авторизация пользователей (с использованием токенов).
- Просмотр баланса.
- Переводы между счетами.
- Пополнение баланса.

## Технологии
- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- OpenAPI
- Maven

## Запуск проекта
1. Склонируйте репозиторий:
   ```bash
   git clone https://github.com/DyahDemetra/SimpleBankingApi.git
   cd SimpleBankingApi
   ```

2. Настройте базу данных (PostgreSQL):
    - Создайте базу данных `banking`.
    - Настройте файл `application.propertes` в папке `src/main/resources`:
      ```yaml
      spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
      spring.datasource.username=<ваш_логин>
      spring.datasource.password=<ваш_пароль>
      spring.jpa.hibernate.ddl-auto=update
      ```

3. Соберите и запустите проект:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## Тестирование
Запустите тесты с помощью Maven:
```bash
mvn test
```

## Эндпоинты API
### Регистрация
**POST** `/api/users/register`  
**Тело запроса:**
```json
{
  "username": "user",
  "password": "password"
}
```

### Авторизация
**POST** `/api/users/login`  
**Тело запроса:**
```json
{
  "username": "user",
  "password": "password"
}
```
**Ответ:**
```json
{
  "token": "ваш_токен"
}
```

### Просмотр баланса
**GET** `/api/accounts/balance`  
**Заголовок:**
```
Authorization: ваш_токен
```

### Перевод средств
**POST** `/api/transactions/transfer`  
**Тело запроса:**
```json
{
  "fromAccountId": 1,
  "toAccountId": 2,
  "amount": 50.0
}
```

### Пополнение баланса
**POST** `/api/accounts/{id}/balance`  
**Тело запроса:**
```json
50
```
