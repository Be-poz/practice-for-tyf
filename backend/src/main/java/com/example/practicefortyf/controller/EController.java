package com.example.practicefortyf.controller;

import com.example.practicefortyf.dto.ErrorRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.Binding;
import javax.validation.Valid;

@RestController
public class EController {

    @PostMapping("/valid")
    public String asdf(@Valid @RequestBody ErrorRequest errorRequest, Errors error) {
//        error.rejectValue();
        return "asdf";
    }
}
