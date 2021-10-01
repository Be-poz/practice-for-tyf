package com.example.practicefortyf.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProfileYaml {

    @Value("${person.name}")
    private String name;

    @Value("${person.age}")
    private String age;

    @Value("${person.weight}")
    private String weight;

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getWeight() {
        return weight;
    }
}
