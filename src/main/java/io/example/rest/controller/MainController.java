package io.example.rest.controller;

import io.example.rest.service.ParsingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@RestController
public class MainController {
    private static final String JSON_GEOCODE = "https://maps.googleapis.com/maps/api/geocode/json?address=317+Carriage+Run+Road,+Annapolis,+MD,+21403&key=<insert key here>";

    @Autowired
    private ParsingService parsingService;

    @GetMapping("/geocode")
    public LinkedHashMap main() {
        LinkedHashMap response = (LinkedHashMap) parsingService.parse(JSON_GEOCODE);
        ArrayList<Object> results = (ArrayList<Object>) response.get("results");
        LinkedHashMap topResult = (LinkedHashMap) results.get(0);
        LinkedHashMap geometry = (LinkedHashMap) topResult.get("geometry");
        LinkedHashMap latAndLong = (LinkedHashMap) geometry.get("location");
        return latAndLong;
    }
}
