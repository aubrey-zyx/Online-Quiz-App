# Online-Quiz-App
A Spring Boot application that provides online quizzes.

Tech: Spring Boot, Spring MVC, Maven, Hibernate, MySQL, HTML/CSS, JSP  

## Pages
### Regular User
#### Registration Page
#### Login Page
- Default page when entering the app.  
#### Home Page
1. Quiz Categories Section
2. Quiz History Table  
#### Quiz Page
- Each quiz has 5 multiple choice questions on a single page.
- Questions are randomly assigned to each quiz.
- User can only have one ongoing quiz.
#### Quiz Result Page
- One page containing quiz name, user's full name, quiz start & end time, the result (Passed/Failed) and the result of each question.
- User passes if more than 3 questions are answered correctly.
#### Contact Us Page
- A form that enables users to send messages to the admin.
- Users can access this page even if they are not logged in.
### Admin
#### Admin Home Page
- Contains buttons to the following pages.
#### User Management Page
- A table that presents all user information.
- "Activate" or "Suspend" buttong at the end of each row to toggle the status of the user.
#### Quiz Result Management Page
- A table that shows all quiz results with links to the Quiz Result Detail Page.
#### Question Management Page
- A table that shows all the questions.
- An "Edit" button at the end of each row for the admin to update question description, choices, or correct answer by redirecting to a question edit page.
- An "Activate" or "Suspend" button at the end of each row to toggle the status of the question.
- An "Add" button to add a new question by redirecting to a new page.
#### Contact Us Management Page
- A table that shows all the contact us messages.
