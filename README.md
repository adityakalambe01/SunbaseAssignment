
# Sunbase Assignment

### Project Structure

```css
src
 └── main
      ├── java
      │    └── com
      │         ├── config
      │         │    ├── AppConfig.java
      │         │    └── SecurityConfig.java
      │         ├── controller
      │         │    ├── AuthController.java
      │         │    ├── CustomerController.java
      │         │    ├── HomeController.java
      │         │    └── Redirect.java
      │         ├── model
      │         │    ├── Customer.java
      │         │    ├── User.java
      │         │    ├── JwtRequest.java
      │         │    └── JwtResponse.java
      │         ├── repository
      │         │    ├── UserRepository.java
      │         │    └── CustomerRepository.java
      │         ├── security
      │         │    ├── JwtAuthenticationEntryPoint.java
      │         │    ├── JwtAuthenticationFilter.java
      │         │    └── JwtHelper.java
      │         ├── service
      │         │    ├── CustomerService.java
      │         │    ├── CustomUserDetailsService.java
      │         │    └── UserService.java
      │         └── SunbaseCustomersApplication.java
      └── resources
           ├── templates
           │    ├── login.html
           │    ├── customerList.html
           │    └── addCustomer.html
           ├── application.properties
           ├── application-prod.properties
           └── application-dev.properties
```


### Setting the Active Profile

To save data on the MySQL server, ensure the production profile is active:

- In `application.properties`, set:
  ```properties
  spring.profiles.active=${ENV:prod}
  ```

To use the in-memory database, modify the `application.properties` file to set the development profile:

- In `application.properties`, set:
  ```properties
  spring.profiles.active=${ENV:dev}
  ```

### Steps to Run Application Using MySQL Driver

1. Create a database with the name `sunbase_assignmnt`.
2. Turn on annotation processing in your IDE (e.g., IntelliJ IDEA or Eclipse).
3. Locate the file `SunbaseCustomersApplication.java` and run it as a Java application.

### Accessing the Pages

1. **Login Page**: [http://localhost:9090](http://localhost:9090)
2. **Customer Page**: [http://localhost:9090/user/customers](http://localhost:9090/user/customers)
