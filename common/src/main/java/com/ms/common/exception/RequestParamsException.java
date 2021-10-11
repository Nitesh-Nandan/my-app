package com.ms.common.exception;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.validation.ObjectError;

@Getter
public class RequestParamsException extends RuntimeException {
    private final List<String> errors;

    public RequestParamsException(List<ObjectError> errorObjs) {
        super(errorObjs.toString());

        this.errors = new ArrayList<>();
        for (ObjectError error: errorObjs) {
            this.errors.add(error.getDefaultMessage());
        }
    }
}