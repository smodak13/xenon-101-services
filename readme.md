/**
Build command
**/
$ mvn clean install

/**
Actual running the xenon sample host jar
**/
$ java -jar ./target/xenon-101-services-0.0.1-SNAPSHOT-jar-with-dependencies.jar

/**
Usage: curl command to add two numbers.
**/
curl -H "Content-Type: application/json" -X POST -d '{"num1"="21", "num2"="7"}' http://127.0.0.1:8000/samples/add-numbers
