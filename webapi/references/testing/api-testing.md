# API Testing & Validation Strategy

The ultimate goal of testing the API is confirming its strict adherence to the OpenAPI contract.

## 1. Contract Testing Overview
*   **Schema Validation**: Every endpoint response in the test suite MUST be validated against the formal OpenAPI Schema to ensure the output didn't unexpectedly drift.
*   **Consumer-Driven Contracts**: Consider using tools like Pact to verify that changes to the provider API do not break the API consumers.

## 2. Test Principles
*   **Integration over Mocking**: When testing an API endpoint, test the full HTTP layer. Send a real HTTP request and assert on the real HTTP response containing JSON. 
*   **Status Code Verification**: Ensure tests explicitly verify standard status codes (`200 OK`, `201 Created`, `204 No Content`, `400 Bad Request`, `401 Unauthorized`, `403 Forbidden`, `404 Not Found`).

## 3. Validation Under Pressure
*   **Bad Requests**: Explicitly write tests for `400 Bad Request` by deliberately sending malformed payloads, violating size constraints, or missing required fields.
*   **RFC 7807 Verification**: Assert that these bad requests return the precisely formatted `application/problem+json` response.

## 4. Agent Guardrails
*   ✅ **Positive & Negative Testing**: The agent MUST generate at least one happy-path test (2xx code) and one validation-failure test (4xx code) for every new API endpoint it implements.
*   ❌ **No Blind Status Assertions**: Do not just check `assertEquals(200, response.getStatus())`. You MUST also parse the response body and assert on specific, predictable nested values.
