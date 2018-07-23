package com.commute.direction.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Duration {

    /** The time duration, in minutes. */
    private long inSeconds;

    private String text;
}