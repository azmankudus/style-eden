# JDK 21 Blueprint Conventions

Standard guidelines for modern Java development within this blueprint.

## 1. Core Features
*   **Records**: Use for all DTOs and immutable data models.
*   **Virtual Threads**: Enabled by default for all blocking I/O operations.
*   **Pattern Matching**: Use `switch` expressions and `instanceof` patterns for domain logic.
*   **Sealed Classes**: Define exhaustive domain states (e.g., `Result<T>`, `PaymentStatus`).

## 2. Best Practices
*   **Immutability**: Prefer `final` fields and immutable collections (`List.of`, etc.).
*   **Final Method Arguments**: All method arguments and local variables intended for one-time assignment MUST be marked `final`.
*   **Explicit Imports**: Never use wildcard imports (`import java.util.*`). Never use fully qualified names (FQN) in code (e.g., `java.util.List`). Always import explicitly.
*   **Default Encapsulation**: Follow "Principle of Least Privilege". Classes, methods, and constructors should be `package-private` (default) unless cross-package access is explicitly required.
*   **Optional**: Use for return types, never for parameters or fields.
*   **Functional Programming**: Leverage Streams and Method References.
*   **Error Handling**: Avoid checked exceptions where possible; use `RuntimeException` or `Result` types.
 
## 3. Anti-Hallucination Guardrails
*   ❌ **No Wildcard Imports**: Prevents import collision and keeps dependencies explicit.
*   ❌ **No FQN in methods**: Ensures code is readable and imports are managed.
*   ❌ **No Mutable method arguments**: Forces a functional and predictable approach.
