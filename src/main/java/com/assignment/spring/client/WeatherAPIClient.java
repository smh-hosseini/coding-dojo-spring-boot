package com.assignment.spring.client;

import com.assignment.spring.client.model.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "weather",url = "#{appConfig.apiUrl}", configuration = WeatherClientConfiguration.class)
public interface WeatherAPIClient {

    @GetMapping("/weather")
    WeatherResponse getByCity(@RequestParam("q") String city, @RequestParam("appid") String appId);
}
