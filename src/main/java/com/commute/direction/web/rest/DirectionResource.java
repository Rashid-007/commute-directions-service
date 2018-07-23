package com.commute.direction.web.rest;

import com.commute.direction.application.DirectionApplicationService;
import com.commute.direction.domain.GeoPoint;
import com.commute.direction.domain.Route;
import com.commute.direction.domain.TransportType;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api/v1")
public class DirectionResource {

    private static final String ROUTING_DEFUAULT_MODE = "default";

    private DirectionApplicationService directionApplicationService;

    @Autowired
    public DirectionResource(DirectionApplicationService directionApplicationService) {
        this.directionApplicationService = directionApplicationService;
    }

    @ApiOperation(value = "Calculates routes based on query parameters")
    @RequestMapping(value = "/routes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

    public ResponseEntity<Route> calculateRoute(

            @ApiParam(value = "A Geopoint with comma separated latitude and longitude i.e 52.515380,13.391013") @RequestParam(value = "origin",
                    required = true) String origin,

            @ApiParam(value = "A Geopoint with comma separated latitude and longitude i.e 52.515380,13.391013") @RequestParam( value = "destination", required = true) String destination,

            @ApiParam(value = "Transport type for the route. DEFAULT will provide routes for all types.", allowableValues = "CAR, BIKE, MOTORCYCLE, PUBLIC, WALKING") @RequestParam(value = "transport",
                    defaultValue = ROUTING_DEFUAULT_MODE) String transportType) {

        Route directionsResult = directionApplicationService.getRoutes(GeoPoint.parse(origin), GeoPoint.parse(destination), TransportType.getValue(transportType));

        return ResponseEntity.ok(directionsResult);
    }
}
