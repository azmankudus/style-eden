# Micronaut Blueprint Conventions

Specialized guidelines for the Micronaut backend in this web application blueprint.

## 1. Architecture
*   **Modular Package Structure**: Organize code into `domain` (business logic) and `shared` (infrastructure) top-level packages.
    *   `com.example.domain.[feature].controller|dto|service|model`
    *   `com.example.shared.common.api.envelope|exception|filter`
*   **Constructor Injection**: Mandatory for all beans. No `@Inject` on fields.
*   **AOP**: Use at-compile-time AOP for `@Transactional`, `@Cacheable`, and `@Retryable`.
*   **Controllers**: RESTful endpoints with explicit `@Produces(MediaType.APPLICATION_JSON)`.
 
## 2. API Contract Standard
*   **Standard Envelopes**: All requests and responses must be wrapped in consistent envelopes.
    *   `RequestEnvelope<T>`: Wraps incoming data and client metadata.
    *   `ResponseEnvelope<T>`: Wraps outgoing data, errors, and server metadata.
*   **Centralized Metadata**: Use `HttpServerFilter` to inject server metadata (trace IDs, timestamps, duration) globally.
*   **Structured Errors**: All exceptions must be handled via `ExceptionHandler` and return `ErrorDetail` within the `ResponseEnvelope`.
 
## 3. Persistence
*   **Micronaut Data**: Prefer JDBC or R2DBC for type-safe repository patterns.
*   **Migrations**: Use Liquibase for versioned database schema changes.

## 3. Web Context Best Practices
*   **Virtual Thread Execution**: Ensure the HTTP server uses a virtual thread executor.
*   **Validation**: Use `jakarta.validation` on all incoming DTOs.
*   **Exception Handlers**: Centralized handlers for consistent API error responses.

## 4. Testing Strategy
*   **@MicronautTest**: Full integration tests for controllers and services.
*   **Testcontainers**: Mandatory for all database and Redis integration tests. Ensure ephemeral ports and cleanup.
*   **JUnit 5 & Mockito**: Use for pure business logic unit testing.

## 5. Anti-Patterns (Anti-Hallucination Guardrails)
*   ❌ **No Field Injection**: Never use `@Inject` on fields. Always use constructor injection for better testability and immutability.
*   ❌ **No Blocking IO on Event Loop**: Never perform blocking database or network calls on Netty's event loop. Use `@ExecuteOn(TaskExecutors.BLOCKING)` or virtual threads.
*   ❌ **No Manual JSON Strings**: Never manually concatenate JSON. Always leverage Jackson with mapped DTOs.
*   ❌ **No Unvalidated Inputs**: Never accept a controller payload without `@Valid`, `@NotNull`, etc.
*   ❌ **No "God" Services**: Prevent hallucinating gigantic service classes. Keep services modular and domain-focused.
