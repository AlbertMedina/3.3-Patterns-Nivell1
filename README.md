## 🔐 Escape Room Management System — Project Overview

This project is a **console-based management system** for an Escape Room business.  
It allows administrators to manage escape rooms, rooms, users, tickets, hints, decorations, rewards, certifications and compute inventory value.  
The project demonstrates clean architectural layering with **DAO pattern**, **services**, **JDBC persistence**, and **menu-driven navigation**.

---

## 🚀 Features

✔ Manage Escape Rooms (create, list, update, delete)  
✔ Manage Rooms inside each Escape Room  
✔ Add Hints & Decorations to rooms  
✔ Register Users & manage subscriptions  
✔ Purchase Tickets & compute total revenue  
✔ Assign Rewards & Certifications to users  
✔ Show Full Inventory and calculate its total value  
✔ Observer notifications sent to subscribed users  
✔ Input validation and domain-specific exceptions

---

## 💻 Technologies Used

- **Java 21**
- **Maven**
- **MySQL 8** (Docker)
- **JDBC**
- **JUnit 5**, AssertJ, Mockito
- **PlantUML / Mermaid** for diagrams

---

## ▶️ Running the Project (Local)

### 1️⃣ Requirements

- JDK 21  
- Maven  
- Docker  
- MySQL running with the provided compose file  

### 2️⃣ Start the database

```bash
cd db
docker-compose up -d
```

###3️⃣ Run the application

```bash

mvn clean install
```
---

## UML Diagram 

![WhatsApp Image 2025-12-02 at 11 34 31](https://github.com/user-attachments/assets/b86f4c32-483c-4ec8-b2e5-6ad0dcd9bf30)



---

##📦Project Structure

src/main/java
├── escapeRoom          → Escape room domain + DAO + service
├── room                → Rooms, difficulties, DAO, service
├── user                → Users + Subscriber (Observer)
├── hint                → Hint entities, DAO, service
├── decoration          → Decorations, DAO, service
├── ticket              → Tickets, DAO, service
├── reward              → Rewards, DAO, service
├── certification       → Certifications, DAO, service
├── Inventory           → Aggregated inventory logic
├── menu                → Interactive console menus (Template Method)
├── input               → Input handling utilities
├── db                  → JDBC connection (Singleton) + Generic DAO
└── exceptions          → Domain + validation exceptions

src/test/java
├── DaoTests            → CRUD tests for DAO implementations
└── ServicesTest        → Mockito tests for business logic
