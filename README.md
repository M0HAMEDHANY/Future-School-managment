# Future School Management
![Screenshot 2024-06-13 172616](https://github.com/mohameddhanyyy/Future-School-managment/assets/130695667/d5f9a87c-3d42-4b81-b27f-0a2072b025c9)


Welcome to the Future School Management project! This application is designed to help manage school data efficiently, providing a user-friendly interface for administrative tasks. This README file provides an overview of the project, including its technical implementation, features, and setup instructions.

## Table of Contents

- [Technical Implementation](#technical-implementation)
- [Building Our Project](#building-our-project)
- [Features](#features)
  - [Login Functionality](#login-functionality)
  - [Sign Up Functionality](#sign-up-functionality)
- [Database Schema](#database-schema)
- [Setup Instructions](#setup-instructions)

## Technical Implementation

### JavaFX and Scene Builder

We used JavaFX and Scene Builder to design a user-friendly graphical interface (GUI) for the desktop application. Scene Builder streamlined the process of creating a visually appealing and intuitive interface for managing school data.

### XAMPP

XAMPP, an open-source package, provided a local server environment, allowing us to set up a MySQL database using XAMPP's built-in tools. This database serves as the heart of our application, storing and managing school information.

## Building Our Project

### Data Modeling

We designed our MySQL database schema to efficiently store all relevant student data. This involves creating tables to hold student information, login credentials, and other necessary data points.

### GUI Design

Using Scene Builder, we created user interfaces for data entry, viewing, and editing. We ensured a clear and organized layout for easy navigation.

### JavaFX Application Logic

We wrote Java code to connect to the MySQL database using JDBC libraries. This code handles tasks like fetching data, performing CRUD (Create, Read, Update, Delete) operations, and manipulating data within the application.

## Features

### Login Functionality

The login process secures the application and ensures authorized access to administrative features.

#### User Interface
![image](https://github.com/mohameddhanyyy/Future-School-managment/assets/130695667/a9aa887d-8fc8-4501-a3be-bff8db4d724f)


- **Login Screen**: Designed with Scene Builder, this screen includes:
  - A text field for entering the username (email address).
  - A password field (masked for security) for entering the user's password.
  - A "Login" button to initiate the authentication process.

#### Backend Functionality (Java)

- **User Input Validation**: When the "Login" button is clicked, the Java code retrieves the username and password entered by the user and validates that the fields are not empty.
- **Database Connection**: Using JDBC libraries, the Java code connects to the MySQL database managed by XAMPP.
- **User Authentication**: The application constructs a query to retrieve user information (username, password hash) from the database. Passwords are stored securely using a hashing algorithm (like bcrypt). The entered password (hashed using the same algorithm) is compared with the stored password hash.
- **Login Success**: If the username and password hash match, the login is successful. The Java code sets a session variable to identify the logged-in user and redirects to the appropriate dashboard based on their user role (e.g., teacher, administrator).
- **Login Failure**: If the username and password hash do not match, an error message is displayed on the login screen indicating invalid credentials.

### Sign Up Functionality

The sign-up process allows new users to create an account.

#### User Interface
![Screenshot 2024-06-13 172641](https://github.com/mohameddhanyyy/Future-School-managment/assets/130695667/afd9618e-d69d-485d-9b9d-061d4de549bb)


- **Sign Up Screen**: Designed with Scene Builder, this screen includes:
  - Text fields for entering user details (e.g., name, email, password, level, confirm password, phone number).
  - A "Sign Up" button to submit the registration form.

#### Backend Functionality (Java)

- **User Input Validation**: When the "Sign Up" button is clicked, the Java code retrieves the entered details and validates that the fields are not empty.
- **Database Connection**: Using JDBC libraries, the Java code connects to the MySQL database managed by XAMPP.
- **User Registration**: The application checks if the user already exists in the database. If not, it stores the new user's details, with the password securely hashed using a hashing algorithm.
- **Registration Success**: If registration is successful, the user is redirected to the login screen.
- **Registration Failure**: If the user already exists, an error message is displayed indicating that the username or email is already taken.

## Database Schema
![image](https://github.com/mohameddhanyyy/Future-School-managment/assets/130695667/3f747648-cc08-4b6f-a744-1e2af3b0523a)



The database includes the following tables:

![Screenshot 2024-06-13 173733](https://github.com/mohameddhanyyy/Future-School-managment/assets/130695667/22dc12a5-ede0-4151-b28a-1c3a10524df7)

- **users**: Stores user login credentials and details.
  - Columns: ID, name, email, level, phone number

## Setup Instructions

To set up the Future School Management project on your local machine, follow these steps:

1. **Clone the Repository**: Clone the project repository from GitHub.

   ```sh
   git clone https://github.com/mohameddhanyyy/future-school-management.git
   ```

2. **Install XAMPP**: Download and install XAMPP from [https://www.apachefriends.org/index.html](https://www.apachefriends.org/index.html).

3. **Set Up the Database**:
   - Start XAMPP and activate the MySQL module.
   - Open phpMyAdmin and create a new database named `school_management`.
   - Import the provided SQL file (`school_management.sql`) to set up the tables and initial data.

4. **Configure the Project**:
   - Open the project in Eclipse.
   - Update the database connection details in the `DatabaseConfig.java` file with your MySQL credentials.

5. **Run the Application**:
   - Build and run the project from Eclipse.
   - Access the application and log in with the provided credentials.

Congratulations! You have successfully set up the Future School Management project. Enjoy managing your school data with ease.
