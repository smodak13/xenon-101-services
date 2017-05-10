$ mvn clean install

$ java -jar ./target/xenon-101-services-0.0.1-SNAPSHOT-jar-with-dependencies.jar
curl -H "Content-Type: application/json" -X POST -d '{"num1"="21", "num2"="7"}' http://127.0.0.1:8000/samples/add-numbers
curl -H "Content-Type: application/json" -X POST -d '{"id":"123456789","firstName":"John","lastName":"Doe","zipCode":"12345"}' http://127.0.0.1:8000/samples/student-roster

