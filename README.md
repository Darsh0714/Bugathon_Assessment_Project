# AI Assessment Platform Bugathon – End-to-End Selenium Automation Framework

## Overview

This repository contains an end-to-end automation framework developed as part of an AI Assessment Platform Bugathon. The objective of this project is to automate critical assessment workflows, validate platform behavior, and identify potential defects impacting system stability and user experience.

The framework is built using **Java**, **Selenium WebDriver**, and **TestNG**, following the **Page Object Model (POM)** design pattern to ensure scalability, maintainability, and clear separation of concerns.

---

## Project Objective

The automation suite covers the complete lifecycle of creating and managing an assessment within an AI-based assessment platform. Key workflows automated include:

- User authentication
- Dashboard navigation
- Template creation
- Assessment creation
- Question library interaction
- Objective question selection
- Programming question selection
- Popup handling and synchronization
- Save and submit workflow

Automation execution was supported by manual testing to validate platform behavior and confirm workflow stability.

---

## Technology Stack

**Programming Language**  
Java

**Automation Tool**  
Selenium WebDriver

**Testing Framework**  
TestNG

**Build Tool**  
Maven

**Framework Design**  
Page Object Model (POM)

**Utilities**  
Custom StepLogger  
Demo Mode Execution Utility

---

## Project Structure

```
com.hyrenet
├── base
│   └── BaseTest.java
│
├── pages
│   ├── LoginPage.java
│   ├── DashboardPage.java
│   ├── TemplateCreatePage.java
│   ├── CreateTestPage.java
│   ├── AssessmentPage.java
│   └── QuestionLibraryPopup.java
│
├── tests
│   └── LoginTest.java
│
├── utils
│   ├── StepLogger.java
│   ├── Config.java
│   └── DemoUtil.java
```

---

## Framework Design Highlights

### Page Object Model (POM)

Each UI page is encapsulated within a dedicated class responsible for handling element interactions and business logic.

- **LoginPage** – Handles authentication and login validation  
- **DashboardPage** – Manages navigation between platform modules  
- **TemplateCreatePage** – Controls template creation workflow  
- **CreateTestPage** – Handles test setup actions  
- **AssessmentPage** – Manages assessment configuration  
- **QuestionLibraryPopup** – Handles modal interaction, filtering, and question selection  

This design improves readability, reusability, and long-term maintainability.

---

### SafeClick Strategy

A custom SafeClick implementation ensures stable interaction with dynamic UI elements. The strategy includes:

- Scroll into view handling
- Explicit wait synchronization
- JavaScript fallback click execution
- Handling ElementNotInteractableException scenarios

This significantly reduces flaky test failures caused by modal transitions and delayed rendering.

---

### Demo Mode Support

The framework supports a configurable Demo Mode designed for presentation and visual walkthroughs.

```
Config.DEMO_MODE = true;
```

When enabled:

- Execution pauses between steps
- Automation flow becomes visually traceable
- Ideal for Bugathon demonstration videos

---

## Automation Flow Covered

Login  
Dashboard Navigation  
Template Creation  
Assessment Creation  
Open Question Library Popup  
Add Objective Questions  
Switch to Programming Tab  
Add Programming Questions  
Close Popup Safely  
Save and Submit Assessment  

---

## Logging System

A custom StepLogger utility provides clear and structured execution logs.

Example output:

```
[STEP] Opening Question Library Popup
[STEP] Adding Objective Questions
[STEP] Selecting Programming Questions
[STEP] Popup closed successfully
[STEP] Save & Submit CONFIRMED
```

This improves debugging efficiency and enhances execution clarity during demos.

---

## How to Run the Project

### Clone the Repository

```
git clone <repository-url>
```

### Open in IDE

Recommended IDE: Visual Studio Code

### Run Tests Using Maven

```
mvn clean test
```

---

## Testing Scope

Automation and manual testing were conducted to validate:

- Functional workflow execution
- Popup handling stability
- Question library interactions
- Navigation consistency
- UI synchronization during modal transitions

Testing focused on flows defined within the Bugathon problem statement.

---

## Bug Identification Status

Based on current automation execution and manual validation, no functional defects were identified within the covered workflows at the time of submission.

All observations and validations are documented within the provided test artifacts.

---

## Deliverables Included

- Selenium Automation Framework
- Test Scenarios
- Test Cases (Positive and Negative)
- Requirement Traceability Matrix (RTM)
- Bug Report Template
- Execution Demo Video
- GitHub Repository

---

## Key Framework Features

- End-to-end assessment workflow automation
- Clean Page Object Model architecture
- Stable SafeClick interaction strategy
- Demo-friendly execution flow
- Custom structured logging
- Modal-safe synchronization design

---

## Future Enhancements

- Allure reporting integration
- Jenkins CI pipeline integration
- Cross-browser execution support
- Automated failure screenshot reporting

---

## Author

Darshini Ravichandran
