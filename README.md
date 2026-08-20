URL Shortener API
=================

Features
--------
- Create short URLs
- Retrieve URLs
- Update URLs
- Delete URLs
- Access statistics
- Redirect support

Tech Stack
----------
Java
Spring Boot
Spring Data MongoDB
MongoDB
Maven

API
---
POST   /shorten
GET    /shorten/{shortCode}
PUT    /shorten/{shortCode}
DELETE /shorten/{shortCode}
GET    /shorten/{shortCode}/stats
GET    /{shortCode}

Running Locally
---------------

1. Start MongoDB
2. Set MONGODB_URI
3. Run ./mvnw spring-boot:run
4. Open Swagger UI

Swagger
-------
http://localhost:8080/swagger-ui.html