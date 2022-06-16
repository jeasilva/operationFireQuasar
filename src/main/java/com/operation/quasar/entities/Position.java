package com.operation.quasar.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL, content = JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Position {

    @JsonProperty("x")
    private Double x;
    @JsonProperty("y")
    private Double y;

    public Position(double[] points){
        this.x = points[0];
        this.y = points[1];
    }

    @Override
    public String toString() {
        return x + "," + y;
    }
}
