# JDK 21 Blueprint Conventions

Standard guidelines for modern Java development within this blueprint.

## 1. Core Features
*   **Records**: Use for all DTOs and immutable data models.
*   **Virtual Threads**: Enabled by default for all blocking I/O operations.
*   **Pattern Matching**: Use `switch` expressions and `instanceof` patterns for domain logic.
*   **Sealed Classes**: Define exhaustive domain states (e.g., `Result<T>`, `PaymentStatus`).

## 2. Best Practices
*   **Immutability**: Prefer `final` fields and immutable collections (`List.of`, etc.).
*   **Optional**: Use for return types, never for parameters or fields.
*   **Functional Programming**: Leverage Streams and Method References.
*   **Error Handling**: Avoid checked exceptions where possible; use `RuntimeException` or `Result` types.
