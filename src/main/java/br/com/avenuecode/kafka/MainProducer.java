package br.com.avenuecode.kafka;

import br.com.avenuecode.kafka.callback.KafkaCallback;
import br.com.avenuecode.kafka.entity.TruckCoordinates;
import br.com.avenuecode.kafka.serializer.CustomSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongSerializer;

import java.util.Properties;

public class MainProducer {

    private static final String TOPIC_NAME = "avenuecode-kafka-custom";
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.serializer", LongSerializer.class.getName());
        properties.setProperty("value.serializer", CustomSerializer.class.getName());

        try(KafkaProducer<Long, TruckCoordinates> producer = new KafkaProducer<>(properties)){
            TruckCoordinates truckCoordinates = new TruckCoordinates(123, 123123.123123,123123.12312);
            ProducerRecord<Long, TruckCoordinates> producerRecord = new ProducerRecord<>(TOPIC_NAME, 123456L,truckCoordinates);
            producer.send(producerRecord, new KafkaCallback());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
