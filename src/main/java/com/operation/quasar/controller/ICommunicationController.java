package com.operation.quasar.controller;

import com.operation.quasar.entities.TopSecretRequest;
import com.operation.quasar.entities.TopSecretResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ICommunicationController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TopSecretResponse> generateMessage(@RequestBody TopSecretRequest request);

}
