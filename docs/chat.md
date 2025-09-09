# 📩 Message Service API

Сервис сообщений — отвечает за отправку и получение сообщений в рамках чатов.

Базовый URL:
```
/api/v1/message
```

---

## 🔹 Эндпоинты

### 1. Отправка сообщения
**POST** `/api/v1/message`

Отправка нового сообщения в чат.

#### Request
```json
{
  "chatId": 1,
  "content": "Привет! Как дела?"
}
```

#### Response `200 OK`
```json
{
  "id": 101,
  "chat_id": 1,
  "sender_id": 5,
  "content": "Привет! Как дела?",
  "created_at": "2025-09-05T14:23:11"
}
```

---

### 2. Получение сообщений чата
**GET** `/api/v1/message/{chat_id}`

Возвращает список сообщений по `chat_id`.

#### Request
```
GET /api/v1/message/1
```

#### Response `200 OK`
```json
[
  {
    "id": 100,
    "chat_id": 1,
    "sender_id": 5,
    "content": "Всем привет!",
    "created_at": "2025-09-05T13:59:42"
  },
  {
    "id": 101,
    "chat_id": 1,
    "sender_id": 6,
    "content": "Привет! Как дела?",
    "created_at": "2025-09-05T14:23:11"
  }
]
```

---

## 🔹 DTO

### `MessageCreateDto`
```json
{
  "chatId": 1,
  "content": "Привет! Как дела?"
}
```

### `MessageDto`
```json
{
  "id": 101,
  "chat_id": 1,
  "sender_id": 5,
  "content": "Привет! Как дела?",
  "created_at": "2025-09-05T14:23:11"
}
```
