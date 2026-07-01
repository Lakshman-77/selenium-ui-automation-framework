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

## Test Coverage (26 Test Cases)

| Module | Coverage |
|---------|----------|
| Login | Valid login, invalid password, empty username, empty password, locked-out user |
| Inventory | Verify page title, verify product count, add single item, add multiple items, sort by price (Low–High), sort by price (High–Low), sort by name (A–Z), sort by name (Z–A) |
| Cart | Empty cart validation, item appears in cart, remove single item, remove all items, continue shopping |
| Checkout | Successful checkout, missing first name, missing last name, missing postal code, order summary validation |
| Logout | Logout redirect, login page visibility after logout, session invalidation using browser back button |

---

## Design Decisions

### Page Object Model (POM)

Each page encapsulates its own locators and user interactions, making the framework easier to maintain and reducing duplication across test classes.

### Reusable Base Classes

- **BasePage** centralizes common Selenium operations such as clicking, typing, waiting, and locating elements.
- **BaseTest** manages browser initialization and cleanup for every test.

### Explicit Waits

The framework uses explicit waits to improve test stability and avoid flaky execution caused by dynamic page loading.

### Reporting

ExtentReports generates a detailed HTML report after every execution, providing clear insights into test results.

---

## Running the Project

### Prerequisites

- Java 17+
- Maven 3.9+
- Google Chrome

### Clone Repository

```bash
git clone https://github.com/Lakshman-77/selenium-ui-automation-framework.git

cd selenium-ui-automation-framework
```

### Execute Tests

```bash
mvn clean test
```

---

## Test Report

After execution, the Extent Report is generated under:

```text
test-output/extent-report.html
```

---

## Continuous Integration

The project includes a GitHub Actions workflow that automatically executes the complete Selenium UI test suite on every push to the repository.

---

## License

This project is licensed under the MIT License.