# 🔗 URL Shortener (Spring Boot)

A simple backend service that converts long URLs into short, unique identifiers and retrieves them when needed.

---

## 🚀 Features

- Generate short URLs for long links
- Retrieve original URL using short URL
- Stores data in database using JPA
- Basic timestamp tracking (createdAt, updatedAt)

---

## 🧠 Architecture

### Layers:

- **Controller** → Handles HTTP requests
- **Service** → Business logic (URL generation, validation)
- **Repository** → Database interaction
- **Entity** → Database schema

---

## 📦 Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- Lombok

---

## 📁 Project Structure

com.url.shortner
│
├── controller
│ └── UrlController.java
│
├── service
│ └── UrlService.java
│
├── repository
│ └── UrlRepository.java
│
├── model
│ └── Url.java

## 🔌 API Endpoints

### 1. Create Short URL

**POST** `/api/`

#### Request Body:
"https://example.com
"


#### Response:
```json
{
  "id": 1,
  "url": "https://example.com",
  "shortUrl": "aB3xYz12",
  "createdAt": "...",
  "updatedAt": "..."
}
