# Standardized Payload Schema

The API blueprint mandates a highly structured envelope for both **Client Requests** and **Server Responses**. This ensures end-to-end traceability, predictable error handling, and consistent metadata encapsulation.

All APIs implementing this blueprint MUST adhere to the following envelope structure.

## 1. Basic API Request and Response Structure

### Client Request
All client requests with a payload must wrap the payload in a `data` object:
```json
{
  "data": { 
    // <DATA>
  }
}
```

### Server Response OK (Success)
All successful server responses must wrap the result in a `data` object:
```json
{
  "data": { 
    // <DATA>
  }
}
```

### Server Response Error (Failure)
All error responses must use an `errors` array containing specific error details. This structure replaces standard RFC 7807 when this envelope is strictly enforced.
```json
{
  "errors": [
    {
      "code": "<ERROR_CODE>",
      "message": "<HUMAN_READABLE_MESSAGE>",
      "detail": "<TECHNICAL_OR_FIELD_LEVEL_DETAIL>"
    }
  ]
}
```

---

## 2. Optional Metadata Envelope (`meta`)

Optional information or metadata can be added into a `"meta"` element, placed next to `"data"` or `"errors"`. The inclusion of metadata or stacktraces is controlled by application switches (e.g., `include_metadata` and `include_exception_stacktrace`) configured within the frontend and backend projects.

### When `include_metadata` is Enabled

**1. Client Request Metadata**  
The client will include the following metadata in the request envelope:
```json
{
  "data": { ... },
  "meta": {
    "client_request_timestamp": "<ISO_OFFSET_DATE_TIME>",
    "client_trace_id": "<UUID>"
  }
}
```

**2. Server Response Metadata**  
The server will respond with a copy of the client metadata, plus its own tracking metadata:
```json
{
  "data": { ... },
  "meta": {
    "client_request_timestamp": "<ISO_OFFSET_DATE_TIME>",
    "client_trace_id": "<UUID>",
    "server_request_timestamp": "<ISO_OFFSET_DATE_TIME>",
    "server_request_trace_id": "<CLIENT_UUID_OR_NEW_UUID>",
    "server_response_timestamp": "<ISO_OFFSET_DATE_TIME>",
    "server_response_duration": "<ISO_DURATION>",
    "server_response_http_status": "<HTTP_CODE> <HTTP_NAME>"
  }
}
```
*(Note: `server_request_trace_id` should use the `client_trace_id` if provided, otherwise the server generates a new UUID).*

### When `include_exception_stacktrace` is Enabled (Debugging)

When this flag is enabled (typically in local development or lower environments, NEVER in production), the server response will include the raw exception stacktrace inside the metadata:
```json
{
  "errors": [ ... ],
  "meta": {
    "...": "...",
    "server_response_exception_stacktrace": "<EXCEPTION_STACKTRACE>"
  }
}
```

## 3. Agent Guardrails
*   ❌ **No Naked Payloads**: Never send or return a business object directly at the root. Always wrap it inside `data`.
*   ✅ **Metadata Toggles**: When generating Framework code (e.g., Micronaut Interceptors or Axios request interceptors), implement explicit toggle switches for `include_metadata` and `include_exception_stacktrace`.
*   ❌ **Production Safety**: Ensure `include_exception_stacktrace` is strictly disabled in production profiles.
