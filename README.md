# Simple cron parser


## Description

Cron parser is able to read input argument provided on program execution. 
Given argument is expected to be string cron expression with at least 
6 space separated, smaller expressions. Example input: <br />
``"*/15 0 1,15 * 1-5 /usr/bin/find .``

After space separating we receive smaller cron expressions:  
`*/15` as minutes  
`0` as hours  
`1,15` as days of month  
`*` as months  
`1-5` as days of week  
`/usr/bin/find .` as command  

The goal of program is to print table which shows execution times of given 
cron expression in following format:  
`minute 0 15 30 45`  
`hour 0`  
`day of month 1 15`  
`month 1 2 3 4 5 6 7 8 9 10 11 12`  
`day of week 1 2 3 4 5`  
`command /usr/bin/find`  


## Use cases/features
1. Supported characters: *  */  /  -   ,
2. Basic exception handling. Program is able to recognise and give information to user 
if whole expression is invalid or contains unsupported character. It also validates range 
of given expression (f.e. if seconds are not bigger than 60).
3. Print timetable in format mentioned in description.
4. Not supported characters (to implement): ?, L, W, #, special strings (like @daily).

## Running instructions

### Prerequisites
Java installed - you can download and get installation instructions from https://www.oracle.com/pl/java/technologies/downloads/#jdk19-mac

### Program run
Run commands: 
1. ```./gradlew build``` - run default gradle build task, compile java and build jar package
with manifest pointing to Main class
2. ``java -jar build/libs/CronExpressionParser-1.0-SNAPSHOT.jar **"0/5 13,18 * * * /usr/bin/find"**``   
run program with you custom cron expression

## Owner
Kamil Bargiel