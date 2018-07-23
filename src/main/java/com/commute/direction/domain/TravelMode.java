package com.commute.direction.domain;

public enum TravelMode {
    DRIVING,
    WALKING,
    BICYCLING,
    TRANSIT;

    public static TravelMode getValue(String value){
        try{
            return TravelMode.valueOf(value);
        }catch (IllegalArgumentException ex){
            throw new IllegalArgumentException("Invalid travel mode" + value);
        }
    }
}
