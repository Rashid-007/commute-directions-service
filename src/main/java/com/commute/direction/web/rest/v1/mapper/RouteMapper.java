package com.commute.direction.web.rest.v1.mapper;

import com.commute.direction.domain.Route;
import com.commute.direction.web.rest.v1.dto.RouteDto;

public class RouteMapper {

  public static RouteDto getDto(Route route){
    RouteDto dto = RouteDto.builder().id(route.getId())
      .segment(route.getSegment())
      .build();

    return dto;
  }
}
