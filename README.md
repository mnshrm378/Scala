# ScalaApps
This repository contains scala/spark applications
This repository contains sample data

Instructions
1. FirstApp(Main Class)--Wordcount of a txt file located in project folder, using spark and scala- saves output within project folder
2. SecondApp--Wordcount of a txt file located in project folder, using spark and scala -Prints 10 lines from output
3. ThirdApp-- Counts occurences of letters in a file (within project folder) and prints the output.
4. FourthApp--SparkStreaming example application, that monitors "netcat on port 2222", works on msgs every seconds and does a wordcount
5. FifthApp-- Reads files (before markings and after markings, converts them to pair RDD with line number as Key and line as the value,subtracts RDD2 from RDD1, saves the resultant data. 
It then also uses spark's data frames to read a csv file and convert the content to JSON.
6. SixthApp -- Extracts metadata from markings data and also extracts specific rows those are column headings for particular kind of data.
More to be added..

Steps to setup Eclipse and Spark to run applications locally.
-------------------------------------------------------------

Utilities and Jars required
---------------------------
