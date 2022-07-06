package br.com.avenuecode.kafka.serializer;

import br.com.avenuecode.kafka.entity.TruckCoordinates;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

public class CustomSerializer implements Serializer<TruckCoordinates> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String s, TruckCoordinates truckCoordinates) {
        byte[] response = null;
        try {
            response = objectMapper.writeValueAsString(truckCoordinates).getBytes();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return response;
    }
}
