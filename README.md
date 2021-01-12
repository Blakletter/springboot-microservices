
![Repository Logo](repository-open-graph-template_banner.png)

### Prequisites:
- MySql Community Server
- MySql Workbench (Not required but recommended)
- Java 8.0


### Microservice architecture design
To put it simply, there is no defined definition.
The way we are implementing it involves each service communicating through HTTP, which means each service runs on its own dedicated port.
Each service registers with a Eureka server, and when the client requests a particular service the Eureka server can pass along the right url to the client.
This is great, because it means you can load balance and spin up multiple of the same services, and then distribute the load across those services.

### Getting started
First, download MySql Server and Workbench (not required but recommended)
- [MySql Installer](https://dev.mysql.com/downloads/installer/)

Next download the project as a .zip or clone the repo to a directory of your choosing.
It is important to set up the MySql database exactly, or else Spring won't be able to save or get users.
You can import the attached [Database](test-database.sql) from the MySql workbench, and it will automatically set up the schema and tables correctly.

*Note:*
- Make sure SqlServer is running as a service. You can check-box this to happen automatically in the installer.
- The login for a default user is `username=root, passsword=password`

Once you have the MySql Server and database imported, run the main method in EurekaApplication.java in the Eureka service.
This will start the Eureka server. Then subsiquently start every other service so they can register with Eureka. 
You can view which services are registered with Eureka by navigating to http://localhost:8761/ or whichever port you ran Eureka server on.
Once all of the services are running, use something like postman to send and receive calls to/from the server. 

*Note: If you remove a user from MySql, it will not remove the mongoData for that user if there is any stored. You must call /savedata to save mongoData, but still, delete users with caution.
The mongoRepository is shared between all instances of the backend, whereas your MySql database is local. There could be conflicts while trying to save data for a user that already exists.*

The default structure for how the microservices are structured is below. Connecting arrows are dependencies/communications with other services.
If you wish to use the */mongo-access-layer* service, you must also have the */sql-access-layer* service running too.

*Note:* You can change the port of any service by navigating to */projectname/src/main/resources/application.properties* and changing the *server.port* value.


![Architecture](MicroServiceArch.jpg)


### Return types / Expected Values
#### /adduser
- Method=`POST`
- Content-Type=`application-json`
- Body=User.java
  `{
  "email":"email-here",
  "password":"password-here"
  }`

- Return:
  - HttpStatus=`201 (CREATED)`
  - Type=`Mono<ResponseEntity<Void>>`

#### /savedata (default data is pre-written for you)
- Method=`POST`
- Content-Type=`application-json`
- Header=`Authorization:jwt`
- Return:
  - HttpStatus=`201 (CREATED)`
  - Type=`Mono<ResponseEntity<Void>>`
  - Body=`{"jwt":"jwt-token"}`

#### /getdata
- Method=`GET`
- Content-Type=`application-json`
- Header=`Authorization:jwt`
- Return:
  - HttpStatus=`200 (OK)`
  - Type=`Mono<ResponseEntity<Object>>`
  - Body=`Object`

#### /authenticate
- Method=`POST`
- Content-Type=`application-json`
- Body=`{"email":"your-email-here", "password":"your-password-here"}`
- Return:
  - HttpStatus=`200 (OK)`
  - Type=`Mono<ResponseEntity<AuthenticationResponse>>`
  - Body=`{"jwt":"jwt-token"}`
