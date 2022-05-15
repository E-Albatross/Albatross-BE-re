package com.albatross.bareungeulssi.domain.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.List;

@Converter
public class StringListConverter implements AttributeConverter<List, String> {

    private static final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES, false);
    //DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false: field가 없어도 exception 발생X
    //DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES, false: primitive 자료형의 데이터가 없어도 exception 발생X

    @Override
    public String convertToDatabaseColumn(List attribute) {
        try {
            return mapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List convertToEntityAttribute(String dbData) {
        try {
            return mapper.readValue(dbData, List.class);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }
}
