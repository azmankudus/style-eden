package com.example.webapi.shared.common.api.exception;

import org.slf4j.helpers.MessageFormatter;

public class ApiException extends RuntimeException {
  private final String code;
  private final String messageKey;
  private final Object[] args;

  public ApiException(final String code, final String messageKey, final Object... args) {
    super(MessageFormatter.arrayFormat(messageKey, args).getMessage());
    this.code = code;
    this.messageKey = messageKey;
    this.args = args;
  }

  public String getCode() {
    return code;
  }

  public String getMessageKey() {
    return messageKey;
  }

  public Object[] getArgs() {
    return args;
  }
}
