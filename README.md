# Expense Tracker / Budget Manager (Console App)

This is the second project in my journey to build a **Junior Java Developer portfolio** while practicing and consolidating my studies.

This project is an **expense management console application**, where the user can add and remove expenses, list them in different ways, and analyze spending based on several parameters.

---

## Features

The console application provides the following main functionalities:

- Add an expense  
- Remove an expense  
- List expenses  
- Analyze expenses  

The first two options are more direct.  
The **listing option** allows expenses to be displayed based on parameters such as:
- Category  
- Month  
- Year  
- Payment method  

The **analysis option** applies more business logic, allowing the user to:
- Find the month or category with the highest or lowest spending  
- Calculate the total amount spent by month or year  
- Calculate average expenses based on different parameters  

---

## Architecture

As in my first project, I applied a **Layered Architecture** to better prepare for future Spring Boot projects and to consolidate the responsibility of each layer:

- **Main** – Application bootstrap and object instantiation  
- **UI** – Console interaction, menus, and basic input validation  
- **Service** – Business logic and Stream operations  
- **Repository** – In-memory data management  
- **Model** – Domain entities and enums  

---

## Development Notes

For this project, I decided to practice **core Java concepts** in a more elaborate way.  
I focused on building more logic in the Service layer, working extensively with **Java Streams**, and exploring topics such as **BigDecimal for monetary precision** and **input validation**.

During development, I faced several challenges, especially:
- Manipulating `BigDecimal`
- Applying Streams correctly
- Understanding when to use `toList()` vs `Collectors.toList()`
- Deciding whether certain logic should belong to the Repository or Service layer  

Throughout the project, I identified many opportunities for **refactoring and improvement**. These ideas became clearer as the code evolved, particularly in the UI listing menus and the analysis logic. Planning, observing the code, and finding ways to better encapsulate behavior turned out to be a valuable and enjoyable learning experience.

---

## Future developments tasks:
- Padronizing the methods
- Creating a new method to read the options and we pass a range of parameters and simplify many validations code

---

This project represents another important step in my path toward building a solid Java foundation before moving on to **data persistence and Spring Boot**.
