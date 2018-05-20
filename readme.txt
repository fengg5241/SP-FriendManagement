1. Description
   - This sample is using SpringBoot.
       -- Choose SpringBoot because it will build project much faster, just add dependence by maven, no need of creating boilerplate configuration, 
        and the "spring-boot-devtools" improve development efficiency because it make autorestart server on code/config updates
   - FriendController --> FriendService -->FriendDao(JdbcTemplate) 
   - All Exceptions are handled in GlobalExceptionHandler using ControllerAdvice
   - Error messages and error code are declared in errors_msg.properties
   - The API server is deployed on the Google platform http://35.198.250.48.
   - Create Junit test class for positive case.
   - Please find db design detail from sp_friend.sql
   
2. How to run
  Take retrieveFriends as example
   http://35.198.250.48/friend-0.0.1-SNAPSHOT/retrieveFriends?email=a@gmail.com

3. Test APIs
   import SP-Friend.postman_collection.json file into PostMan plugin and test.
   For connect , subscribe and block API, if passded email does not exist in DB, I will create uesr with this email first,
   then create relationship, so I don't provide seperated API to create user by email.
   
   
4.Issue
   1) Based on my understanding, for block API
   if they are connected as friends, then "andy" will no longer receive notifications from "john"
   My logic is if "andy" block "john", andy cannot subscribe john anymore.So if try to call subscribe API will see the error message.
   
   2)For 6. As a user, I need an API to retrieve all email addresses that can receive updates from an email address.
   I don't understand what does this mean "has been @mentioned in the update"? and how to use "text" request parameter in 
   {
     "sender":  "john@example.com",
     "text": "Hello World! kate@example.com"
   },
   So I just ingore this case first, if possible to explain this requirement i will implememt later.
