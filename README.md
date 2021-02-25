# pinboard-backend
Bulletin board app

#### Motivation
Project created during courses Projektowanie Aplikacji Internetowych and Organizacja i Rozwj projektw Open Source.

#### Tech & frameworks used
* Java 8
* Maven 3.3.9
* Spring Boot 2.2.7
* Hibernate 5.4.15
* PostgreSQL

#### Running app 

`mvnw spring-boot:run`

#### API

<!-- markdown-swagger -->
 Endpoint                             | Method | Auth? 
 ------------------------------------ | ------ | ----- 
 `/api/account`                       | GET    | No    
 `/api/account`                       | POST   | No    
 `/api/account/change-password`       | POST   | No    
 `/api/account/reset-password/finish` | POST   | No    
 `/api/account/reset-password/init`   | POST   | No    
 `/api/account/sessions`              | GET    | No    
 `/api/account/sessions/{series}`     | DELETE | No    
 `/api/activate`                      | GET    | No    
 `/api/authenticate`                  | GET    | No    
 `/api/images`                        | GET    | No    
 `/api/images`                        | POST   | No    
 `/api/images`                        | PUT    | No    
 `/api/images/{id}`                   | GET    | No    
 `/api/images/{id}`                   | DELETE | No    
 `/api/lat-lngs`                      | GET    | No    
 `/api/lat-lngs`                      | POST   | No    
 `/api/lat-lngs`                      | PUT    | No    
 `/api/lat-lngs/{id}`                 | GET    | No    
 `/api/lat-lngs/{id}`                 | DELETE | No    
 `/api/register`                      | POST   | No    
<!-- /markdown-swagger -->

#### License
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
