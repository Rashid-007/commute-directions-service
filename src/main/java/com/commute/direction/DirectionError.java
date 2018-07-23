package com.commute.direction;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum DirectionError implements ErrorCode{
    GEOPOINT_NOT_VALID(0, "The geo point is not valid"),
    MODE_NOT_VALID(1, "The mode is not valid");

    private final int codePrefix = 2;
    private int codeNumber;

    @Setter
    private String errorMessage;
}
