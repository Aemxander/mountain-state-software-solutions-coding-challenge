# Mountain State Software Solutions Coding Challenge
The purpose of this repository is to upload my code for the Mountain State Software Solutions coding challenge.

This coding challenge is for an interview for the Jr. Software Engineer position.



## Tech
The project uses two open source projects to work properly:

* [Java 15]
* [SQLiteJDBC]



## Overview
The goal of the challenge is to read in a CSV file into Java, insert valid records into a SQLite database, place all of the other records into another CSV file, and to create a log file with statistics about the records.

My approach was to iterate over each line in the CSV file, split the line by commas since I am using a CSV file, check to see if the line has the proper number of columns, place successful records into the SQLite database, and place failed records into another CSV file.

I also tried to keep the results the same by deleting old data each time the program is ran. Whenever you run the program it deletes the old SQLite table and failed records CSV file to prevent any old data from carrying over to the new files.



## Installation
The source code is uploaded to this repository as an Eclipse Java project. Even though it is an Eclipse Java project it can easily be loaded into any Java IDE using the same instructions.

Download the source code as a ZIP file, extract the folder from the ZIP file, and then import the extracted folder into your IDE as an existing Java project.



## Usage
To run the project through your Java IDE you have to run Main.java.

The console will prompt you to enter the filename of the file you want to use. There is a default file in the folder that can be used by entering:
```sh
ms3Interview.csv
```

To run the program using a different file, place the file in the same folder where ms3Interview.csv is and enter your filename when prompted.



## Author
Alex Mello - [GitHub](https://github.com/Alex-E-Mello)



## License
MIT







[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

   [Java 15]: <https://www.oracle.com/java/technologies/javase/jdk15-archive-downloads.html>
   [SQLiteJDBC]: <https://github.com/xerial/sqlite-jdbc>