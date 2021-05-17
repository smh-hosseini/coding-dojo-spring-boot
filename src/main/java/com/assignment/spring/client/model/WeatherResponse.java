package com.assignment.spring.client.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;

@Data
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class WeatherResponse {
    @JsonProperty("coord")
    private Coord coord;
    @JsonProperty("weather")
    private List<Weather> weather = null;
    @JsonProperty("base")
    private String base;
    @JsonProperty("main")
    private Main main;
    @JsonProperty("visibility")
    private Integer visibility;
    @JsonProperty("wind")
    private Wind wind;
    @JsonProperty("sys")
    private Sys sys;
    @JsonProperty("clouds")
    private Clouds clouds;
    @JsonProperty("dt")
    private Integer dt;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("cod")
    private Integer cod;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

}
