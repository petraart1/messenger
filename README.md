# Messenger Project

Минимальная версия мессенджера на микросервисной архитектуре.

## 📖 О проекте
На данный момент реализованы два сервиса:
- **Auth Service** – регистрация и авторизация пользователей.
- **Chat Service** – управление чатами и участниками.
- **Message Service** – отправка сообщений.
- **User Service** – информация о пользователях.

Проект находится на стадии **MVP**:
- Есть базовая аутентификация через JWT.
- Чаты можно создавать, получать, добавлять и удалять участников.
- Можно отправлять сообщения
- Подняты docker-контейнеры для каждого сервиса.

## ⚙️ Подготовка
Перед запуском необходимо создать файл `.env` в корне проекта.  
Пример содержимого:

```env
POSTGRES_USER=user
POSTGRES_PASSWORD=password
POSTGRES_DB=name

DB_PORT=1234

AUTH_DB_CONTAINER_NAME=auth-db
CHAT_DB_CONTAINER_NAME=chat-db
MESSAGE_DB_CONTAINER_NAME=message-db
USER_DB_CONTAINER_NAME=user-db

JWT_SECRET=secret_key
```


## 🚀 Запуск локально

### 1. Склонировать репозиторий
```bash
git clone https://github.com/your-repo/messenger.git
cd messenger
```

### 2. Запустить сервисы
```bash
make up
```

### 3. Остановить сервисы
```bash
make down
```

### 4. Полное удаление (вместе с томами)
```bash
make delete
```

### 5. Подключение к базам данных
- Auth DB → `make psql-auth-db`
- Chat DB → `make psql-chat-db`
- Message DB → `make psql-message-db`
- User DB → `make psql-user-db`

### 6. Запуск тестов
```bash
make test
```

## 🌐 Доступ к сервисам
- Auth Service → `http://localhost:8080`
- Chat Service → `http://localhost:8081`
- Message Service → `http://localhost:8082`
- User Service → `http://localhost:8083`

## 📦 Структура проекта

```
messenger/
├─ authService/
├─ chatService/
├─ messageService/
├─ userService/
├─ compose.yaml
├─ Makefile
└─ .env
```

## 📄 Документация

Документация каждого сервиса доступна в формате swagger:

- [Documentation](documentation.yaml)
