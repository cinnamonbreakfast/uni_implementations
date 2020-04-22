# 📡 TCP Version

## Server 

This version features a Server-Client communication through a TCP connection between them. Every client request is handled by a [TcpServer.ClientHandler](https://github.com/cinnamonbreakfast/uni_implementations/blob/master/SDI/Movie%20Rental%20TCP/server/src/main/java/tcp/TcpServer.java#L41) which is a Thread(Runnable), so we get non-blocking operations.

The request built on a [:common:Message](https://github.com/cinnamonbreakfast/uni_implementations/blob/master/SDI/Movie%20Rental%20TCP/common/src/main/java/Message/Message.java) which enstablish the connection between the two entities. Inside the *Message* a *[MessageHeader](https://github.com/cinnamonbreakfast/uni_implementations/blob/master/SDI/Movie%20Rental%20TCP/common/src/main/java/Message/MessageHeader.java)* can be found, of type *enum*, used to describe the wanted operation.

**Repository**
Postgres repo is in use, in this case, where I also implemented a [SortingRepository](https://github.com/cinnamonbreakfast/uni_implementations/blob/master/SDI/Movie%20Rental%20TCP/server/src/main/java/repository/SortingRepository.java), where I can easily sort and filter the data by different criteria, thanks to [:server:sorting.Sort](https://github.com/cinnamonbreakfast/uni_implementations/blob/master/SDI/Movie%20Rental%20TCP/server/src/main/java/repository/sorting/Sort.java).
[:server:sorting.Sort](https://github.com/cinnamonbreakfast/uni_implementations/blob/master/SDI/Movie%20Rental%20TCP/server/src/main/java/repository/sorting/Sort.java) class is based on Java reflection.

## Client

On the other side of the window, non-blocking attribute is maintained in client to, by using CompletableFutures, messages being handled by [:client:ui.Console](https://github.com/cinnamonbreakfast/uni_implementations/blob/master/SDI/Movie%20Rental%20TCP/client/src/main/java/ui/Console.java). Services creates the requests, send them to server, wait for the desired answer and display the result.

## Common

Common module contains the interfaces and classes that are handled between the two sides, being server and client. Here we can find the [:common:Message](https://github.com/cinnamonbreakfast/uni_implementations/tree/master/SDI/Movie%20Rental%20TCP/common/src/main/java/Message), used for communication, the [Domain](https://github.com/cinnamonbreakfast/uni_implementations/tree/master/SDI/Movie%20Rental%20TCP/common/src/main/java/Domain), containing the entities, and finally [:common:Services](https://github.com/cinnamonbreakfast/uni_implementations/tree/master/SDI/Movie%20Rental%20TCP/common/src/main/java/service), containing the interfaces that assure the communication between the two parties.
