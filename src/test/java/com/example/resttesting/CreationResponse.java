package com.example.resttesting;

import java.util.Objects;

public class CreationResponse {


  public String status;

  public CreationResponse(String status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CreationResponse that = (CreationResponse) o;
    return Objects.equals(status, that.status);
  }

  @Override
  public int hashCode() {

    return Objects.hash(status);
  }

}
