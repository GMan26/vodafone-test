# Vodafone Test
For this test there are 2 methods for this application to communite with the in-memory h2 database.
These controllers are: 
  - Web
  - REST
# Table of Contents
1. [Web Controller](##web-controller)

1.1. [Add](###add-mobile-number)

1.1.1. [Storage - Saving](####saving)

1.2. [Get](###get-all-mobile-numbers)

1.3. [Get with criteria](###get-all-mobile-number-with-criteria)

1.4. [Update Mobile Services](###changing-mobile-service )

1.5. [Delete Mobile Services](###deleting-mobile-number )

1.6. [Mobile Number Format](####mobile-number-format)

1.7. [Number Service Type](####number-service-type)

2. [REST](##rest-controller)

2.1 [Select All](###select-all-mobile-numbers)

2.2 [Select with cretia](###select-mobile-number-with-creitra)

2.2 [Add,Update & Delete](###add,update-&-delete)

3. [The Minor Errors](##the-minor-errors)

4. [Thank you](#thank-you)

### Prerequisite
#### Run with Arguments
```
mvn clean install
mvn spring-boot:run
mvn eclipse:eclipse
mvn archetype:create 
  -DgroupId=net.viralpatel.spring
  -DartifactId=SpringRest
  -DarchetypeArtifactId=maven-archetype-webapp
  ```
 ## 1. Web Controller
### CLI Menu

![N|Solid](http://i64.tinypic.com/2ppleg5.png)]
1. Displays the full database of all clients, if no clients exists a status 200 error will be displayed
2. This will display a certain number from a given criteria
3. Adds Mobile to database, if client already exists a status 409 will appear
4. Change Mobile Plan
5. This will delete a given mobile number from the database 

### 1.1 Add Mobile Number
If the mobile has already been added, it will return a status code of 409

#### 1.1.1 Saving
After all these parameters have been verified, they will be stored in a constructor. Which contains:
auto-incrment id, msisdn, owner id, user id, ServiceType(Enum), time

#### In order to **__save__** the parameters to the database :
```
http://localhost:8080/saveMobileNumber
```
For example, if 2 numbers have been added through the CLI Menu, visit this site twice
```
http://localhost:8080/saveMobileNumber
```
This will then store the Information gathered to the UserModel which contains the same parameters as the consurtor above.

Result:
![N|Solid](http://i65.tinypic.com/2s1ls1f.png)


### 1.2 Get all Mobile Numbers
One may see the all the actions performed on the database with the use of :
```
http://localhost:8080/findall
```
If no mobile numbers exists in the database, it will return an http status code 200

![N|Solid](http://i63.tinypic.com/2gvoscm.png)


### 1.3 Get all Mobile Number withe criteria
One may see the all the actions performed on the database 
![N|Solid](http://i68.tinypic.com/k15q4x.png)
with the use of :
```
http://localhost:8080/getMobileNumber?msisdn=35699999992
```
![N|Solid](http://i67.tinypic.com/23rwdhs.png)
If no mobile numbers exists in the database, it will return a http status code 404

### 1.4 Changing Mobile Service 
![N|Solid](http://i67.tinypic.com/hv1klh.png)
#### Changing Mobile Service Issues
There is still one thing unsolved whilst changing service type.
It is something very simple and is documented within the code, there must be something tiny which i am missing out on
The issue is with
```
    @Query("UPDATE UserModel u SET u.service_type=:service_type WHERE u.msisdn=:msisdn")
```
In order for this function to keep on working, i had to apply another method, which removes then re-adds the data. 
Doing this is NOT advisded as this method would then change the auto_incremented ID of the mobile number

### 1.5 Deleting Mobile Number
![N|Solid](http://i64.tinypic.com/262xs80.png)
Once the site is visited, it will delete the mobile number from the database
If no mobile number is found, it will return a status error 404

  - Type some Markdown on the left
  - See HTML in the right
  - Magic

#### 1.6 Mobile Number Format
The format for the mobile numbers uses a independently called 
```
com.googlecode.libphonenumber
```
This will automatically format the mobile number to your country code
If the mobile number entered does not corresponds to the format an error will display to re-enter the value
#### 1.7 Number Service Type
These Service Type's are created with the use of Enums, as instructed
```
MOBILE_PREPAID
MOBILE_POSTPAID
```

## 2. REST Controller
Due to a couple of time constrains this week, i could not complete all the service with the use of REST.
The REST Controller also provides the required HTTP Status Errors.
In order to save Mobile Numbers, to use for the REST Services, please follow the CLI Menu - Add Mobile Number instructions, which may be found above.
Remember, to save mobile numbers, first add it using the CLI Menu, then visit
```
http://localhost:8080/saveMobileNumber
```
### 2.1 Select All Mobile Numbers
![N|Solid](http://i64.tinypic.com/1zvbdyh.png)

### 2.2 Select Mobile Number with creitra
![N|Solid](http://i63.tinypic.com/14vpb7r.png)

### 2.3 Add,Update & Delete
The code for these has been implemented inside the source code however, they have not been tested due to an error:
```
Request method 'GET' not supported"
```
I have tried it with both. The traninalion method : @RequestMapping and method (POST, PUT,GET) & the predefined method @PUTMapping and so on

## 3. The Minor Errors
There are two minor errors inside this application

The first error is when trying to update the database through the web controller. Which led to another method to be use. More information by be found above  [Update Mobile Services Issue](####changing-mobile-service-issues). However the Contractor update is successfull

The second is a "Request method 'GET' not supported" error for the Update,Delete & Add for the REST Controller.
The code for these methods are inside the code, they just have not been tested

# 4. Thank you
Thank you very much for your time, I hope this test was good enough to make it to get next round :)
