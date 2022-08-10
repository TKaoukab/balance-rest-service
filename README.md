# balance-rest-service
API and front-end code to be used for opening a new “current account” of already existing customers.

## Notes about the application :

balance-rest-service is a spring boot application, proposing an API with two end-points, and  front-end basic screens to help test the API :
### 1- API
- **GET "/customer/{customerId}"** : takes a path parameter _{customerId}_ and output the user's information showing : Name, Surname, balance, and transactions of the accounts.
- **POST "/account/{customerId}?initialCredit={value}"** takes a path parameter _{customerId}_ and a query parameter _"initialCredit"_. Once the endpoint is called, a new account will be opened connected to the user whose ID is customerID. Also, if _"initialCredit"_ is not "0" (zero), a transaction will be sent to the new account.

### 2- Front-end
The front-end is developped using [thymeleaf](https://www.thymeleaf.org/) and basic HTML5 code. It proposes two screens :

* _"index.html"_ : which represents home screen. this screen allows to search a customer by id.
* _"customerList.html"_ : It displays a users details, and handles accounts creation for the corresponding customer.

To simplify the applications deployment and tests, the front-end shares the spring-boot application with the API. For entreprise-level architecture, the front-end would be set on a diffrent spring boot application, calling the API from a remote server. That's said, I simulate this loose coupling by calling the API as I would have done from a remote server.

## Features

- Existing customers list is not hard coded, it can be modified in the _"application.properties"_ file
- Input values are validated by format :
--   **_"customerId"_** is an **integer** type entry
--   **_"initialCredit"_** is a **double** type entry
- the application considers entreprise-level architecture : layers, abstractions and testability
- the application propose a basic front-end to simplify the API's testing
- Unitary service testing
- Integration testing using

## Technical stack

balance-rest-service uses a number of open source projects to work properly:

- [Java-8](https://www.oracle.com/fr/java/technologies/javase/javase8-archive-downloads.html)
- [Spring-boot-2.7.2](https://spring.io/projects/spring-boot)
- [JUnit-5.8.2](https://junit.org/junit5/) : (included in spring-boot dependecies)
- [Mockito-4.5.1](https://site.mockito.org/) : (included in spring-boot dependecies)
- [Tomcat-9.0.65](https://tomcat.apache.org/) : (included in spring-boot dependecies)
- [Maven](https://maven.apache.org/)
-[thymeleaf](https://www.thymeleaf.org/)

And of course balance-rest-service itself is open source with a [public repository](https://github.com/TKaoukab/balance-rest-service)  on GitHub.

## Configuration
the application's configuration file **_application.properties_"" contains two keys :
* customer.list : contains a list of "existing customers". The customers are separated by ";" and the fields of each customer are separated by "|". This key is initialized with two customers, feel free to add/remove users to the list.
* local.url     : it's the url used by 

## Installation & use
### Using IDE (Eclipse, Intellij)
The best way to get into the source code is to have an IDE and import the spring boot balance-rest-service. I personnaly use Eclipse, and I will try to guide you through it.

If you choose to proceed this way, you will have to :
 * download the [Spring tool suite](https://marketplace.eclipse.org/content/spring-tools-4-aka-spring-tool-suite-4). 
 * checkout the source code from [public repository](https://github.com/TKaoukab/balance-rest-service) : two ways to do this : 
 * start the spring boot application : the main class is **"_AssignementApplication_"**.

### Using command line
To launch the application from command line, you will need Maven (and Hava since maven is a java tool). If you don't have a Maven installation, you can get started here : [Maven guide](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)

Once you have the JDK 8 and Maven installed, you can build and start the application with the command : 
```sh
cd {balance-rest-service_path} # go to the source folder of the application
mvn spring-boot:run            # compile and run the application
```

## License
**Free Software, Hell Yeah!**