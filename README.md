## Rock, Paper, Scissors

[![Build Status](https://travis-ci.org/alesavin/rock-paper-scissors.svg?branch=master)](https://travis-ci.org/alesavin/rock-paper-scissors)

[Task description](TASK.md)

Application based on [Spring Boot](https://spring.io/projects/spring-boot), [JQuery](https://jquery.com/) + [Bootstrap](https://getbootstrap.com/) at frontend side. 
For security reasons web part work with HTTPS and handle session by [secure cookie](https://en.wikipedia.org/wiki/HTTP_cookie#Secure_cookie) (JSESSION).
Memory storage based on ConcurrentHashMap, so thread safe. 
Global statistics is eventual consistent, possible to receive inconsistent data 
at some point of time (total count of rounds != wins1 + wins2 + draws). Global statistics
 update every 5 sec at frontend side.

## Run
- ./mvnw test spring-boot:run (mvnw.cmd for Windows)
- goto https://localhost:8443 at browser, allow to work with self-signed cert (not for production)
- if need another session - open url in incognito mode or delete cookie. Or open it at another browser =)

## TODO
- add APIController tests
- add engine concurrent tests
- add logging
- simplify Solver function
- add code coverage, checks (findbugs)
- simplify javascript - add round stat at the end of list, so skip get /statistics and re-rendering all rounds data

## Notes
- all actions are synchronous there for simplicity, we can discuss actor-based solution 





