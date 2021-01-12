# jwt-jpa-security
 Springboot jwt authentication with JPA database!
 
 ## Starting server
 Open pom.xml as a project in IntelliJ and navigate to JwtJpaSecurityApplication and click the green arrow next to the method, or use CMD and navigate to the project home directory and run `mvnw spring-boot:run`
 
 Once the server is up and running, it is accessable on `http://localhost:8080/` you have several API's to call to.
 
## API calls
 
 ### /add (POST)
 Expects *application/json* in the Body of form:
 
 `{
    "username":"user",
    "password":"password",
    "roles":"USER"
}`

 API, if successful will return:
 `{
    "id": 1,
    "username": "user",
    "password": "The BCrypted password",
    "active": true,
    "roles": "USER"
}`

 ### /authenticate (POST)
 Used to get a token to access other endpoints
 Expects *application/json* in the Body of form:
 `{
    "username": "user",
    "password": "password"
}`
and if login is successful, will return:
`{
    "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjA5NDYxNDA2LCJpYXQiOjE2MDk0NTc4MDZ9.1al6dm7A9BEk-bn73rRCNxk6mobU6pPCKlgyWkAoy20"
}`
This is your token to pass to other endpoints for access. The token is valid for one hour.

### /save (POST)
Used to save data for a user into the mongoDB repository. The data that is saved is pre-set on the server end.
Expects the jwt in the header `Authorization, *token*`

### /find (GET)
Used to get example data for a user from the mongoDB database
Expects the jwt in the header `Authorization, *token*`

 
 ## Accessing JPA database through H2 console
 Navigate to `http://localhost:8080/h2-console` and login using 
 username: `sa`
 password: `password`
 
 *Note: You can change the default username and password in resources/application.properties
  
 ## Accessing MongoDB database repository:
 The mongoDB database is currently in test mode on my school email. If you need to change/modify/access something please contact me.
 

