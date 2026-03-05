# Universal API Design Standards

These design formatting rules apply universally to any framework implementing the API.

## 1. Formatting & Naming Conventions
*   **URL Path Case**: Always `kebab-case` (e.g., `/api/v1/user-profiles`).
*   **JSON Field Case**: Always `camelCase` for JSON payload properties (e.g., `firstName`, `createdAt`). This is preferred to universally align with JS/TS consumers.
*   **Query Parameters Case**: Always `camelCase` (e.g., `?sortBy=createdAt&pageIndex=1`).

## 2. Versioning & Restfulness
*   **URL Versioning**: Endpoints must be versioned in the URL path (e.g., `/api/v1/...`). Avoid header-based or content-negotiation versioning for simplicity unless required by complex enterprise constraints.
*   **Nouns vs. Verbs**: URL paths must be nouns (e.g., `/api/v1/users`), never verbs (e.g., `/api/v1/getUsers`). Use HTTP methods (`GET`, `POST`, `PUT`, `PATCH`, `DELETE`) to indicate the action.

## 3. Pagination & Collections
*   **Standard Envelope**: If an endpoint returns a collection, it should use an envelope containing `data` for the array and `meta` for pagination metadata.
*   **Example Response**:
    ```json
    {
      "data": [
        { "id": "123", "name": "John" }
      ],
      "meta": {
        "totalElements": 1,
        "totalPages": 1,
        "pageNumber": 1,
        "pageSize": 20
      }
    }
    ```

## 4. Agent Guardrails
*   ❌ **No Generic Maps/Dicts**: When writing the OpenAPI spec or generating code, never accept or return an unstructured `Map<String, Object>` or `Dict[str, Any]`. Always define strict JSON Schemas.
*   ✅ **Explicit Nullability**: Always explicitly define whether a field is required (`required: [fieldName]`) and whether its value can be null (`nullable: true`) in the OpenAPI schema.
