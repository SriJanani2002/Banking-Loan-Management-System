# 🏦 Banking & Loan Management System

A full-stack REST API backend built with **Java Spring Boot** for managing bank accounts, transactions, and loans with role-based JWT authentication.

---

## 🚀 Tech Stack

| Technology | Usage |
|------------|-------|
| Java 17 | Programming Language |
| Spring Boot 3.x | Backend Framework |
| Spring Security | Authentication & Authorization |
| JWT | Token-based Security |
| Spring Data JPA | Database ORM |
| MySQL | Database |
| Maven | Build Tool |

---

## 📌 Features

### 👤 User Module
- Register as Admin or Customer
- Login with JWT token
- Role-based access control

### 🏦 Account Module
- Create Savings or Current account
- View account balance
- Deposit and Withdraw money

### 💸 Transaction Module
- Fund transfer between accounts
- Transaction history

### 💰 Loan Module
- Apply for loan
- EMI auto-calculation
- Admin approve/reject loans
- Loan repayment tracking

### 🔐 Security
- JWT Authentication
- Role-based access (ADMIN, CUSTOMER)
- Input validation
- Global error handling

---

## ⚙️ Setup & Installation

### 1. Clone the repository
```bash
git clone https://github.com/your-username/banking-loan-management.git
cd banking-loan-management
```

### 2. Configure MySQL
Create a database:
```sql
CREATE DATABASE project_loanmanagementdb;
```

Update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/project_loanmanagementdb
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
jwt.secret=your_secret_key
jwt.expiration=604800000
```

### 3. Run the application
```bash
mvn spring-boot:run
```

App runs on `http://localhost:8091`

---

## 📡 API Endpoints

### 🔓 Auth (Public)
| Method | URL | Description |
|--------|-----|-------------|
| POST | `/api/auth/register` | Register user |
| POST | `/api/auth/login` | Login & get token |

### 🏦 Accounts (Customer)
| Method | URL | Description |
|--------|-----|-------------|
| POST | `/api/accounts/create` | Create account |
| GET | `/api/accounts/my` | View my accounts |
| PUT | `/api/accounts/{id}/deposit` | Deposit money |
| PUT | `/api/accounts/{id}/withdraw` | Withdraw money |

### 💸 Transactions (Customer)
| Method | URL | Description |
|--------|-----|-------------|
| POST | `/api/transactions/transfer/{fromAccountId}` | Transfer money |
| GET | `/api/transactions/history/{accountId}` | Transaction history |

### 💰 Loans (Customer)
| Method | URL | Description |
|--------|-----|-------------|
| POST | `/api/loans/apply` | Apply for loan |
| GET | `/api/loans/my` | View my loans |
| POST | `/api/loans/{loanId}/repay` | Repay loan |
| GET | `/api/loans/{loanId}/repayments` | Repayment history |

### 👑 Admin Panel
| Method | URL | Description |
|--------|-----|-------------|
| GET | `/api/admin/users` | View all users |
| GET | `/api/admin/accounts` | View all accounts |
| GET | `/api/admin/transactions` | View all transactions |
| GET | `/api/admin/loans` | View all loans |
| PUT | `/api/admin/loans/{loanId}/status` | Approve/Reject loan |

---

## 🗄️ Database Schema

users           → id, name, email, password, role
accounts        → id, user_id, account_no, balance, type, status
transactions    → id, from_account_id, to_account_id, amount, type, created_at
loans           → id, user_id, amount, interest_rate, tenure_months, emi_amount, status
loan_repayments → id, loan_id, amount_paid, paid_at

---

## 🔐 Default Test Users

| Role | Email | Password |
|------|-------|----------|
| Admin | admin@bank.com | admin123 |
| Customer | customer@bank.com | customer123 |

---

## 📦 Project Structure

src/main/java/com/bank/loanmanagement/
├── controller/       # REST Controllers
├── service/          # Business Logic
├── repository/       # Database Queries
├── entity/           # Database Models
├── dto/              # Request/Response DTOs
├── enums/            # Enumerations
├── security/         # JWT & Spring Security
└── exception/        # Global Error Handling

---

## 👨‍💻 Author

Sri Janani JS 
[GitHub](https://github.com/SriJanani2002) • [LinkedIn](https://linkedin.com/in/sri-janani-js-b911b61a4/)