package br.com.avenuecode.kafka;

import br.com.avenuecode.kafka.callback.KafkaCallback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class MainProducer {

    private static final String TOPIC_NAME = "avenuecode-kafka";
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.serializer", LongSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());

        KafkaProducer<Long, String> producer = new KafkaProducer<>(properties);
        ProducerRecord<Long, String> record = new ProducerRecord<>(TOPIC_NAME, 123456L,"22.5726 N,88.3639 E");

        try{
            producer.send(record, new KafkaCallback());
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}
