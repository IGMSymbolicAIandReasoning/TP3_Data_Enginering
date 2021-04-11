# Data Engineering Project
## Covid-19 Vaccines Side Effects

This project was developped during our the last year of our master degree in software development and data engineering.
This project aims to help us understand what kind of technologies are employed by data enginers and how to use these to create, transfer, process and analysing large amounts of data throught several exercices. 

## Project group
The group in charge of the Covid-19 Vacinnes Side Effects project was composed by the following members :
* [Pierre-Jean BESNARD](https://github.com/PJbesnard)
* [Louis BILLAUT](https://github.com/LouisBillaut)
* [Jeanne CROHARE](https://github.com/jcrohare)
* [Thomas JUILLARD](https://github.com/JUILLARD-Thomas)


## Availables features
First, run `CreateDatas`to create the `lubm1_extended`file
### Integrate the RDF data in the pipeline (Ex1)
 - Run `ConsumerAvroAge`
 - Run `SendDataAge`
### Anonymized transactions (Ex2)
- Run `ConsumerAvroProducerAnonymous`
- Run `SendData`
### A first consumer for a specific side effect (Ex3)
- Run `ConsumerAnonymous`
- Run `SendData`
### Counting side effects (Ex4)
- Run `ConsumerCountingSideEffect`
- Run `SendData`
### Counting side effects per vaccine (Ex5)
- Run `ConsumerVaccineGroup`
- Run `SendDataByGroup`
### Counting side effects per vaccine and age intervals (Ex6)
- Run `ConsumerVaccineGroupAndAge`
- Run `SendDataByGroup`

## Sources

* [Apache Zookeeper](https://zookeeper.apache.org/)
* [Apache Kafka](https://kafka.apache.org/)
* [Apache Jena](https://jena.apache.org/)
* [Bijection-Avro](https://mvnrepository.com/artifact/com.twitter/bijection-avro)
* [JavaFaker](https://github.com/DiUS/java-faker)
* [Jackson](https://mvnrepository.com/artifact/org.codehaus.jackson)
* [Apache Spark](https://spark.apache.org/)
