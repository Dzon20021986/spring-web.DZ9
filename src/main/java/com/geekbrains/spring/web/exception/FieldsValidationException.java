package com.geekbrains.spring.web.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FieldsValidationException {
    private List<String> errorFieldsMessage;

    public FieldsValidationException(List<String> errors) {
        this.errorFieldsMessage = errors;
    }
}
