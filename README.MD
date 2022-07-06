# Kafka Producer

## Requirements
- Need to have a Kafka application running in your local machine or in a server with http access.
- Java 11+

## Kafka

If you want to run kafka in your local machine, please, follow the instrauctions that are in the [link](https://kafka.apache.org/quickstart).

Use the following commando to create a topic.
```shell
#Create a topic with 10 partitions
./bin/kafka-topics.sh --create --replication-factor 1 --partitions 10 --topic avenuecode-partitions --bootstrap-server localhost:9092
```
```shell
#Describe the topic you created
./bin/kafka-topics.sh --describe --topic avenuecode-partitions --bootstrap-server localhost:9092
```