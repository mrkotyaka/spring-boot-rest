# Task: Authorization Service

## Description

Today, you will implement a user authorization service based on login and password. The key to this task is how your application will respond to errors that your service will throw in different cases.

To get started, you need to prepare several classes.

**Step 0**. Create a Spring Boot application and make all controller, service, and repository classes binaries in your application context.

**Step 1**. The authorization request will be sent to the controller:

```java
@RestController
public class AuthorizationController {
    AuthorizationService service;
    
    @GetMapping(“/authorize”)
    public List<Authorities> getAuthorities(@RequestParam(“user”) String user, @RequestParam(“password”) String password) {
        return service.getAuthorities(user, password);
    }
}
```

**Step 2.** The class service that will process the entered login and password looks like this:

```java
public class AuthorizationService {
    UserRepository userRepository;

    List<Authorities> getAuthorities(String user, String password) {
        if (isEmpty(user) || isEmpty(password)) {
            throw new InvalidCredentials(“User name or password is empty”);
        }
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(user, password);
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser(“Unknown user ” + user);
        }
        return userAuthorities;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }
}
``` 
It accepts the login and password and returns permissions for that user if such a user is found and the data is valid. If the data sent is incorrect, then InvalidCredentials is thrown:

```java
public class InvalidCredentials extends RuntimeException {
    public InvalidCredentials(String msg) {
        super(msg);
    }
}
``` 

If your repository did not return any permissions or returned an empty collection, then an UnauthorizedUser error is thrown:

```java
public class UnauthorizedUser extends RuntimeException {
    public UnauthorizedUser(String msg) {
        super(msg);
    }
}
``` 

The enum with permissions looks like this:

```java
public enum Authorities {
    READ, WRITE, DELETE
}
``` 

**Step 3.** You need to implement the getUserAuthorities method in the UserRepository class, which returns either permissions or an empty array.

```java
public class UserRepository {
    public List<Authorities> getUserAuthorities(String user, String password) {
        return ...;
    }
}
``` 

To check if it works, you can make a request from your browser by filling in `<USERNAME>` and `<USERPASSWORD>` with your test data:

localhost:8080/authorize?user=<USERNAME>&password=<USERPASSWORD>

**Step 4.** Now that you have all the code ready, you need to write error handlers for errors thrown by the `AuthorizationService` service.

Requirements for error handlers:

* For `InvalidCredentials`, it must send back an HTTP status with code 400 and a body in the form of a message from the exception to the client.
* For `UnauthorizedUser`, it must send back an HTTP status with code 401 and a body in the form of a message from the exception to the client and write a message from the exception to the console.
 
