package com.assignment.spring.client.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({
        "additionalProperties"
})
@JsonPropertyOrder({
    "speed",
    "deg"
})
public class Wind {

    @JsonProperty("speed")
    private Double speed;
    @JsonProperty("deg")
    private Integer deg;
}
