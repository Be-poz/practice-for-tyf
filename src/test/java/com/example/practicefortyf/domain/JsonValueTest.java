package com.example.practicefortyf.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonValueTest {

    @Test
    public void asdf() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonValue jsonValue = new JsonValue();
        jsonValue.setName("kang");
        String json = mapper.writeValueAsString(jsonValue);
        System.out.println(json);
    }

}