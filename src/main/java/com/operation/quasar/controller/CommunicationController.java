package com.operation.quasar.controller;

import com.operation.quasar.entities.TopSecretRequest;
import com.operation.quasar.entities.TopSecretResponse;
import com.operation.quasar.exceptions.LocationException;
import com.operation.quasar.exceptions.MessageException;
import com.operation.quasar.services.CommunicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/topsecret")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommunicationController implements ICommunicationController {

    private final CommunicationService service;

    @Override
    public ResponseEntity<TopSecretResponse> generateMessage(TopSecretRequest request) {
        try {
            return ResponseEntity.ok(service.generateMessage(request));
        } catch (MessageException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (LocationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
