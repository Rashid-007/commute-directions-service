package com.commute.direction.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Journey {

    private Distance distance;
    private Duration duration;
}
