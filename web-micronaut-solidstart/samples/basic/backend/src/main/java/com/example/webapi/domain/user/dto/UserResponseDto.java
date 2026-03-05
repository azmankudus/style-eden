package com.example.webapi.domain.user.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class UserResponseDto {

  private Long id;
  private String username;
  private String email;
  private String status;

  public UserResponseDto(final Long id, final String username, final String email, final String status) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getStatus() {
    return status;
  }
}
