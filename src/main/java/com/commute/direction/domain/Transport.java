package com.commute.direction.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Transport {
    private TravelMode mode;
    //Suggest vehicles
}