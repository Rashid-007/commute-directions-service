package com.commute.direction.application;

import com.commute.direction.domain.GeoPoint;
import com.commute.direction.domain.Route;
import com.commute.direction.domain.RouteSegment;
import com.commute.direction.domain.TransportType;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DirectionApplicationService {

    @NonNull
    private GoogleMapsClient googleMapsClient;

    public Route getRoutes(GeoPoint origin, GeoPoint destination, TransportType transportType) {

        Route route = null;

        switch (transportType){
            case CAR:
            case MOTORCYCLE:
            case BIKE:
                RouteSegment drivingDirections = googleMapsClient.getDrivingDirections(origin, destination, transportType);
                route = Route.builder()
                        .id(UUID.randomUUID())
                        .segment(drivingDirections)
                        .build();
                break;
            case PUBLIC:
                RouteSegment transitDirections = googleMapsClient.getTransitDirections(origin, destination, DateTime.now().toString());
                route = Route.builder()
                        .id(UUID.randomUUID())
                        .segment(transitDirections)
                        .build();
                break;

            case WALKING:
                RouteSegment walkingDirections = googleMapsClient.getWalkingDirections(origin, destination);
                route = Route.builder()
                        .id(UUID.randomUUID())
                        .segment(walkingDirections)
                        .build();
                break;
            case DEFAULT:
        }
        return route;
    }

}
