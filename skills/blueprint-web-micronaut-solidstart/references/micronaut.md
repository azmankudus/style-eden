# Micronaut Blueprint Conventions

Specialized guidelines for the Micronaut backend in this web application blueprint.

## 1. Architecture
*   **Constructor Injection**: Mandatory for all beans.
*   **AOP**: Use at-compile-time AOP for `@Transactional`, `@Cacheable`, and `@Retryable`.
*   **Controllers**: RESTful endpoints with explicit `@Produces(MediaType.APPLICATION_JSON)`.

## 2. Persistence
*   **Micronaut Data**: Prefer JDBC or R2DBC for type-safe repository patterns.
*   **Migrations**: Use Liquibase for versioned database schema changes.

## 3. Web Context Best Practices
*   **Virtual Thread Execution**: Ensure the HTTP server uses a virtual thread executor.
*   **Validation**: Use `jakarta.validation` on all incoming DTOs.
*   **Exception Handlers**: Centralized handlers for consistent API error responses.

## 4. Testing
*   **@MicronautTest**: Full integration tests for controllers.
*   **Testcontainers**: Used for all database-related tests.
