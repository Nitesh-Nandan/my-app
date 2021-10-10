package com.ms.app.controller;

import com.ms.common.constants.APIPath;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping(value = APIPath.HEALTH_CHECK)
    @ResponseStatus(HttpStatus.OK)
    public String healthCheck() {
        return "success";
    }
}
