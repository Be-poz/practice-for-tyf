package com.example.practicefortyf.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorRequest {

    @Positive
    private int value;
}
