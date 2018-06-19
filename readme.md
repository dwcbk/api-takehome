# api-takehome project
This is the api-takehome project implemented in Java.

## Building
Requirements: 
- Java 8
- Maven 3.x

## Running
This project contains an embedded Tomcat instance. To run, simply run this command from the project root folder  
`mvn tomcat7:run`

User location matches (when a user is in the same location as a friend as the same date & time), for
 the purposes of this project, must have the exact same latitude and longitude, and the location timestamps must match 
 down to the minute (seconds are ignored).

## User and Relationship data
Currently the users and relationships data comes the following files and can't be changed after Tomcat has started 
- `src/main/resources/users.json`
- `src/main/resources/relationships.json`

## Scalability improvements
[Read more](scalability.md)