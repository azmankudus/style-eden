package com.example.webapi;

import io.micronaut.context.annotation.Property;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@MicronautTest
@Property(name = "application.include-metadata", value = "true")
class ApiContractMetadataTest {

  @Inject
  @Client("/api/v1")
  HttpClient client;

  @Test
  void testClientRequestWithMetadataToServerResponseOkWithMetadata() {
    final Map<String, Object> payload = Map.of(
        "data", Map.of(
            "username", "janedoe",
            "email", "jane@example.com"),
        "meta", Map.of(
            "client_trace_id", "trace-123",
            "client_request_timestamp", "2026-03-05T10:00:00Z"));

    final HttpResponse<Map> response = client.toBlocking().exchange(
        HttpRequest.POST("/users", payload), Map.class);

    assertEquals(HttpStatus.CREATED, response.getStatus());
    assertNotNull(response.body());
    assertTrue(response.body().containsKey("data"));
    assertTrue(response.body().containsKey("meta"));

    final Map<String, Object> meta = (Map<String, Object>) response.body().get("meta");
    assertEquals("trace-123", meta.get("server_request_trace_id"));
    assertNotNull(meta.get("server_response_duration"));
  }

  @Test
  void testServerResponseErrorWithMetadata() {
    final Map<String, Object> payload = Map.of(
        "data", Map.of(
            "username", "admin", // triggers error
            "email", "admin@example.com"),
        "meta", Map.of(
            "client_trace_id", "trace-error-123"));

    try {
      client.toBlocking().exchange(HttpRequest.POST("/users", payload), Map.class);
      fail("Should have thrown HttpClientResponseException");
    } catch (final HttpClientResponseException e) {
      assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
      final Map responseEntity = e.getResponse().getBody(Map.class).orElse(null);
      assertNotNull(responseEntity);
      assertTrue(responseEntity.containsKey("errors"));
      assertTrue(responseEntity.containsKey("meta"));

      final Map<String, Object> meta = (Map<String, Object>) responseEntity.get("meta");
      assertEquals("trace-error-123", meta.get("server_request_trace_id"));
    }
  }
}
