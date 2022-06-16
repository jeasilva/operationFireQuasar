package com.operation.quasar.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Getter
@NoArgsConstructor
@PropertySource("classpath:application.yml")
public class SatelliteProperties {


    private Integer number;

    private String[] positions;
}
