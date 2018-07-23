package com.commute.direction.infrastructure.gateway.googlemaps.api;

import com.google.maps.model.DirectionsResult;
import com.commute.direction.domain.GeoPoint;

public interface GoogleMapsApi {
    DirectionsResult getDirections(GeoPoint origin, GeoPoint destination, String mode);
    DirectionsResult getDirections(GeoPoint origin, GeoPoint destination, String mode, String departure);
}
