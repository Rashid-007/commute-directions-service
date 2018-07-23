package com.commute.direction.domain;

public enum TransportType {
    WALKING,

    /** Corresponds to Bike vehicle type.*/
    BIKE,

    /** Corresponds to Motorcycle vehicle type*/
    MOTORCYCLE,

    /** Corresponds to CAR vehicleType.*/
    CAR,

    /** Only public transport. */
    PUBLIC,

    /** All types or routes. */
    DEFAULT;

    public static boolean contains(String s){
        for(TransportType m : TransportType.values()){
            if(m.name().equalsIgnoreCase(s)){
                return true;
            }
        }
        return false;
    }
    public static TransportType getValue(String type){
        try{
            return TransportType.valueOf(type.toUpperCase());
        }catch (IllegalArgumentException|NullPointerException e){
            throw new IllegalArgumentException("Invalid transport type " + type);
        }
    }
}
