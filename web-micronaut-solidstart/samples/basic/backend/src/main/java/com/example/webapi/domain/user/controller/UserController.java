package com.example.webapi.domain.user.controller;

import com.example.webapi.domain.user.dto.UserRequestDto;
import com.example.webapi.domain.user.dto.UserResponseDto;
import com.example.webapi.shared.common.api.envelope.RequestEnvelope;
import com.example.webapi.shared.common.api.envelope.ResponseEnvelope;
import com.example.webapi.shared.common.api.exception.ApiException;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Controller("/v1/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "User Management")
public class UserController {

  UserController() {
  }

  @Post
  @Operation(summary = "Create user", description = "Creates a new user and returns standardized metadata envelope.")
  HttpResponse<ResponseEnvelope<UserResponseDto>> createUser(
      @Body @Valid final RequestEnvelope<UserRequestDto> request) {

    final UserRequestDto payload = request.getData();

    if ("admin".equalsIgnoreCase(payload.getUsername())) {
      throw new ApiException("FORBIDDEN_NAME", "error.user.forbidden", payload.getUsername());
    }

    if ("exception".equalsIgnoreCase(payload.getUsername())) {
      // Test unhandled condition for stacktrace extraction testing
      throw new RuntimeException("Simulated unexpected exception");
    }

    final UserResponseDto created = new UserResponseDto(1001L, payload.getUsername(), payload.getEmail(), "CREATED");

    return HttpResponse.created(ResponseEnvelope.success(created));
  }
}
