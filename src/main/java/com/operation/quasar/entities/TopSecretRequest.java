package com.operation.quasar.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL, content = JsonInclude.Include.NON_EMPTY)
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class TopSecretRequest {

    @JsonProperty("satellites")
    private List<Satellite> Satellite;
}
