package br.com.avenuecode.kafka;

import br.com.avenuecode.kafka.callback.KafkaCallback;
import br.com.avenuecode.kafka.entity.TruckCoordinates;
import br.com.avenuecode.kafka.partition.VIPPartitioner;
import br.com.avenuecode.kafka.serializer.CustomSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongSerializer;

import java.util.Properties;

public class MainProducer {

    private static final String TOPIC_NAME = "avenuecode-partitions";
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CustomSerializer.class.getName());
        properties.setProperty(ProducerConfig.PARTITIONER_CLASS_CONFIG, VIPPartitioner.class.getName());
        properties.setProperty(ProducerConfig.ACKS_CONFIG, "all");
        properties.setProperty(ProducerConfig.BUFFER_MEMORY_CONFIG, "34343434");
        properties.setProperty(ProducerConfig.COMPRESSION_TYPE_CONFIG, "gzip");
        properties.setProperty(ProducerConfig.RETRIES_CONFIG, "2");
        properties.setProperty(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, "400");
        properties.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, "10243343434");
        properties.setProperty(ProducerConfig.LINGER_MS_CONFIG, "500");
        properties.setProperty(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, "200");
        properties.setProperty(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "truckCoordinates-transactions");

        KafkaProducer<Long, TruckCoordinates> producer = new KafkaProducer<>(properties);
        producer.initTransactions();
        TruckCoordinates truckCoordinates = new TruckCoordinates(123, 37.2431,115.793);
        ProducerRecord<Long, TruckCoordinates> producerRecord = new ProducerRecord<>(TOPIC_NAME, 123456L,truckCoordinates);
        try{
            producer.beginTransaction();
            producer.send(producerRecord);
            producer.commitTransaction();
        } catch (Exception e){
            producer.abortTransaction();
            e.printStackTrace();
        }finally {
            producer.close();
        }
    }
}
