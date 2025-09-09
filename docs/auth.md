# 📑 Auth Service API

Сервис отвечает за регистрацию, авторизацию и управление токенами.

Базовый URL:
```
http://localhost:8081/api/v1/auth
```

---

## 🔹 POST `/register`

Регистрация нового пользователя.

### Request Body
```json
{
  "username": "john",
  "email": "john@example.com",
  "password": "securePass123"
}
```

### Response `201 Created`
```json
{
  "id": 1,
  "username": "john",
  "email": "john@example.com",
  "created_at": "2025-09-05T15:22:11"
}
```

### Response `400 Bad Request`
```json
{
  "message": "User already exists"
}
```

---

## 🔹 POST `/login`

Аутентификация пользователя (по username **или** email).

### Request Body (username)
```json
{
  "username": "john",
  "password": "securePass123"
}
```

### Request Body (email)
```json
{
  "email": "john@example.com",
  "password": "securePass123"
}
```

### Response `200 OK`
```json
{
  "id": 1,
  "username": "john",
  "email": "john@example.com",
  "accessToken": "jwt_token_here",
  "tokenType": "Bearer"
}
```

### Response `401 Unauthorized`
```json
{
  "message": "Bad credentials"
}
```

---

## 🔹 POST `/logout`

🚧 **Пока не реализован.**  
Планируется:
- Принять `refreshToken` или `accessToken`.
- Добавить токен в blacklist.

Пример тела:
```json
{
  "refreshToken": "some_token"
}
```

---

## 🔹 POST `/refresh`

🚧 **Пока не реализован.**  
Планируется:
- Обновление `accessToken` с использованием `refreshToken`.

Пример тела:
```json
{
  "refreshToken": "some_token"
}
```

---

## 🔹 Ошибки

Все ошибки возвращаются в формате:
```json
{
  "message": "Описание ошибки"
}
```

---

## 📌 Статусы реализации
- ✅ Регистрация
- ✅ Авторизация (логин)
- ⏳ Логаут
- ⏳ Обновление токена
