package com.commute.direction.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@ToString
@Getter
public class Arrival {
    private String address;
    private GeoPoint location;
}
