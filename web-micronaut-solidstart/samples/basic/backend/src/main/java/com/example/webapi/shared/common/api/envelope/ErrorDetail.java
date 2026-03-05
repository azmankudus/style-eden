package com.example.webapi.shared.common.api.envelope;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class ErrorDetail {
  private String code;
  private String message;
  private String detail;

  ErrorDetail() {
  }

  public ErrorDetail(final String code, final String message, final String detail) {
    this.code = code;
    this.message = message;
    this.detail = detail;
  }

  public String getCode() {
    return code;
  }

  public void setCode(final String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(final String message) {
    this.message = message;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(final String detail) {
    this.detail = detail;
  }
}
