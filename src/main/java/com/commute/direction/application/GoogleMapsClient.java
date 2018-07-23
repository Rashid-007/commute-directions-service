package com.commute.direction.application;

import com.commute.direction.domain.GeoPoint;
import com.commute.direction.domain.RouteSegment;
import com.commute.direction.domain.TransportType;

public interface GoogleMapsClient {
    RouteSegment getWalkingDirections(GeoPoint origin, GeoPoint destination);

    RouteSegment getTransitDirections(GeoPoint origin, GeoPoint destination, String departure);

    RouteSegment getDrivingDirections(GeoPoint origin, GeoPoint destination, TransportType transportType);
}
