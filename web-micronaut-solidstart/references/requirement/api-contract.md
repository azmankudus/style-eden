# API Contract Governance

API synchronization is the most critical coordination point between backend and frontend teams (and agents) to avoid hallucinated parameters or mismatched endpoints.

## 1. Single Source of Truth
*   **Backend Generates Contract**: The Micronaut backend is the authoritative source.
*   **OpenAPI 3.1**: Micronaut must use `micronaut-openapi` to automatically generate `openapi.yml` at build time based on `@Controller` annotations and Java DTOs.

## 2. Frontend Consumption
*   **Never Hand-code API Clients**: The frontend team (or agent) must use `@openapitools/openapi-generator-cli` or similar tools to automatically generate TypeScript Fetch/Axios clients and `interfaces` from the backend's `openapi.yml`.
*   **Mocking**: Frontend uses the `openapi.yml` to generate MSW (Mock Service Worker) handlers for local UI development avoiding dependency on the backend state.

## 3. Formatting Rules
*   **URLs**: Always `kebab-case` (e.g., `/api/v1/user-profiles`).
*   **JSON Properties**: Always `camelCase`.
*   **Standard Envelopes**:
    *   **Request**: `{"data": {...}, "meta": {...}}`. `meta` is optional.
    *   **Response**: `{"data": {...}, "errors": [...], "meta": {...}}`. `data` and `errors` are mutually exclusive.
*   **Error Standard**: Return `ResponseEnvelope` with `errors` list containing `code`, `message`, and `detail`. For unhandled exceptions, inclusion of stacktrace in `meta` must be configurable.
 
## 4. Agent Guardrails
*   ❌ **Don't Assume Endpoints**: Wait for the Micronaut API spec before building the SolidStart fetch call.
*   ✅ **Verify Type Parity**: Ensure the backend Java DTO properties strictly match the frontend TypeScript interface properties.
*   ✅ **Envelope Enforcement**: Always use `RequestEnvelope` for POST/PUT/PATCH bodies and `ResponseEnvelope` for all return types.
