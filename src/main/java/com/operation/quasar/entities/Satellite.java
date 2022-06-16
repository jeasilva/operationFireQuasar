package com.operation.quasar.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL, content = JsonInclude.Include.NON_EMPTY)
@Getter
public class Satellite extends Spaceship {

    @JsonProperty("name")
    private String name;
    @JsonProperty("distance")
    private Double distance;
    @JsonProperty("message")
    private List<String> message;

    @Builder
    public Satellite(Position position, String name, Double distance, List<String> message) {
        super(position);
        this.name = name;
        this.distance = distance;
        this.message = message;

    }

}
