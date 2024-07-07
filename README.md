# Food Waste Reduction Platform

## Overview
The Food Waste Reduction Platform is a comprehensive solution aimed at addressing the global issue of food waste. It connects food retailers, consumers, and charitable organizations to facilitate the efficient redistribution of surplus food.

## High Level Design Document
https://docs.google.com/document/d/1VAmqbzf8KjLdld_JqMUog4fVVvuHUrC3EsTj1KPETiA/edit

## Features

### register (User Registration and Authentication (Retailers, Consumers, Charitable Organizations))
![image](https://github.com/YangyangLi-can/24S_CST8288_Final_Project/assets/21339369/066cb9a9-763b-4053-89f9-f9e252b30431)
### login
![image](https://github.com/YangyangLi-can/24S_CST8288_Final_Project/assets/21339369/0502923a-c16e-402b-96ea-02a2620c8a82)
### retailer food item dashboard (Surplus Food Identification and Listing)
![image](https://github.com/YangyangLi-can/24S_CST8288_Final_Project/assets/21339369/9d09f197-46bd-4ab4-a858-f928cb216b0f)
### retailer create food item
![image](https://github.com/YangyangLi-can/24S_CST8288_Final_Project/assets/21339369/84a9c4a6-3ee6-4af1-a7d6-f4a36f437df1)
### retailer update food item (Inventory Management for Retailers)
![image](https://github.com/YangyangLi-can/24S_CST8288_Final_Project/assets/21339369/ba86cadd-0de8-4ba6-9c7f-cfee948470e8)
### customer food item dashboard 
![image](https://github.com/YangyangLi-can/24S_CST8288_Final_Project/assets/21339369/c4076a57-b39f-4882-be70-d9638fd41365)
### customer puerchase food item on discount (Discounted Purchases for Consumers)
![image](https://github.com/YangyangLi-can/24S_CST8288_Final_Project/assets/21339369/b50125df-a8fa-4579-8846-b05348658289)
### customer subscribe to surplus food alerts (Surplus Food Alert System)
![image](https://github.com/YangyangLi-can/24S_CST8288_Final_Project/assets/21339369/72e432eb-7d01-4ac6-92ff-4ec70ec20ffd)
### organization food item dashboard
![image](https://github.com/YangyangLi-can/24S_CST8288_Final_Project/assets/21339369/2830400f-fde7-4c9f-942d-5633d61d472b)
### organization claim food item (Donation Claiming for Charitable Organizations)
![image](https://github.com/YangyangLi-can/24S_CST8288_Final_Project/assets/21339369/c6fe7a15-1969-45c1-80c2-16bde0b6ff96)
### organization subscribe to surplus food alerts (Surplus Food Alert System)
![image](https://github.com/YangyangLi-can/24S_CST8288_Final_Project/assets/21339369/66269e83-2bf7-4bd4-91d9-ceb481157815)


## Getting Started

### Cloning the Repository

1. **Clone this project**:
    ```sh
    git clone git@github.com:YangyangLi-can/24S_CST8288_Final_Project.git
    ```

### Setting Up the Database

2. **Set up the database**:
    - Create a MySQL database named `fwrp`.
    - Execute the `DDL.sql` script located in the `/SQL` folder.

### Building the Project

3. **Build the project**:
    ```sh
    mvn clean package
    ```

### Running the Application

4. **Deploy to a Servlet Container**:
    - Use Tomcat 9.
    - Access the application at `http://localhost:8080`.
  
### Branching Strategy

   **Checkout your own branch**:
    - Create a branch for your feature, e.g., `feature/customer_dashboard`.
    - Do not directly change the `main` branch.

### Development Workflow
  
  **Develop and test your feature**:
    - After completing and testing your feature branch, create a pull request on GitHub.
      ![image](https://github.com/YangyangLi-can/24S_CST8288_Final_Project/assets/21339369/8816ce96-b486-406a-a8a1-72d0efa299e5)
      ![image](https://github.com/YangyangLi-can/24S_CST8288_Final_Project/assets/21339369/5d7d0046-e048-457a-b6d6-3f3a9705becf)
    - Request a code review from other group members.
      ![image](https://github.com/YangyangLi-can/24S_CST8288_Final_Project/assets/21339369/ab786f87-50a3-4384-8de0-081c3561355a)
    - It's recommended to attach screenshots to prove you've fully tested your code and to assist the reviewer in understanding your changes.
      ![image](https://github.com/YangyangLi-can/24S_CST8288_Final_Project/assets/21339369/8e82015e-a9ac-41d1-960a-ce101e60dd96)

### Technology Stack

- Backend: Java EE
- Frontend: JSP
- Database: MySQL 8.0
- Build Tool: Maven
- Version Control: Git
- Testing: JUnit

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Maven 3.6 or higher
- MySQL 8.0 
- Servlet Container: Tomcat 9



