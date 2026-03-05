package com.example.webapi.shared.common.api.envelope;

import java.util.List;
import java.util.Map;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class ResponseEnvelope<T> {

  private T data;
  private List<ErrorDetail> errors;
  private Map<String, Object> meta;

  public ResponseEnvelope() {
  }

  // Success Factory
  public static <T> ResponseEnvelope<T> success(final T data) {
    final ResponseEnvelope<T> envelope = new ResponseEnvelope<>();
    envelope.setData(data);
    return envelope;
  }

  // Success with Meta Factory
  public static <T> ResponseEnvelope<T> success(final T data, final Map<String, Object> meta) {
    final ResponseEnvelope<T> envelope = new ResponseEnvelope<>();
    envelope.setData(data);
    envelope.setMeta(meta);
    return envelope;
  }

  // Error Factory
  public static <T> ResponseEnvelope<T> error(final List<ErrorDetail> errors) {
    final ResponseEnvelope<T> envelope = new ResponseEnvelope<>();
    envelope.setErrors(errors);
    return envelope;
  }

  // Error with Meta Factory
  public static <T> ResponseEnvelope<T> error(final List<ErrorDetail> errors, final Map<String, Object> meta) {
    final ResponseEnvelope<T> envelope = new ResponseEnvelope<>();
    envelope.setErrors(errors);
    envelope.setMeta(meta);
    return envelope;
  }

  public T getData() {
    return data;
  }

  public void setData(final T data) {
    this.data = data;
  }

  public List<ErrorDetail> getErrors() {
    return errors;
  }

  public void setErrors(final List<ErrorDetail> errors) {
    this.errors = errors;
  }

  public Map<String, Object> getMeta() {
    return meta;
  }

  public void setMeta(final Map<String, Object> meta) {
    this.meta = meta;
  }
}
