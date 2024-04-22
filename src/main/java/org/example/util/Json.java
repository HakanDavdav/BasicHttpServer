package org.example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.awt.image.RasterFormatException;

public class Json {
    private static ObjectMapper objectMapper = new ObjectMapper();


    public void defaultObjectMapper(){
        objectMapper = new ObjectMapper();
    }

    public JsonNode objectIntoTree(Object object){
        return objectMapper.valueToTree(object);
    }

    public <A> A treeIntoObject(JsonNode jsonNode, Class<A> clazz) throws JsonProcessingException {
            return objectMapper.treeToValue(jsonNode,clazz);
    }

    public String treeIntoJson(JsonNode jsonNode) throws JsonProcessingException {
        ObjectWriter objectWriter = objectMapper.writer();
        return objectWriter.writeValueAsString(jsonNode);
    }

    public JsonNode jsonIntoTree(String json) throws JsonProcessingException {
        return objectMapper.readTree(json);

    }

    public String objectIntoJson(Object object) throws JsonProcessingException {
        ObjectWriter objectWriter = objectMapper.writer();
        return objectWriter.writeValueAsString(object);
    }





}
