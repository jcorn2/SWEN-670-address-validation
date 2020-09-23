package io.example.rest.controller;

import io.example.rest.service.ParsingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@RestController
public class MainController {
    private static final String JSON_GEOCODE = "http://www.mapquestapi.com/geocoding/v1/address?key=";

    @Autowired
    private ParsingService parsingService;

    @GetMapping("/geocode")
    public LinkedHashMap main(@RequestParam(name = "address", required = true) String address) {
        String url = JSON_GEOCODE + address;
        LinkedHashMap response = (LinkedHashMap) parsingService.parse(url);
        ArrayList<Object> results = (ArrayList<Object>) response.get("results");
        LinkedHashMap topResult = (LinkedHashMap) results.get(0);
        ArrayList<Object> locations = (ArrayList<Object>) topResult.get("locations");
        LinkedHashMap addressInfo = (LinkedHashMap) locations.get(0);
        LinkedHashMap latLng = (LinkedHashMap) addressInfo.get("latLng");
        return latLng;
    }
}
