# Strict API Contract Governance

The API Contract is the absolute law of the project. This prevents agents and development teams from guessing or hallucinating endpoint signatures, query parameters, or payload shapes across any language or framework.

## 1. API-First Design (Contract-Driven)
*   **Design Before Code**: An OpenAPI 3.1 (`openapi.yml` or `openapi.json`) document MUST be written, reviewed, and finalized BEFORE any server-side controller/router or client-side fetch logic is implemented.
*   **Single Source of Truth**: The OpenAPI document is the single source of truth. If a feature changes, the OpenAPI document must be updated first, and the codebase follows.

## 2. Code Generation vs. Manual Implementation
*   **Backend Validation**: Frameworks should ideally implement code-generation (e.g., generating interfaces/types from the OpenAPI spec) to ensure compile-time adherence. If code-gen is not available, strict runtime validation against the schema is required.
*   **Frontend Clients**: Clients (browsers, mobile apps) MUST strictly auto-generate their types and fetch clients (via `@openapitools/openapi-generator-cli`, Orval, etc.) from the server's OpenAPI spec. Hand-coded API clients are forbidden.

## 3. Strict RFC 7807 Error Handling
*   All APIs MUST return errors formatted according to **RFC 7807 (Problem Details for HTTP APIs)**.
*   The payload `Content-Type` must be `application/problem+json`.
*   The JSON object must include at minimum: `type`, `title`, `status`, and `detail`. 
*   ❌ **Anti-pattern**: Never return a raw string message or a generic `{"error": "message"}` payload.

## 4. Agent Guardrails
*   ❌ **No Blind Endpoints**: Never invent or implement an endpoint that does not exist in the defined OpenAPI contract.
*   ✅ **Verify Type Parity**: If instructing a language-specific framework, the agent MUST ensure backend DTOs or serializers perfectly match the fields, nullability, and types defined in the `openapi.yml` specification.
