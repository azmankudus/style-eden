# Operations & Observability

Observability is crucial to ensure the deployed system is healthy and to quickly diagnose issues.

## 1. Metrics & Tracing (Backend)
*   **Micrometer**: Always enable Micronaut Micrometer metrics (`micronaut-micrometer-registry-prometheus`).
*   **OpenTelemetry**: Use OpenTelemetry (`micronaut-tracing-opentelemetry`) for distributed tracing. Ensure spans are created for database queries, external HTTP calls, and long-running services.
*   **Health Endpoints**: Enable Micronaut management endpoints (`/health`, `/info`, `/metrics`). Secure these endpoints so they are not exposed to the public internet.

## 2. Frontend Logging & Error Tracking
*   **Sentry / Datadog**: Integrate an error tracking SDK in the SolidStart application to catch unhandled JavaScript exceptions or rejected promises.
*   **Correlation IDs**: Pass backend trace IDs to the frontend (e.g., in a response header) so that the user's action can be traced from the browser all the way through the database.

## 3. Structured Logging
*   **JSON Formatting**: Use Logback with `logstash-logback-encoder` to output logs as JSON. This prevents multi-line stack traces from breaking log aggregators (like ELK/Datadog).
*   **Contextual Data**: Use MDC (Mapped Diagnostic Context) / Reactor Context to inject the `userId` and `traceId` into every log line automatically.

## 4. Agent Guardrails
*   ✅ **Log Important Events**: When writing a backend Service, strictly instruct the agent to include `log.info` and `log.error` statements for critical paths and failure modes.
*   ❌ **No PII in Logs**: The agent MUST NEVER log personal identifiable information (PII) such as passwords, tokens, full credit card numbers, or unmasked emails.
