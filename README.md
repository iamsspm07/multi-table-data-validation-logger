# User Registration & Management System

## Project Overview

This is a **User Registration & Management System** built using **Spring Boot** and **PostgreSQL**. The application allows users to register, update, delete, and fetch user details. It features role-based access control, password encryption, and comprehensive API services for managing user data.

### Key Features:
- **User Registration**: Users can register by providing their details (name, email, phone number, role, profession, etc.).
- **Update User Details**: Users can update their profile details (email, phone, password, etc.).
- **Delete User**: Users can be deleted using their email or phone number.
- **Fetch User Details**: Fetch user details based on email or phone number.
- **Role-based Access Control**: Roles like `Customer (Buyer)`, `Seller (Vendor)`, etc.
- **Password Encryption**: Passwords are encrypted using `BCryptPasswordEncoder`.

## Technologies Used
- **Backend**: Spring Boot
- **Database**: PostgreSQL
- **Security**: Spring Security for user authentication and authorization
- **APIs**: RESTful APIs for user management
- **Version Control**: Git and GitHub

## API Endpoints

### 1. **User Registration**
- **Endpoint**: `POST /api/users/register`
- **Description**: Register a new user.
- **Request Body**:
  ```json
  {
    "userName": "John Doe",
    "userMail": "johndoe@example.com",
    "userNumber": "1234567890",
    "userPassword": "Password@123",
    "userRole": "Customer",
    "userProfession": "Engineer",
    "country": "USA",
    "city": "New York"
  }
