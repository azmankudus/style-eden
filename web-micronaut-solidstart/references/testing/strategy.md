# Testing Strategy & Quality Gates

Enforcing a rigorous testing culture for the full stack.

## 1. Quality Gates (Pre-commit & CI)
*   **Java**: Strict Checkstyle/Spotless rules must pass. PMD analysis must be green. Code coverage minimum: 80%.
*   **TypeScript**: Biome or ESLint strict rules. No `console.log` left behind. Prettier checks.
*   **Failed checks block commits**: Use husky + lint-staged (frontend) and Spotless hooks (backend) to ensure bad code never enters version control.

## 2. Backend (Micronaut)
*   **Unit Tests**: Use **JUnit 5 & Mockito**. Focus purely on business services. Keep them incredibly fast.
*   **Integration Tests**: Use **@MicronautTest** + **Testcontainers** (MySQL/Postgres & Redis).
    *   Initialize Testcontainers securely using ephemeral ports.
    *   Test persistence and REST endpoints (status codes, headers, and validated JSON).
*   **Anti-pattern**: Relying on H2/in-memory DBs instead of Testcontainers for JPA/R2DBC tests. Always test against the real DB engine.

## 3. Frontend (SolidStart)
*   **Unit Tests**: Use **Vitest** for signals, stores, and layout components.
*   **End-to-End Tests**: Use **Playwright** for complete user journeys. Playwright tests should handle login, navigation, and CRUD operations.
*   **Mocking API**: Use **Mock Service Worker (MSW)**. MSW handlers should be generated or hand-coded explicitly based on the Micronaut OpenAPI spec.

## 4. Agent Guardrails
*   ✅ **Test-Driven Generation**: If asked to create a feature, generate the test file alongside the implementation.
*   ❌ **Shallow Assertions**: Do not just check `expect(result).toBeDefined()`. Verify specific values matching the domain model.
