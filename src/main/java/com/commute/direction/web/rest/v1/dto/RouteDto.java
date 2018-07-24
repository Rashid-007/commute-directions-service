package com.commute.direction.web.rest.v1.dto;

import com.commute.direction.domain.RouteSegment;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RouteDto {
  @ApiModelProperty(required = true, value = "id", position = 1)
  @JsonProperty(value = "id", required = true)
  private UUID id;
  @ApiModelProperty(required = true, value = "Route segment", position = 2)
  @JsonProperty(value = "segment")
  private RouteSegment segment;

}
