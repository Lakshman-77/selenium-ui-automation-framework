# UI Automation Framework

![UI Automation Tests](https://github.com/Lakshman-77/selenium-ui-automation-framework/actions/workflows/ci.yml/badge.svg)

**Author:** Lakshman Naidu Bandela

A reusable UI automation framework built using **Java, Selenium WebDriver, TestNG, and Maven** for end-to-end testing of the **SauceDemo** e-commerce web application. The framework follows the **Page Object Model (POM)** design pattern with reusable page objects, explicit waits, HTML reporting using ExtentReports, and continuous integration through GitHub Actions.

---

## Features

- Page Object Model (POM) architecture
- Reusable BasePage and BaseTest classes
- Explicit waits for reliable UI interactions
- Positive and negative test scenarios
- End-to-end e-commerce workflow automation
- HTML reporting using ExtentReports
- Automated test execution with GitHub Actions
- Clean and maintainable project structure

---

## Tech Stack

- Java 17
- Selenium WebDriver 4
- TestNG
- Maven
- WebDriverManager
- ExtentReports
- GitHub Actions

---

## Project Structure

```text
selenium-ui-automation-framework/
│
├── .github/
│   └── workflows/
│       └── ci.yml
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── pages/
│   │           ├── BasePage.java
│   │           ├── LoginPage.java
│   │           ├── InventoryPage.java
│   │           ├── CartPage.java
│   │           ├── CheckoutPage.java
│   │           └── LogoutPage.java
│   │
│   └── test/
│       ├── java/
│       │   ├── base/
│       │   │   └── BaseTest.java
│       │   ├── listeners/
│       │   │   └── ExtentReportListener.java
│       │   └── tests/
│       │       ├── LoginTest.java
│       │       ├── InventoryTest.java
│       │       ├── CartTest.java
│       │       ├── CheckoutTest.java
│       │       └── LogoutTest.java
│       │
│       └── resources/
│           └── testng.xml
│
├── pom.xml
├── README.md
├── LICENSE
└── .gitignore
```

---

## Test Coverage (24 Test Cases)

| Module | Test Scenarios |
|---------|----------------|
| **Login** | Valid login, invalid password, empty username, empty password, locked-out user |
| **Inventory** | Verify page title, verify product count, add single item, add multiple items, sort by price (Low–High), sort by price (High–Low), sort by name (A–Z), sort by name (Z–A) |
| **Cart** | Empty cart validation, item appears in cart, remove single item, remove all items, continue shopping |
| **Checkout** | Successful checkout, missing first name, missing last name, missing postal code, verify order summary |
| **Logout** | Verify authenticated session is not restored using the browser Back button after logout |

---

## Framework Design

### Page Object Model (POM)

Each application page is represented by a dedicated Page Object class that encapsulates element locators and user interactions. This keeps test classes clean and improves maintainability.

### Base Classes

- **BasePage** provides reusable Selenium utilities such as click, type, waits, text retrieval, and element visibility checks.
- **BaseTest** manages browser setup, teardown, and Chrome configuration for both local execution and GitHub Actions.

### Explicit Waits

Explicit waits are used throughout the framework to synchronize with dynamic UI elements and reduce flaky test execution.

### Reporting

ExtentReports generates an interactive HTML report after every execution, making it easier to analyze passed and failed test cases.

---

## Running the Project

### Prerequisites

- Java 17 or later
- Maven 3.9 or later
- Google Chrome

### Clone the Repository

```bash
git clone https://github.com/Lakshman-77/selenium-ui-automation-framework.git

cd selenium-ui-automation-framework
```

### Execute All Tests

```bash
mvn clean test
```

---

## Test Report

After execution, the Extent Report is generated at:

```text
test-output/extent-report.html
```

---

## Continuous Integration

GitHub Actions automatically builds the project and executes the complete Selenium UI test suite on every push and pull request, helping ensure the framework remains stable.

---

## License

This project is licensed under the MIT License.