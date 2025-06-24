# Candidate Management Application

A simple full-stack web application for managing candidate records, built with:

- **Backend:** Spring Boot (Java 17)
- **Frontend:** Angular 17+

---

## 🚀 Backend - Spring Boot (Java)

### Technologies Used

- Spring Boot 3.1.0
- Spring Data JPA
- H2 In-Memory Database
- Maven
- Java 17

### Features

- RESTful API for candidate management.
- Field validation (name, valid email, position).
- Unique email check to prevent duplicates.
- Pagination support.
- Exception handling for invalid or duplicate inputs.
- H2 in-memory database.

### API Endpoints

| Method | Endpoint                        | Description                      |
|--------|---------------------------------|----------------------------------|
| GET    | `/api/candidates?page=0&size=5` | Retrieve paginated candidates |
| POST   | `/api/candidates`               | Add a new candidate (with validation) |
| DELETE | `/api/candidates/{id}`          | Delete a candidate by ID         |

### Running the Backend

```bash
cd candidate-backend
mvn clean install
mvn spring-boot:run
```

## 💻 Frontend - Angular (Candidate App)

### Technologies Used

- Angular 14
- TypeScript
- Bootstrap 5

---

### Features

- **Add Candidate:**  
  A form to add new candidates with validation on:
  - Required fields (Name, Email, Position)
  - Valid email format
- **View Candidates:**  
  Displays the list of candidates in a table format with:
  - Pagination controls
  - Real-time data fetched from backend via REST API
- **Delete Candidate:**  
  Users can delete a candidate by clicking the "Delete" button.
- **Responsive UI:**  
  The frontend uses Bootstrap for basic styling and responsiveness.

---

### Running the Frontend (Development Server)

1. Install dependencies:

```bash
cd candidate-app
npm install
ng serve


