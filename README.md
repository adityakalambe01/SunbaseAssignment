# SunbasedataAssignment

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
           ├── Application.properties
           ├── Application-prod.properties
           └── Application-dev.properties
```


### How to run application
1. locate file SunbaseCustomersApplication.java and run it
