package com.commute.direction.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class RouteSegment {

    private UUID id;
    private TravelMode mode;
    private Departure departure;
    private Journey journey;
    private Arrival arrival;
    private String encodePolyLine;
}

