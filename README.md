# Java & Spring Mastery Journey   
A personal roadmap following a 6-month structured learning path to master Java, Spring Boot, microservices, and system design.
This repository documents **every step** of my journey — code, notes, exercises, challenges, and mini-projects.
---

## 📅 Learning Roadmap Progress
### Weeks 1–4 — Java Fundamentals & Tooling  
- [x] Java OOP  
- [x] Collections Framework  
- [x] Streams & Optionals  
- [x] Git, Maven  
- [x] SQL basics + JDBC  
- [ ] JUnit 5 testing 

### Weeks 5–8 — Spring Boot & REST  
- [x] Spring Boot fundamentals  
- [x] Validation + DTOs  
- [x] Spring Data JPA
- [ ] Spring Security
- [ ] Spring batch
- [ ] Spring AI
- [ ] Testing REST APIs  
- [ ] OpenAPI documentation  

### Weeks 9–12 — Microservices Architecture  
- [ ] Service decomposition  
- [ ] Feign clients  
- [ ] Resilience4j  
- [ ] Distributed transactions (SAGA)

### Weeks 13–16 — Messaging & Data  
- [ ] Kafka fundamentals  
- [ ] Kafka Streams  
- [ ] CQRS patterns  

### Weeks 17–20 — Observability & Reliability  
- [ ] Prometheus metrics  
- [ ] Grafana dashboards  
- [ ] OpenTelemetry tracing  

### Weeks 21–26 — Containers, Security & CI/CD  
- [ ] Docker & Kubernetes  
- [ ] OAuth2, JWT  
- [ ] GitHub Actions full pipeline  
- [ ] Capstone microservices project  

---

##  Week-by-Week Notes

 
- ### **Month 1 — Java Fundamentals & Tooling**
- 
### **Week 1 — Java Fundamentals**
- Practicing Streams, Optionals
- Exercises implemented
- 
####  Deliverables
- CLI-based Java application connected to PostgreSQL
- Database versioning using Flyway migrations
- Connection pooling using HikariCP
- Maven project structure
- Unit testing with JUnit 5

####  Project Location
- month1_java_fundamentals/deliverables1_cli_db_App/cli-db-app
  
####  Technical Highlights
- Flyway migration:
  - `V1__create_users_table.sql`
- Custom datasource configuration
- Clean separation between configuration, application logic, and tests
- Tests validating datasource and application startup

####  Concepts Practiced
- Java Streams & Optionals
- JDBC fundamentals
- Maven dependency management
- HikariCP config + flyway migration config
- Git commit discipline
- Writing testable Java code

####  Notes
This week focused on **foundational correctness over complexity** — building a small but realistic Java application using professional tooling commonly used in production systems.
---

### **Month 2 — Next Phase — Spring Boot & REST APIs (Step 2)**

Starting **Step 2** of the roadmap, the focus shifts from core Java fundamentals to **Spring / Spring Boot and backend application development**.

==> Due to Work situation and time complexity this phase could take longer than 1 month

###  Objectives
- Understand Spring/Spring Boot fundamentals and auto-configuration
- Build RESTful APIs
- Apply validation and DTO patterns
- Use Spring Data JPA for persistence
- Write integration and controller tests

###  Planned Deliverables
- Spring Boot REST API project
- CRUD endpoints with validation
- Mysql/Postgresql integration using Spring Data JPA
- OpenAPI / Swagger documentation / Postman for end point test
- Integration tests using Testcontainers (later phase)

This phase builds directly on the foundations from Month 1 and moves toward **production-ready backend services**.

### 🏛️ Library System Lab: Multi-Database Architecture
A backend library management system demonstrating **polyglot persistence** with two databases working side by side.

####  Project Location
- month2_spring_fundamentals/deliverable2_library_system
------------
#### 🎯 **Project Purpose**
This lab was designed to consolidate practical knowledge of Spring persistence technologies by building a real system with **two databases**, each serving a distinct purpose:
- **MySQL** → Transactional database (JPA) for domain operations
- **H2** → In-memory reporting database (JDBC) for analytics

No UI, no security — just pure data access, transactions, and architecture.
------------------------------------------------------
#### ✅ **Learning Objectives Achieved**

| Concept | Implementation |
|--------|----------------|
| ✅ Multiple DataSources | Configured MySQL + H2 in one Spring Boot app |
| ✅ Spring Data JPA | Entities, repositories, relationships |
| ✅ JDBC + explicit SQL | Reporting queries against H2 |
| ✅ Hibernate mappings | `@OneToMany`, `@ManyToOne`, fetch strategies, cascade |
| ✅ Transaction management | `@Transactional` across two databases |
| ✅ DAO vs Repository | JPA repositories for entities, JDBC DAO for reports |
| ✅ Bidirectional relationships | Helper methods to keep both sides in sync |
| ✅ Reporting cache | H2 auto-populated from MySQL on startup |
--------------------------------------------------------------------------------
#### 🏗️ **Architecture Overview**
| Layer | Technology | Database | Purpose | Key Components |
|-------|-----------|----------|---------|----------------|
| **PRESENTATION** | Spring MVC | - | REST API endpoints | `@RestController` <br> DTOs (future) <br> HTTP request/response |
| ↓ | | | |
| **SERVICE** | Spring `@Service` | - | Business logic <br> Transaction boundaries | `LoanService` <br> `ReportService` <br> `DataSyncService` <br> `@Transactional` |
| ↓ | | | |
| **DATA ACCESS** | **Spring Data JPA** | **MySQL** | **Write Operations** <br> Entity management <br> CRUD operations <br> Relationships | `AuthorRepository` <br> `BookRepository` <br> `MemberRepository` <br> `LoanRepository` |
| | **JDBC Template** | **H2** | **Read/Reporting** <br> Aggregation queries <br> Explicit SQL <br> Performance analytics | `ReportDao` <br> `JdbcTemplate` <br> `book_report` table |
| ↓ | | | |
| **DATABASE** | **MySQL** | **Permanent** | Transactional data <br> ACID compliance <br> Entity relationships <br> Historical records | Tables: <br> • authors <br> • books <br> • members <br> • loans |
| | **H2** | **In-Memory** | Reporting cache <br> Aggregated stats <br> Fast read access <br> **Volatile** (resets on restart) | Tables: <br> • book_report <br> • borrow_counts |
| ↓ | | | |
| **SYNC LAYER** | **DataSyncService** | MySQL → H2 | Auto-populate H2 on startup <br> Manual refresh on demand <br> Keep reports up-to-date | `@PostConstruct` <br> `POST /api/sync/now` <br> `GET /api/sync/status` |

------------------------------------------------------------------

#### 📊 **Domain Model**
---

### 🧩 **Entities Overview**

| Entity | Table Name | Primary Key | Description | Relationships |
|--------|-----------|-------------|-------------|---------------|
| **Author** | `authors` | `id` | Person who writes books | One-to-Many with Book |
| **Book** | `books` | `id` | Physical book that can be borrowed | Many-to-One with Author <br> One-to-Many with Loan |
| **Member** | `members` | `id` | Person who borrows books | One-to-Many with Loan |
| **Loan** | `loans` | `id` | Record of a book being borrowed | Many-to-One with Book <br> Many-to-One with Member |

---

### 🔗 **Relationship Matrix**

|              | **Author** | **Book** | **Member** | **Loan** |
|--------------|-----------|----------|------------|----------|
| **Author**   | - | 1 → N | - | - |
| **Book**     | N → 1 | - | - | 1 → N |
| **Member**   | - | - | - | 1 → N |
| **Loan**     | - | N → 1 | N → 1 | - |

**Legend:** `1 → N` = One-to-Many | `N → 1` = Many-to-One

------------------------------------------------------
#### 🌐 **REST API Endpoints**
| Method | Endpoint | Description | Database |
|--------|---------|-------------|----------|
| **Authors** |
| POST | `/api/authors` | Create new author | MySQL |
| GET | `/api/authors` | List all authors | MySQL |
| GET | `/api/authors/{id}` | Get author by ID | MySQL |
| PUT | `/api/authors/{id}` | Update author | MySQL |
| DELETE | `/api/authors/{id}` | Delete author | MySQL |
| **Books** |
| POST | `/api/books?authorId={id}` | Create book | MySQL |
| GET | `/api/books` | List all books | MySQL |
| GET | `/api/books/{id}` | Get book by ID | MySQL |
| GET | `/api/books/author/{authorId}` | Books by author | MySQL |
| **Members** |
| POST | `/api/members` | Register member | MySQL |
| GET | `/api/members` | List members | MySQL |
| GET | `/api/members/{id}` | Get member | MySQL |
| **Loans** |
| POST | `/api/loans/borrow?memberId={id}&bookId={id}` | Borrow book | MySQL + H2 |
| POST | `/api/loans/return/{loanId}/{memberId}` | Return book | MySQL + H2 |
| GET | `/api/loans/active` | Active loans | MySQL |
| GET | `/api/loans/overdue` | Overdue loans | MySQL |
| GET | `/api/loans/member/{memberId}` | Member's loans | MySQL |
| **Reports (H2)** |
| GET | `/api/reports/most-borrowed` | Top 10 books | H2 |
| GET | `/api/reports/stats` | Book statistics | H2 |
| GET | `/api/reports/status` | H2 health check | H2 |
| **Sync** |
| POST | `/api/sync/now` | Manual H2 refresh | MySQL → H2 |
| GET | `/api/sync/status` | Sync service status | - |

-------------------------------------------------

####  **H2 Reporting Schema**

```sql
CREATE TABLE IF NOT EXISTS book_report (
    book_id BIGINT PRIMARY KEY,
    title VARCHAR(255),
    borrow_count INT DEFAULT 0
);
```
### **Project Structure**

month2_spring_fundamentals/
└── deliverable2_library_system/
    ├── src/main/java/.../
    │   ├── config/
    │   │   ├── DatabasesConfig.java    # MySQL + H2 data sources
    │   │   └── JpaConfig.java          # JPA configuration
    │   │
    │   ├── entities/
    │   │   ├── Author.java
    │   │   ├── Book.java
    │   │   ├── Member.java
    │   │   ├── Loan.java
    │   │   └── enums/
    │   │       ├── Genre.java
    │   │       ├── LoanStatus.java
    │   │       └── MembershipStatus.java
    │   │
    │   ├── repositories/               # Spring Data JPA
    │   │   ├── AuthorRepository.java
    │   │   ├── BookRepository.java
    │   │   ├── MemberRepository.java
    │   │   └── LoanRepository.java
    │   │
    │   ├── dao/                       # JDBC for H2
    │   │   └── ReportDao.java
    │   │
    │   ├── service/
    │   │   ├── AuthorService.java
    │   │   ├── BookService.java
    │   │   ├── MemberService.java
    │   │   ├── LoanService.java       # @Transactional across DBs
    │   │   ├── ReportService.java     # H2 reports
    │   │   └── DataSyncService.java   # Auto + manual sync
    │   │
    │   └── controller/                # REST endpoints
    │       ├── AuthorController.java
    │       ├── BookController.java
    │       ├── MemberController.java
    │       ├── LoanController.java
    │       ├── ReportController.java
    │       └── DataSyncController.java
    │
    └── src/main/resources/
        ├── application.properties     # DB credentials
        └── schema-h2.sql             # H2 table definition


 

---

##  Testing  
All exercises include JUnit 5 tests.  

---

##  Tools  
- Java 17  
- Maven  
- IntelliJ  
- Git & GitHub  

---

##  Changelog  
Every update includes a commit and a README change.

- `2025-11-17` — Repository created, Week 1 started.  
- *(Add entries as you progress)*  

---

## ⭐ Goals
- Build professional-quality projects  
- Document entire learning journey  
- Become consistent and disciplined  
