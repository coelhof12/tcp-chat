# 🗨️ Multi-Client TCP Chat Server

![Project Cover](https://raw.githubusercontent.com/coelhof12/tcp-chat/refs/heads/main/assets/Repo_Covers.jpg)

## 💡 The Concept

A simple yet powerful multi-client chat server and client application built using Java and TCP sockets. This project demonstrates network programming concepts, concurrency handling, and seamless communication between clients.

## 🎥 TCP Chat Demo

<p align="left"> 
   <img width="920" height="600" src="https://raw.githubusercontent.com/coelhof12/tcp-chat/refs/heads/main/assets/tcp-chat_demo.gif"> 
</p>

## 📖 Introduction

This project was developed to implement a robust chat system where multiple clients can connect to a single server, exchange messages, and interact through various commands. It showcases essential skills in networking, concurrent programming, and software packaging using Maven.

## 🎯 Purpose

The goal of this project is to:

 - Build a practical example of a client-server architecture.
 - Demonstrate how to handle multiple connections using Java threads.
 - Provide hands-on experience with socket programming.

## 📊 Current Status

The project is fully implemented with the following features:

 - Message Broadcasting: Clients can send messages to everyone in the chat.
 - Command Handling: Commands such as /list, /whisper, and /name are supported.
 - Graceful Connection Management: The server and client handle connections and disconnections seamlessly.

Future enhancements:

 - Improve error handling for invalid commands.
 - Add more advanced commands like /mute or /kick.
 - Implement a graphical user interface (GUI).

## 📝 Key Features

 - Real-Time Communication: Messages are exchanged in real time across all connected clients.
 - Command System: Supports various commands such as:
    - ```/list```: Lists all the connected users.
    - ```/whisper <user> <message>```: Sends a private message to a specific user.
    - ```/name <new_user>```: Changes the username.
    - ```/quit```: Disconnects the client.
 - Scalable Connections: Handles multiple clients concurrently using a thread pool.

## 🛠️ Technologies Used

 - Java: Core programming language for both the server and client.
 - TCP Sockets: For reliable communication between clients and server.
 - Maven: For dependency management and JAR packaging.
 - Multithreading: For concurrent client connections.

## 🚀 Setup and Usage

### Prerequisites
  - Java 11 or higher installed.
  - Maven installed for building the project.
### Runing the Server 
1. Clone the repository and navigate to the server folder.
2. Package the server insto a JAR file:
  ```mvn package```
3. Run the server using the JAR file:
  ```java -jar target/server-1.0-SNAPSHOT-jar-with-dependencies.jar <port>```
Replace ```<port>``` with the desired port number (default: 8090).
### Runing the Client
1. Navigate to the client folder:
```cd ../client```
2. Package the client into a JAR file:
```mvn package```
3. Run the client using the JAR file:
```java -jar target/client-1.0-SNAPSHOT-jar-with-dependencies.jar <host> <port>```
Replace ```<host>``` with the server's IP address and <port> with the port number used by the server.

## 📝 Commands

| Command        | Description                                   |
|----------------|-----------------------------------------------|
| `/list`        | Lists all connected clients.                 |
| `/whisper`     | Sends a private message to another user.     |
| `/name`        | Changes your username.                       |
| `/quit`        | Exits the chat.                              |
| `/help`        | Displays available commands.                 |


## ⚖️ Legal Notice

This project is for educational purposes only. Use responsibly and with proper permissions.
