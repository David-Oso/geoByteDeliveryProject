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
* Brevo API
* MySQL database for data storage.
* React Js
* JPA for persistence

Note: I used MySql because i couldn't connect my application to the db after configuring spring security.

# Setup
* Clone the repository: git clone https://github.com/David-Oso/geoByteDeliveryProject.git

# Backend Setup
* Open the cloned project folder
* Open the GeoByteDelivery folder using an IDE e.g NetBean, Intellij, Eclipse etc.
* Create a application-dev.properties file
* Declare and set the value for DB_USER_NAME, DB_PASSWORD, DB_URL, MAIL_API_KEY, MAIL_URL, JWT_SECRET, ACCESS_TOKEN_EXPIRATION, REFRESH_TOKEN_EXPIRATION
* Run the application by clicking on the play button.


# Frontend Setup
* Open the geo_byte_frontend folder using a text editor or an IDE e.ge Webstorm, VsCode, Atom etc.
* Install dependencies: npm install
* Start the React application: npm start
  

# Running the Application
* The backend API is available at http://localhost:8080
* Access the application frontend at http://localhost:3000
* After you have accessed the frontend, you get access to the application


# Endpoints
user endpoints
* /user/register:  To register the user.
* /user/resend_verification_mail:  To resend the otop for email verification.
* /user/verify:   To verify the user by the otp sent to the user email.
* /user/login:   To login using your credentials.
* /user/current:  To get the user of the application when the jwt tokens have been genereated.
* /user/get/email:  Toe get the user by email.

  
delivery locations endpoints
* /delivery_location/add:  To add a new delivery locataion to the existing delivery locations 
* /delivery_location/update:  To update a delivery location
* /delivery_location/get/all:  To get all the delivery locations
* /delivery_location/cost_of_delivery/get:  To get the cost of delivery between a location to another.
* /delivery_location/delete{id}:  To get delivery location by id


# Contributing
Contributions are welcome! If you find any issues or have suggestions for improvements, please open an chat me up on osodavid001@gmail.com, or on LinkedIn: https://www.linkedin.com/in/david-oso-david/
