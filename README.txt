System Requirements
------------
1. Java 1.8
2. maven 3

Optional Setting changes
-----------------------------
1. In application.properties the following properties can be updated. Currently default properties are provided to test the app quickly.
    a. server.port
    b. google.maps.api.key

Instructions to start the server
-------------------------------
1. If code is checked out than from the command prompt at pom level run command -> mvn spring-boot:run
OR
if the code is checked out just run App.main() from IDEA.
OR
mvn clean install
java -jar target/shop-finder-1.0-SNAPSHOT.jar



Instructions to add a new shop
-----------------------------
1. Select Method = POST
2. Paste the URL http://localhost:8080/shop/add
3. Select Body -> raw -> JSON (application/json)
4. Paste the json request. Examples given below

Request 1
-----------
{
  "shopName": "ABC",
  "address": {
    "number": "195",
    "postcode": "SE16 2LW"
  }
}

Request 2
---------
{
  "shopName": "ABC",
  "address": {
    "number": "280",
    "postcode": "EC2M 4RB"
  }
}

4. Click Send to sent the request.

Instructions to find a nearest shop
----------------------------------
1. Select Method = GET
2. Paste the URL http://localhost:8080/shop/search?latitude=[LATITUDE]&longitude=[LONGITUDE]. Replace LATITUDE and LONGITUDE by appropriate values
    eg: http://localhost:8080/shop/search?latitude=51.5066&longitude=-0.0261
3. Click Send to send the request.
