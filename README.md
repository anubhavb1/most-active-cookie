# Most active cookie analyzer
The Most Active Cookie Analyzer is a command-line Java application that processes a cookie log file and identifies the most active cookie for a specified day.

## Tech

- Java 17, Spring Boot (framework), Maven (build tool)

## Requirements

Given a cookie log file in the following format:

~~~
cookie,timestamp
AtY0laUfhglK3lC7,2018-12-09T14:19:00+00:00
SAZuXPGUrfbcn5UA,2018-12-09T10:13:00+00:00
5UAVanZf6UtGyKVS,2018-12-09T07:25:00+00:00
AtY0laUfhglK3lC7,2018-12-09T06:19:00+00:00
SAZuXPGUrfbcn5UA,2018-12-08T22:03:00+00:00
4sMM2LxV07bPJzwf,2018-12-08T21:30:00+00:00
fbcn5UAVanZf6UtG,2018-12-08T09:30:00+00:00
4sMM2LxV07bPJzwf,2018-12-07T23:30:00+00:00
~~~

The program requires two command-line parameters:

-f: Specifies the path to the cookie log file.
-d: Specifies the date (in UTC timezone) to analyze, formatted as YYYY-MM-DD.

e.g. To find the most active cookie on December 9, 2018:

~~~
$ java -jar most-active-cookie.jar -f cookie_log.csv -d 2018-12-09
~~~

And it would write to stdout(assuming the current version of the cookie log file):

~~~
AtY0laUfhglK3lC7
5UAVanZf6UtGyKVS
~~~

We define the most active cookie as one seen in the log the most times during a given day.

## Assumptions

- If multiple cookies meet that criteria, please return all of them on separate lines.
- Only use additional libraries for testing, logging and cli-parsing.
- You can assume -d parameter takes date in UTC time zone.
- You have enough memory to store the contents of the whole file.
- Cookies in the log file are sorted by timestamp (most recent occurrence is the first line of the file).

## Build

Navigate to the application directory and use Maven to compile and package the source code into an executable JAR file:

~~~
mvn clean package
~~~

## Run

From the application directory, execute the following command to run the Most Active Cookie Analyzer:

~~~
java -jar target\most-active-cookie-<version>-SNAPSHOT.jar -f csv-file-path -d selected-date
~~~

- Example:

~~~
java -jar target\most-active-cookie-0.0.1-SNAPSHOT.jar -f src\logs\cookie_log.csv -d 2018-12-09
~~~

Alternatively, IDE run config can be modified to add the [parameters](https://ibb.co/2N6mKWN).

[Run Successful](https://ibb.co/mhMCYf6)
