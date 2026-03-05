package com.example.webapi;

import io.micronaut.context.annotation.Property;
import io.micronaut.http.HttpRequest;
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
@Property(name = "application.include-exception-stacktrace", value = "true")
class ApiContractStacktraceTest {

  @Inject
  @Client("/api/v1")
  HttpClient client;

  @Test
  void testServerResponseErrorWithMetadataException() {
    final Map<String, Object> payload = Map.of(
        "data", Map.of(
            "username", "admin", // triggers error
            "email", "admin@example.com"));

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
      assertNotNull(meta.get("server_response_exception_stacktrace"));
      assertTrue(meta.get("server_response_exception_stacktrace").toString().contains("ApiException"));
    }
  }
}
