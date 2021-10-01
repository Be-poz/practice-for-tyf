package com.example.practicefortyf.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProfileYaml {

    @Value("${bepoz.name}")
    private String key;

    public String getKey() {
        return key;
    }
}
