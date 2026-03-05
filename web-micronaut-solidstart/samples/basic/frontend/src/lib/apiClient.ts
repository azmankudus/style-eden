export interface RequestEnvelope<T> {
  data: T;
  meta?: {
    client_request_timestamp?: string;
    client_trace_id?: string;
    [key: string]: any;
  };
}

export interface ErrorDetail {
  code: string;
  message: string;
  detail?: string;
}

export interface ResponseEnvelope<T> {
  data?: T;
  errors?: ErrorDetail[];
  meta?: Record<string, any>;
}

/**
 * A wrapper to enforce the Strict API Blueprint envelope pattern.
 */
export async function fetchWithEnvelope<T, R>(
  url: string,
  method: 'POST' | 'PUT' | 'PATCH',
  payload: T,
  includeMeta: boolean = true,
  lang: string = 'en'
): Promise<ResponseEnvelope<R>> {

  const envelope: RequestEnvelope<T> = { data: payload };

  if (includeMeta) {
    envelope.meta = {
      client_request_timestamp: new Date().toISOString(),
      client_trace_id: crypto.randomUUID()
    };
  }

  const response = await fetch(url, {
    method,
    headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'Accept-Language': lang
    },
    body: JSON.stringify(envelope)
  });

  // Depending on whether it's mapped directly, try parsing
  return (await response.json()) as ResponseEnvelope<R>;
}
