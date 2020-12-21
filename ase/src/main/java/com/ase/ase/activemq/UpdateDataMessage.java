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

  @Override
  public String toString() {
    try {
      return new ObjectMapper().writeValueAsString(this);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return null;
  }
}
