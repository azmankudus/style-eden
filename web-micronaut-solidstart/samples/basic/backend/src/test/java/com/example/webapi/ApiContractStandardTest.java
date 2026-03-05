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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

@MicronautTest
@Property(name = "application.include-metadata", value = "false")
class ApiContractStandardTest {

  @Inject
  @Client("/api/v1")
  HttpClient client;

  @Test
  void testClientRequestToServerResponseOk() {
    final Map<String, Object> payload = Map.of(
        "data", Map.of(
            "username", "johndoe",
            "email", "john@example.com"));

    try {
      final HttpResponse<Map> response = client.toBlocking().exchange(
          HttpRequest.POST("/users", payload), Map.class);

      assertEquals(HttpStatus.CREATED, response.getStatus());
      assertNotNull(response.body());
      assertTrue(response.body().containsKey("data"));
      assertFalse(response.body().containsKey("errors"));
      assertFalse(response.body().containsKey("meta"));

      final Map<String, Object> data = (Map<String, Object>) response.body().get("data");
      assertEquals("johndoe", data.get("username"));
    } catch (HttpClientResponseException e) {
      System.err.println("Test failed. Response body: " + e.getResponse().getBody(Map.class).orElse(null));
      throw e;
    }
  }

  @Test
  void testServerResponseError() {
    final Map<String, Object> payload = Map.of(
        "data", Map.of(
            "username", "admin", // triggers error
            "email", "admin@example.com"));

    try {
      client.toBlocking().exchange(HttpRequest.POST("/users", payload), Map.class);
      fail("Should have thrown bad request exception");
    } catch (final HttpClientResponseException e) {
      assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
      final Map responseEntity = e.getResponse().getBody(Map.class).orElse(null);
      assertNotNull(responseEntity);
      assertTrue(responseEntity.containsKey("errors"));
      assertFalse(responseEntity.containsKey("data"));
      assertFalse(responseEntity.containsKey("meta"));
    }
  }
}
