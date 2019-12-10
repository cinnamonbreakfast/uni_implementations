# Toy Language Interpreter 🪀

* About
* How does it work
* Updates
* Future updates

## About

This is an assignment for Advanced Programming methods where we mostly write projects in Java (but C# and Scala as well). This project, as the title says, is a Toy Language interpreter, based only on Java, starting with basic statements and types up until multithreading and heap. Beside basic implementations from my UNI assignments, I am planning to lead this project to a certain point where everybody can compile code in terminal using any IDE. The assignments are based on hard-coded examples and not using any kind of file handling & compiling.

The main idea of this project is to exercise Java skills where the scenario is other that a basic management system for people, cars, et cetera.

## How does it work

Trying to avoid getting deeper into technical terms and stuff, this interpreter _handles_ a class, named **ProgramStatement** which contains instructions/statements to be executed, as well as a repo for code, ADTs, Heap, SymbolTable and so on. **IStatement** class is built, most of the time, like a binary tree, or more exactly in a _"telescopic"_ form, so we can achieve a well-linked syntax, executed step-by-step (in depth).

**_TBA_**: Interpreting a file, custom syntax, compiling to binary.

## Updates

### 1.0 - Beginning
* ProgramState
* Repository
* ADTs
* Types
* Values
* Basic statements and expressions

### 1.1 - Things are getting serious
* Added file handling
* Added execution log to log files
* Log on each step (debugging)
* Logical Expressions and Boolean Type & Value

### 1.2 - What the actual f...
* Added Ref type (pointer in Heap)
* Ref can Ref ref a Ref that refs a Ref that refs a ...
* Heap handling functions (new, read, write)
* Garbage collecter
* While statement

### 1.3 - Why
* Fork statement, multi-threading
* New structure for Controller, ProgramState, statements
