# 🧵 RMI Version

+Added config classes for both parties.

## Server 

Now the RMI assures the client with a service.

**Repository**
Postgres repo is in use, in this case, where I also implemented a [SortingRepository](https://github.com/cinnamonbreakfast/uni_implementations/blob/master/SDI/Movie%20Rental%20SpringBoot/server/src/main/java/repository/SortingRepository.java), where I can easily sort and filter the data by different criteria, thanks to [:server:sorting.Sort](https://github.com/cinnamonbreakfast/uni_implementations/blob/master/SDI/Movie%20Rental%20SpringBoot/server/src/main/java/repository/sorting/Sort.java).
[:server:sorting.Sort](https://github.com/cinnamonbreakfast/uni_implementations/blob/master/SDI/Movie%20Rental%20SpringBoot/server/src/main/java/repository/sorting/Sort.java) class is based on Java reflection.

## Client

Services contain an interface to the actual service present on server side.

## Common

Common module contains the interfaces and classes that are handled between the two sides, being server and client.

Other details inherit from [TCP Version](https://github.com/cinnamonbreakfast/uni_implementations/tree/master/SDI/Movie%20Rental%20TCP#-tcp-version).
