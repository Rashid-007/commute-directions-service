package com.commute.direction.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Fare {
    private String segment_id;
    private String provider;
    private Currency currency;
    private double price;
    private double estimated;
}
