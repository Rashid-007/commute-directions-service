package com.commute.direction.infrastructure.gateway.googlemaps;

import com.commute.direction.domain.*;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.commute.direction.application.GoogleMapsClient;
import com.commute.direction.infrastructure.gateway.googlemaps.api.GoogleMapsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
@Service
public class GoogleMapsGateway implements GoogleMapsClient {
    private GoogleMapsApi api;

    @Autowired
    public GoogleMapsGateway(GoogleMapsApi api) {

        this.api = api;
    }

    @Override
    public RouteSegment getWalkingDirections(GeoPoint origin, GeoPoint destination) {
        DirectionsResult directionsResult = api.getDirections(origin, destination, TravelMode.WALKING.name());

        RouteSegment.RouteSegmentBuilder segmentBuilder = RouteSegment.builder();
        //@formatter:off
        segmentBuilder
                .id(UUID.randomUUID())
                .mode(TravelMode.WALKING);

        Transport transport = Transport.builder()
                .mode(TravelMode.WALKING)
                .build();
        //@formatter:on
        setSegmentFields(segmentBuilder, transport, directionsResult.routes[0]);

        return segmentBuilder.build();
    }

    @Override
    public RouteSegment getTransitDirections(GeoPoint origin, GeoPoint destination, String departure) {
        DirectionsResult directionsResult = api.getDirections(origin, destination, TravelMode.TRANSIT.name(), departure);

        RouteSegment.RouteSegmentBuilder segmentBuilder = RouteSegment.builder();

        //@formatter:off
        segmentBuilder
                .id(UUID.randomUUID())
                .mode(TravelMode.TRANSIT);

        Transport transport = Transport.builder()
                .mode(TravelMode.TRANSIT)
                .build();
        //@formatter:on
        setTransitSegmentFields(segmentBuilder, transport, directionsResult.routes[0]);

        return segmentBuilder.build();
    }

    @Override
    public RouteSegment getDrivingDirections(GeoPoint origin, GeoPoint destination, TransportType transportType) {
        DirectionsResult directionsResult = api.getDirections(origin, destination, resolveTravelMode(transportType));

        //@formatter:off
        RouteSegment.RouteSegmentBuilder segmentBuilder = RouteSegment.builder();
        segmentBuilder
                .id(UUID.randomUUID())
                .mode(TravelMode.getValue(resolveTravelMode(transportType)));

        Transport transport = Transport.builder()
                .mode(TravelMode.getValue(resolveTravelMode(transportType)))
                .build();
        //@formatter:on

        setSegmentFields(segmentBuilder, transport, directionsResult.routes[0]);
      RouteSegment segment = segmentBuilder.build();
      return segment;
    }

    private void setSegmentFields(RouteSegment.RouteSegmentBuilder segmentBuilder, Transport transport, DirectionsRoute route) {
        if (route.legs != null && route.legs.length == 1) {
            DirectionsLeg leg = route.legs[0];

            //@formatter:off
            Departure departure = Departure.builder()
                    .address(leg.startAddress)
                    .location(new GeoPoint(leg.startLocation.lat, leg.startLocation.lng))
                    .transport(transport)
                    .build();

            Journey journey = Journey.builder()
                    .distance(new Distance(leg.distance.inMeters, leg.distance.humanReadable))
                    .duration(new Duration(leg.duration.inSeconds, leg.duration.humanReadable))
                    .build();

            Arrival arrival = Arrival.builder()
                    .address(leg.endAddress)
                    .location(new GeoPoint(leg.endLocation.lat, leg.endLocation.lng))
                    .build();

            segmentBuilder.departure(departure)
                    .journey(journey)
                    .arrival(arrival)
                    .encodePolyLine(route.overviewPolyline.getEncodedPath());
            //@formatter:on
        }
    }

    private void setTransitSegmentFields(RouteSegment.RouteSegmentBuilder segmentBuilder, Transport transport, DirectionsRoute route) {
        if (route.legs != null && route.legs.length == 1) {
            DirectionsLeg leg = route.legs[0];

            //@formatter:off
            Departure departure = Departure.builder()
                    .address(leg.startAddress)
                    .location(new GeoPoint(leg.startLocation.lat, leg.startLocation.lng))
                    .transport(transport)
                    .build();

            Journey journey = Journey.builder()
                    .distance(new Distance(leg.distance.inMeters, leg.distance.humanReadable))
                    .duration(new Duration(leg.duration.inSeconds, leg.duration.humanReadable))
                    .build();

            Arrival arrival = Arrival.builder()
                    .address(leg.endAddress)
                    .location(new GeoPoint(leg.endLocation.lat, leg.endLocation.lng))
                    .build();

            segmentBuilder.departure(departure)
                    .journey(journey)
                    .arrival(arrival)
                    .encodePolyLine(route.overviewPolyline.getEncodedPath());
            //@formatter:on
        }
    }

    /** Maps myScotty vehicle to a Travel Mode in google for getting directions. */
    private String resolveTravelMode(TransportType type) {
        switch (type) {
            case CAR:
                return TravelMode.DRIVING.name();
            case MOTORCYCLE:
                return TravelMode.DRIVING.name();
            case BIKE:
                return TravelMode.BICYCLING.name();
            default:
                return null;
        }
    }
}
