# Scalability Improvements
Here are some ways to improve the perormance and scalability of this application

## User Location DAO
The [User Location DAO](src/main/java/com/dwcbk/dao/UserLocationDaoImpl.java) dao performs two functions:  
- Search for matching friends' locations 
- Add reported user locations to a database

### Search matching locations
This is one of the biggest bottlenecks in the the app. Currently user locations are stored in a brute-force fashion -- simply as a java `List<>` of [UserLocation](src/main/java/com/dwcbk/beans/UserLocation.java) objects.
Every time you search for "matching locations" for a user, the full dataset is traversed multiple times, which is incredibly
inefficient and unnecessary. This can be improved in multiple ways:  
- Index User Locations in a DB that supports geo-spatial searches natively, such as Elastic
- For even more scalability, shard the data by segmenting by lat/long location. For example, each city might be a separate
shard. Or in a city such as NYC with tons of users, segment further by borough, etc.
- Since User Locations can only be added and are never modified, all user locations more than a few minutes in the past
can be searched for matching friends' locations and the results cached for quick retrieval later. If a user adds or 
removes a friend, the index will need to be updated.   

### Adding reported user locations 
In addition to the previously noted huge inefficiency with storing user locations in a simple list, the function of adding
reported user locations is currently done synchronously:
1. User Location "add" POST request sent
2. User Location is added to DB (synchronous)
3. OK response is sent to browser  

This can be improved by making this an asynchronous operation:
1. User Location "add" POST request sent
2. Do basic validation on input params (valid User id, lat/long values)
3. Add data to "add location" processing queue (synchronous)
4. Return OK response
5. (asynchronous) a separate, dedicated service (perhaps using Kafka) processes the "add location" queue

This improvement will be critical since thousands of users reporting their locations once every second will 
generate lots of data and lots of requests. 