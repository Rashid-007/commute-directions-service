package com.commute.direction.infrastructure.gateway.googlemaps.api;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import com.commute.direction.cfg.SystemProperties;
import com.commute.direction.domain.GeoPoint;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GoogleMapsApiImp implements GoogleMapsApi {

    private SystemProperties systemProperties;
    private GeoApiContext geoApiContext;

    @Autowired
    public GoogleMapsApiImp(SystemProperties systemProperties, GeoApiContext geoApiContext) {

        this.systemProperties = systemProperties;
        this.geoApiContext = geoApiContext;
    }

    @Override
    public DirectionsResult getDirections(GeoPoint origin, GeoPoint destination, String mode) {

        DirectionsResult directionsResult = null;
        try {
            //@formatter:off
            directionsResult = DirectionsApi.newRequest(geoApiContext)
                    .origin(new LatLng(origin.getLat(), origin.getLng()))
                    .destination(new LatLng(destination.getLat(), destination.getLng()))
                    .mode(TravelMode.valueOf(mode.toUpperCase()))
                    .await();
            //@formatter:on
        } catch (ApiException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return directionsResult;
    }

    @Override
    public DirectionsResult getDirections(GeoPoint origin, GeoPoint destination, String mode, String departure) {

        DirectionsResult directionsResult = null;
        DateTime departureTime = DateTime.parse(departure);

        try {
            //@formatter:off
            directionsResult = DirectionsApi.newRequest(geoApiContext)
                    .origin(new LatLng(origin.getLat(), origin.getLng()))
                    .destination(new LatLng(destination.getLat(), destination.getLng()))
                    .mode(TravelMode.valueOf(mode.toUpperCase()))
                    .departureTime(departureTime)
                    .await();
            //@formatter:on
        } catch (ApiException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return directionsResult;
    }



}