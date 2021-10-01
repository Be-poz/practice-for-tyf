package com.example.practicefortyf.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ProfileYamlTest {

    @Autowired
    private ProfileYaml profileYaml;

    @Test
    @DisplayName("Value 어노테이션을 이용한 yml 파일 읽기")
    public void getYamlProperties() {
        assertThat(profileYaml.getKey()).isNotNull();
    }
}