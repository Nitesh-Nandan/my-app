package com.ms.web;

import com.ms.common.constants.APIPath;
import com.ms.common.exception.RequestParamsException;
import com.ms.common.model.request.SMSMessageRequest;
import com.ms.common.model.request.response.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;

@Slf4j
@RestController
@RequestMapping(value = APIPath.BASE_PATH)
public class SMSController {

    @PostMapping(value = APIPath.SMS.MESSAGE)
    public ResponseEntity message(@RequestBody @Valid SMSMessageRequest smsMessageRequest, Errors errors) {

        log.info("Request =  {}", smsMessageRequest);
        try {
            if (errors.hasErrors()) {
                throw new RequestParamsException(errors.getAllErrors());
            }
            log.info("Sender = {}, Body = {}, Time = {}", smsMessageRequest.getSender(), smsMessageRequest.getMessage(), smsMessageRequest.getTime());
            return ResponseEntity.ok().body("Success");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new GenericResponse<>(null, Collections.singletonList(ex.getMessage())));
        }

    }

}
