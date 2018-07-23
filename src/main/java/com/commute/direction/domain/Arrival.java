package com.commute.direction.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@ToString
public class Arrival {

    private LocalDateTime time;
    @Getter
    private String address;
    @Getter
    private GeoPoint location;

    public String getTime(){
        return this.time.toString();
    }
}
