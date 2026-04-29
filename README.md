# 🏦 Banking System — Java & JDBC

A console-based Banking System built in Java using JDBC and MySQL. This project simulates core banking operations like user registration, login, account creation, deposits, and withdrawals.

---

## 🚀 Features

- **User Registration** — Register with email, name, and password
- **User Login** — Secure login with email and password validation
- **Create Bank Account** — Create a bank account with a 4-digit security PIN
- **Check Balance** — View your current account balance
- **Deposit Money** — Add funds to your account
- **Withdraw Money** — Withdraw funds with balance validation

---

## 🛠️ Tech Stack

| Technology | Usage |
|------------|-------|
| Java | Core application logic |
| JDBC | Database connectivity |
| MySQL | Relational database |

---

## 📁 Project Structure

```
Banking System/
├── src/
│   ├── Main.java           # Entry point, handles user interaction & menu
│   ├── DAO/
│   │   ├── UserDAO.java    # Handles user registration & login
│   │   └── AccountDAO.java # Handles account creation, deposit & withdrawal
│   └── Util/
│       └── DBConnection.java # MySQL database connection utility
├── .gitignore
└── README.md
```

---

## 🗄️ Database Setup

1. Open MySQL and create the database:

```sql
CREATE DATABASE Banking;
USE Banking;
```

2. Create the `users` table:

```sql
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) UNIQUE NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);
```

3. Create the `accounts` table:

```sql
CREATE TABLE accounts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    balance DOUBLE DEFAULT 0.0,
    security_pin VARCHAR(4) NOT NULL,
    FOREIGN KEY (email) REFERENCES users(email)
);
```

---

## ⚙️ How to Run

1. **Clone the repository**
```bash
git clone https://github.com/ayush417/Banking---System---Java.git
cd Banking---System---Java
```

2. **Configure the database connection**

Open `src/Util/DBConnection.java` and update your MySQL credentials:
```java
DriverManager.getConnection("jdbc:mysql://localhost:3306/Banking", "your_username", "your_password");
```

3. **Add MySQL JDBC Driver**

Download the [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/) and add it to your project's classpath in IntelliJ:
- `File → Project Structure → Libraries → + → Java → select the .jar`

4. **Run the project**

Open `Main.java` in IntelliJ IDEA and click the ▶️ Run button.

---

## 📸 Sample Output

```
Welcome to XYZ Bank
1. Register
2. Login
3. Exit

--- Banking Menu ---
1. Show Balance
2. Deposit
3. Withdraw
4. Logout
```

---

## 🙋‍♂️ Author

**Ayush** — [GitHub](https://github.com/ayush417)
