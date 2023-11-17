# GeoByte Delivery System
This repository contains the source code for the GeoByte Delivery System, a web application that allows users to register, log in, manage delivery locations, and generate optimal routes for package delivery.

# Table of Contents
* Features
* Tools/Technologies
* Setup
* Running the Application
* Endpoints
* Contributing


# Features
* User registration with name, email, and password
* User login with email and password
* View a list of delivery locations
* Add, remove, and update delivery locations
* Generate optimal routes for package delivery with cost estimation

# Tools/Technologies
* Java
* Spring Boot
* Spring Security
* Brevo Api
* MySQL database for data storage.
* React Js
* JPA for persistence

Note: I used MySql because i couldn't connect my application to the db after configuring spring security.

# Setup
* Clone the repository: git clone https://github.com/David-Oso/geoByteDelivery.git
*

# Backend Setup
* Open the cloned project using an IDE
* Create a application-dev.properties file
* Declare and set the value for DB_USER_NAME, DB_PASSWORD, DB_URL, MAIL_API_KEY, MAIL_URL, JWT_SECRET, ACCESS_TOKEN_EXPIRATION, REFRESH_TOKEN_EXPIRATION
* Run the application by clicking on the play button.

# Frontend Setup
* Install dependencies: npm install
* Start the Angular application: ng serve
* Running the Application
* Access the application frontend at http://localhost:4200
* The backend API is available at http://localhost:8080

# Endpoints
* /api/register: User registration
* /api/login: User login
* /api/delivery-locations: CRUD operations for delivery locations
* /api/generate-route: Generate optimal routes for package delivery

# Contributing
