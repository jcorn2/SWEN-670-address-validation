package io.example.rest.controller;

import io.example.rest.service.ParsingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zones.MapPoint;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
public class MainController {
    private static final String JSON_GEOCODE = "http://www.mapquestapi.com/geocoding/v1/address?key=UMSAjF5lBczBQcjA7IbstEJs1l4e7I4V&location=";

    @Autowired
    private ParsingService parsingService;

    @GetMapping("/geocode")
    public LinkedHashMap main(@RequestParam(name = "address", required = true) String address) throws Exception {
        String mapquestUrl = JSON_GEOCODE + address;
        // send request for lat and long values from mapquest api
        LinkedHashMap response = (LinkedHashMap) parsingService.parse(mapquestUrl);

        // extract lat and long values from nested return object
        ArrayList<Object> results = (ArrayList<Object>) response.get("results");
        LinkedHashMap topResult = (LinkedHashMap) results.get(0);
        ArrayList<Object> locations = (ArrayList<Object>) topResult.get("locations");
        LinkedHashMap addressInfo = (LinkedHashMap) locations.get(0);
        LinkedHashMap latLng = (LinkedHashMap) addressInfo.get("latLng");

        // Use MapPoint class to find zone for user's lat and long
        LinkedHashMap zoneCode = new LinkedHashMap<String, String>();
        MapPoint startApp = new MapPoint();
        try {
            List<String> zones = startApp.findZones((Double) latLng.get("lng"), (Double) latLng.get("lat"));
            zoneCode.put("zone", zones.get(0));
        } catch(Exception exc) {
            System.out.println(exc.getMessage());
            zoneCode.put("zone", "Failed to find zone");
        }

        return zoneCode;
    }
}
