Hello,
Here's my implementation
Sorry there is an implementation issue while invoking - it's something to do with POST and text/csv content type
The CSV is not being propagated properly - some special characters get injected - dont know why!

Implementation notes
1. A separate Enrichment and product look up service to distribute load and to make sure that the products are
loaded in a separate JVM on a separate microservice
2. Unit tests written in Spock/Groovy
Thanks for reading my code!

How to run
curl --data-binary @test2.csv -H "Content-Type: text/csv"  "http://localhost:8080/enrich"

Sample input trade file content:
20210601,1,EUR,3.5
20230530,2,USD,4.5
