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

        KafkaProducer<Long, TruckCoordinates> producer = new KafkaProducer<>(properties);
        ProducerRecord<Long, TruckCoordinates> record = new ProducerRecord<>(TOPIC_NAME, 123456L,new TruckCoordinates(123, 123123.123123,123123.12312));

        try{
            producer.send(record, new KafkaCallback());
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}
