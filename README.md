Ripple - Instagram Clone Web Application
Ripple is a social media platform inspired by Instagram, built using Spring Boot for the backend and HTML, CSS, and JavaScript for the frontend. The app provides basic features such as user authentication, image posting, and likes. It is designed to showcase my skills in creating scalable and maintainable web applications with modern technologies.

Features
User Authentication: Users can register, log in, and manage their profiles.
Image Posting: Users can upload images and share them with others.
Likes: Users can like posts and see the count of likes on each post.
Design: Built with HTML, CSS, and JavaScript to ensure a new experience.


Tech Stack
Backend:
Java
Spring Boot (RESTful APIs)
PostgreSQL (Database)

Frontend:
HTML
CSS
JavaScript
Installation
To run Ripple locally, follow these steps:

Prerequisites
Java (version 8 or above)
Maven (for managing dependencies)
PostgreSQL (for the database)


Steps
Clone the repository:
open bash
Copy code
git clone https://github.com/AnisShah03/Ripple-Backend.git
cd ripple
Set up the PostgreSQL database:

Create a new database in PostgreSQL.
Update the application.properties file with your database credentials.

Build the application:
bash
Copy code
mvn clean install

Run the application:
bash
Copy code
mvn spring-boot:run
The backend server will be running on http://localhost:8080.

Access the app in your browser by navigating to the front-end directory and opening the index.html file.
API Endpoints
Here are some key RESTful API endpoints available in the app:

//pending to add


Contributing
Feel free to fork the repository, create a branch, and submit a pull request with improvements or bug fixes. Contributions are welcome!

License
This project is licensed under the MIT License - see the LICENSE file for details.

Acknowledgements
Thanks to Spring Boot for making it easy to build Java-based applications.
Special thanks to the PostgreSQL community for providing a robust and reliable database.
