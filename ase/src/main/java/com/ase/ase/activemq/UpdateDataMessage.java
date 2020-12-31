package com.ase.ase.activemq;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

@JsonSerialize
public class UpdateDataMessage implements Serializable {

  private boolean update;

  public UpdateDataMessage(boolean update) {
    this.update = update;
  }

  @JsonProperty("update")
  public boolean update() {
    return update;
  }

  private String toJson() {
    try {
      return new ObjectMapper().writeValueAsString(this);
    } catch (JsonProcessingException e) {
      System.out.println("Caught: " + e);
      e.printStackTrace();
    }
    return "";
  }

  @Override
  public String toString() {
    return toJson();
  }
}
