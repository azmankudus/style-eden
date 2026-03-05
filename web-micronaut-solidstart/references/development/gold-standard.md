# Micronaut Gold Standard Samples
 
These samples represent the highest quality patterns for this blueprint. AI agents should follow these exactly.
 
## 1. Modular Package Structure
 
```text
src/main/java/com/example/api/
├── Application.java                   # Root
├── domain/                            # Feature-based business logic
│   └── user/
│       ├── controller/
│       │   └── UserController.java    # Package-private constructor
│       ├── dto/
│       │   ├── UserRequestDto.java    # @Serdeable Record/Class
│       │   └── UserResponseDto.java   # Public accessors
│       ├── service/                   # Optional: logic layer
│       └── model/                     # Optional: entity layer
└── shared/                            # Infrastructure and utilities
    └── common/
        └── api/
            ├── envelope/              # Request/Response wrappers
            ├── exception/             # Custom exceptions + handlers
            └── filter/                # HTTP filters (metadata, logs)
```
 
## 2. Standard Controller Pattern
 
```java
package com.example.api.domain.user.controller;
 
import com.example.api.domain.user.dto.UserRequestDto;
import com.example.api.domain.user.dto.UserResponseDto;
import com.example.api.shared.common.api.envelope.RequestEnvelope;
import com.example.api.shared.common.api.envelope.ResponseEnvelope;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.validation.Valid;
 
@Controller("/v1/users")
class UserController {
 
    // Package-private constructor (Constructor Injection)
    UserController() {}
 
    @Post
    HttpResponse<ResponseEnvelope<UserResponseDto>> createUser(
            @Body @Valid final RequestEnvelope<UserRequestDto> request) {
        
        final UserRequestDto data = request.getData();
        // ... logic ...
        final UserResponseDto created = new UserResponseDto(1L, data.getUsername());
        
        return HttpResponse.created(ResponseEnvelope.success(created));
    }
}
```
 
## 3. Strict Coding Standards
 
*   **Final by Default**: `final String name = ...`, `public void execute(final String arg)`.
*   **Explicit Imports**: No `*`.
*   **Encapsulation**: Default to `package-private` for controllers and handlers.
*   **Envelopes**: Every API interaction uses `RequestEnvelope` and `ResponseEnvelope`.
