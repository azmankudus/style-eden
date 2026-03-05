# Security & OWASP Guardrails

To prevent hallucinating insecure code patterns and ensure rigorous security coverage for full-stack projects.

## 1. Authentication & Authorization (Backend)
*   **JWT & Stateful Sessions**: Use Micronaut Security. If using JWT, ensure tokens are signed securely (HS256/RS256) and expiration is short.
*   **Role-Based Access Control (RBAC)**: Protect endpoints using `@Secured("ROLE_ADMIN")` or `@Secured(SecurityRule.IS_AUTHENTICATED)`.
*   **Password Hashing**: Store passwords using `BCrypt` or `Argon2`.
*   ❌ **Anti-pattern**: Never hardcode credentials in source code. Use environment variables injected via `application.yml`.

## 2. Frontend Security (SolidStart)
*   **XSS Protection**: Solid natively escapes output, but strictly avoid using `innerHTML` or `solid-js` `innerHTML` bindings unless absolutely necessary (and sanitized with DOMPurify).
*   **CSRF Protection**: If using session cookies instead of JWT, enforce `SameSite=Strict` or `Lax` and implement CSRF tokens in API headers.
*   **Local Storage**: Do not store sensitive data (like plain PII or long-lived JWTs) carelessly in `localStorage`. Use `HttpOnly` cookies where possible.

## 3. Data & APIs
*   **Input Validation**: Enforce `jakarta.validation` on the backend and Zod/Valibot schemas on the frontend.
*   **Rate Limiting**: Apply rate-limiting headers and handling (e.g., Spring-style or Micronaut rate limiting packages, Redis backed) on public APIs.
*   **CORS**: Secure CORS tightly. Do not use wildcard `*` domains in production.

## 4. Agent Guardrails
*   ❌ **No Insecure Randomness**: Never use `Math.random()` or `java.util.Random` for security keys. Use `window.crypto` or `java.security.SecureRandom`.
*   ✅ **Review OWASP Top 10**: Check major features for injection risks, broken auth, or exposing sensitive data.
