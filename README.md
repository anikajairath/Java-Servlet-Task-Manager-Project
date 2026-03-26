# Task Manager Web Application (Java Servlet-Based)

# Overview

This project is a web-based task management application that allows users to manage their daily tasks efficiently. It supports user authentication and provides full CRUD functionality for task management.

# Objective

To develop a scalable web application that enables users to create, update, and manage tasks while ensuring secure user-specific data handling.

# Tools & Technologies

* Java (Servlets, JSP)
* MySQL
* JDBC
* Apache Tomcat

# System Features

* User Registration and Login
* Session-based Authentication
* Add, View, Update, Delete Tasks (CRUD operations)
* Task categorization using status and priority
* Due date tracking

# System Architecture

The application follows the MVC (Model-View-Controller) architecture:

* Model: Java classes representing data (Task model)
* View: JSP pages for user interface
* Controller: Servlets handling business logic

# Implementation Details

* Used Servlets for backend processing
* JSP used for frontend UI
* JDBC used for database connectivity
* PreparedStatement used to prevent SQL injection
* Session management implemented using HttpSession

# Database Design

* User Table: Stores user credentials
* Task Table: Stores task details linked to users
* One-to-Many relationship (One user → Multiple tasks)

# Key Learnings

* Understanding of Java Servlet lifecycle
* Implementation of MVC architecture
* Session handling in web applications
* Database integration using JDBC
* Secure coding practices using PreparedStatement

# Outcome

A fully functional web application that demonstrates real-world backend development, user authentication, and database integration.
