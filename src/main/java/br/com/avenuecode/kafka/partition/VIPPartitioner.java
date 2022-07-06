package br.com.avenuecode.kafka.partition;

import br.com.avenuecode.kafka.entity.TruckCoordinates;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;

public class VIPPartitioner implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        List<PartitionInfo> partitionInfos = cluster.availablePartitionsForTopic(topic);
        TruckCoordinates truckCoordinates = (TruckCoordinates) value;
        if ((truckCoordinates.getLatitude().equals(37.2431) && truckCoordinates.getLongitude().equals(115.793)))
            return 5;
        return Math.abs(Utils.murmur2(keyBytes)%partitionInfos.size()-1);
    }

    @Override
    public void close() { }

    @Override
    public void configure(Map<String, ?> map) { }
}
