Course Project 
================

--------------------------------------------------------------------------------------------------------------------
CS 474 Object Oriented Programming Language and Environment
--------------------------------------------------------------------------------------------------------------------

#Content.
Abstract
Introduction
How to Run
Result

##Abstract
Aim of this project is to build compile time error free code for java language. Production rules for java language is provided in a configuration file. Using these production rules and upper bounds for each cases we build a random java program. We have considered all basic production rules in java along with advanced rules of interfaces, inheritance and recusrsion

##Introduction
*All the production rules are restrictions are defined in configuration file. First step of program is reading configuration file and add data to database. Reader Package reads configuration file line by line and all the data is stored into package called as Data. Then using code generator package we create the tree whose leaves will be our final code.
###Reader package
*It reads each production rule. Production rule is first splitted on based ‘:=’
*This is stored in a hash map. LHS part forms the key. 
*RHS part is again splitted on basis of ‘|’ and each value is stored in a vector. Its upper and lower bound is noted and this value is passed as value of key.
    Example : Prod1:=example1|example2
    <Key, value>= <Prod1, 0-1>
    Where 0-1 represent range of values

###Data Package
*All the data needed for code generation from configuration file is stored in Data. 

###Generator Package
*First step is creation of Interfaces. This is done by InterfaceGenerator.
*MainGenerator generates classes. 
*Output of each generator is a tree. We have to pass rootnode, which is basically start symbol. Using production rules we expand root node, building the tree. Each branch terminates at leaf node which is basically the actual code.
*Once the tree is created we traverse from left to right and collect  all leaf nodes which will be the java code.

Final output will be stored in the file whose name is class name of public class.

##Features of JAVA code generated
*Contains Generic class
*Contains interfaces along with default methods
*Single public class
*Random number of classes which ranges from min and max value of classes mentioned in config file will be generated.
*Generator may choose to inherit classes
*Minimum 1 method will be  there for each class and number of methods depends on max value mentioned(This value is picked randomly)
*Field declarations
*Conditional statements
*Loop statements


##Gradle Build Instructions:
1. Traverse to the directory, where the Project is present.
2. From the Command prompt run ./gradlew build.
3. Run ./gradlew run.
4. Run ./gradlew Test

##Sbt Build Instructions:

###From Command Prompt
1. Traverse to the directory, where the Project is present.
2. Open the Command prompt Run sbt compile
3. Run sbt run

###From Sbt Shell - use the following commands
1. compile
2. run


