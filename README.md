# Data Engineering Project
## Covid-19 Vaccines Side Effects

This project was developped during the last year of our master degree in software development and data engineering.
This project aims to help us understand what kind of technologies are employed by data enginers and how to use these to create, transfer, process and analysing large amounts of data throught several exercices. 

## Project group
The group in charge of the Covid-19 Vacinnes Side Effects project was composed by the following members :
* [Pierre-Jean BESNARD](https://github.com/PJbesnard)
* [Louis BILLAUT](https://github.com/LouisBillaut)
* [Jeanne CROHARE](https://github.com/jcrohare)
* [Thomas JUILLARD](https://github.com/JUILLARD-Thomas)


## Available features
First, run `CreateDatas`to create the `lubm1_extended`file
### Integrate the RDF data in the pipeline (Ex1)
In this exercise we developed a simple kafka producer with which we send persons in our LUBM1-extension dataset who has been vaccinated. 
#### Run
 - Run `ConsumerAvroAge`
 - Run `SendDataAge`
### Anonymised transactions (Ex2)
In this exercise we developed a consumer that consumes data in our Ex1's stream and a producer that resend the same data without first names and last names in another topic.
#### Run
- Run `ConsumerAvroProducerAnonymous`
- Run `SendData`

### A first consumer for a specific side effect (Ex3)
In this exercise, we developed a consumer that only displays entries with a sider code equals to C0027497 with a simple String comparison. 
#### Run
- Run `ConsumerAnonymous`
- Run `SendData`

### Counting side effects (Ex4)
In this exercise, we developed a consumer that reads our anonymised stream and class data using a Map to class each sider effects.

#### Run
- Run `ConsumerCountingSideEffect`
- Run `SendData`
### Counting side effects per vaccine (Ex5)
In this exercise, we developed a group of consumer that reads multiple topics filled by a producer that send our anonymised data. The producers class data using a Map to class the different sider effects of each vaccine.

#### Run
- Run `ConsumerVaccineGroup`
- Run `SendDataByGroup`
### Counting side effects per vaccine and age intervals (Ex6)
In this exercise, we extended the Ex5's consumers and join results with the static part of our data (LUBM1 extension RDF file).
#### Run
- Run `ConsumerVaccineGroupAndAge`
- Run `SendDataByGroup`


## Difficulties encountered

No major difficulties has been encountered during the development of this project. However understanding the environment and the data engineering tools took us an unexpected amount of time.
There is a lack of online resources about some tools that has been used, therefore, sometimes it was a bit complicated to find several code examples.
## Sources

* [Apache Zookeeper](https://zookeeper.apache.org/)
* [Apache Kafka](https://kafka.apache.org/)
* [Apache Jena](https://jena.apache.org/)
* [Bijection-Avro](https://mvnrepository.com/artifact/com.twitter/bijection-avro)
* [JavaFaker](https://github.com/DiUS/java-faker)
* [Jackson](https://mvnrepository.com/artifact/org.codehaus.jackson)
* [Apache Spark](https://spark.apache.org/)
