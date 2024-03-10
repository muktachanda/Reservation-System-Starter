# Reservation Service

This repository contains a simple application that provides a ticket reservation service for flights and helicopters, It could be extended if you would want to.

It contains:
  * Entities for various types of air-travel e.g., 
    descriptions of planes (``flight.reservation.plane.*``), 
    airports (``flight.reservation.Airport``), and 
    flights and schedule (``flight.reservation.flight.*``)
  * Customer (``flight.reservation.Customer``) and passenger (``flight.reservation.Passenger``) entities
  * Classes for 
    the reservation (``flight.reservation.order.*``)
    and payment (``flight.reservation.payment.*``)
    of tickets
    
The functionality currently includes:
  * Creating flights from one airport to another with a certain aircraft 
  * Scheduling flights for a specific date and time.
  * Booking a reservation for a customer on a specific scheduled flight 
  * Processing the payment (by credit card or PayPal) and closing/finalizing the order.

There is no interface for the service yet. Instead, test were written for:
  * Scheduling flights (on unit level)
  * Booking scenarios (on acceptance level)

### Background story

This prototype version of a reservation service has all functionality it needs right now. 
However, the quality of the code is not very good, and it gets hard to maintain and extend. 


## Task - refactoring

The task is to Find flaws and refactor the code using **design patterns**.
You can add, change, move classes, interfaces, and methods to enhance maintainability and extendability.
Make sure to mention how the refactoring helped using *code snippets*.

**This part onwards is not important for the class activity, but would be important later, since the activity would be extended as Assignment 2**

### Procedure

1. Create a fork of this repository.
2. Refactor/extend the code.
  * Javadoc and extensive error handling can be disregarded for the purpose of this exercise.
  * Adjust the test code so that all tests succeed after refactoring (*Optional, but do ensure refactoring does not break build*).
3. Explain the refactoring done, along with respective design pattern followed in a report.

## How to build and run

### Required technologies
To run the application, the following should be installed:
* [git](https://git-scm.com/downloads)
* JDK (e.g., [AdoptOpenJDK](https://adoptopenjdk.net/))
* [Apache Maven](https://maven.apache.org/install.html)

### Run from source

In the command line run the following: 

```
git clone https://github.com/Sidx-sys/Reservation-System-Starter.git
# or use SSH link if SSH is configured 
cd Reservation-System-Starter
```

This project has no interface yet.
You can run the tests to see if the application can be executed and still behaves as intended.

In the command line, run the following:

``./mvnw clean install``
