package com.ms.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.common.constants.APIPath;
import com.ms.common.exception.RequestParamsException;
import com.ms.common.model.request.SMSMessageRequest;
import com.ms.common.model.request.response.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@Slf4j
@RestController
@RequestMapping(value = APIPath.BASE_PATH)
public class TelegramController {

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping(value = APIPath.TEL.MESSAGE, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity messages(SMSMessageRequest smsMessageRequest, Errors errors) {
        log.info("Telegram Request = {}", smsMessageRequest);

        try {
            if (errors.hasErrors()) {
                throw new RequestParamsException(errors.getAllErrors());
            }
            log.info("Telegram String = {}", objectMapper.writeValueAsString(smsMessageRequest.getMessage()));
            System.out.println("Out:- " + smsMessageRequest.getMessage());

            log.info("Sender = {}, Body = {}, Time = {}", smsMessageRequest.getSender(), smsMessageRequest.getMessage(), smsMessageRequest.getTime());
            return ResponseEntity.ok().body("Success");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new GenericResponse<>(null, Collections.singletonList(ex.getMessage())));
        }
    }
}
