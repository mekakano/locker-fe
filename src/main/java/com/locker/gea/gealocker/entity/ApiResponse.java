package com.locker.gea.gealocker.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ApiResponse {
  @JsonProperty("statuscode")
  private int statuscode;

  @JsonProperty("message")
  private String message;

  @JsonProperty("object")
  private Object object;

}
