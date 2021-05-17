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
public class Clouds {

    @JsonProperty("all")
    private Integer all;

}
