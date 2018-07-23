package com.commute.direction.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Distance {

    /** The numeric distance, in meters. */
    private long inMeters;

    /**
     * The human-friendly distance. This is rounded and in an appropriate unit for the request.
     * The units can be overridden with a request parameter.
     */

    private String text;

/*  public String toString() {
    return String.format("%f %s", ((double)inMeters)/1000.0, humanReadable);
  }*/

}
