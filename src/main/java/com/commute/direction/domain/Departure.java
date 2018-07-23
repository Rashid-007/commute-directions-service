package com.commute.direction.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@ToString
public class Departure {

    private LocalDateTime time;
    @Getter
    private String address;
    @Getter
    private GeoPoint location;
    @Getter
    private Transport transport;

    public String getTime(){
        return this.time.toString();
    }
}
