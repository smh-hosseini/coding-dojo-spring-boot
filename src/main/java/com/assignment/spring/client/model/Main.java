package com.assignment.spring.client.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;

@Data
@NoArgsConstructor
@JsonInclude(NON_NULL)
@JsonIgnoreProperties({
        "additionalProperties"
})
@JsonPropertyOrder({
    "temp",
    "pressure",
    "humidity",
    "temp_min",
    "temp_max"
})
public class Main {

    @JsonProperty("temp")
    private Double temp;
    @JsonProperty("pressure")
    private Integer pressure;
    @JsonProperty("humidity")
    private Integer humidity;
    @JsonProperty("temp_min")
    private Double tempMin;
    @JsonProperty("temp_max")
    private Double tempMax;

}
