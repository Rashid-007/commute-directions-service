package com.commute.direction.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
public class Route {

    private UUID id;
    private RouteSegment segment;
    private int transfers;
    private Duration duration;

    private Departure departure;
    private Arrival arrival;

    private List<Fare> fares;

}
