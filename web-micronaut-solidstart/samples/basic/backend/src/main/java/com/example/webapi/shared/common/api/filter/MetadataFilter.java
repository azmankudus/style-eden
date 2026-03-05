package com.example.webapi.shared.common.api.filter;

import com.example.webapi.shared.common.api.envelope.RequestEnvelope;
import com.example.webapi.shared.common.api.envelope.ResponseEnvelope;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.HttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Filter("/**")
public class MetadataFilter implements HttpServerFilter {

  private final boolean includeMetadata;

  MetadataFilter(@Value("${application.include-metadata:false}") final boolean includeMetadata) {
    this.includeMetadata = includeMetadata;
  }

  @Override
  public Publisher<MutableHttpResponse<?>> doFilter(final HttpRequest<?> request, final ServerFilterChain chain) {
    final long startTime = System.currentTimeMillis();

    return Flux.from(chain.proceed(request)).map(response -> {
      if (includeMetadata) {
        final Object responseBody = response.body();

        if (responseBody instanceof ResponseEnvelope) {
          final ResponseEnvelope<?> envelope = (ResponseEnvelope<?>) responseBody;

          Map<String, Object> meta = envelope.getMeta();
          if (meta == null) {
            meta = new HashMap<>();
          }

          // Populate Server response metadata
          meta.put("server_response_duration", (System.currentTimeMillis() - startTime) + "ms");
          meta.put("server_api_url", request.getUri().toString());
          meta.put("server_response_http_status",
              response.getStatus().getCode() + " " + response.getStatus().getReason());

          // Extract Client Request Metadata from parsed request body
          final Optional<RequestEnvelope> reqEnvOpt = request.getBody(RequestEnvelope.class);
          if (reqEnvOpt.isPresent()) {
            final RequestEnvelope<?> reqEnv = reqEnvOpt.get();
            if (reqEnv.getMeta() != null && reqEnv.getMeta().containsKey("client_trace_id")) {
              meta.put("server_request_trace_id", reqEnv.getMeta().get("client_trace_id"));
              meta.put("client_request_timestamp", reqEnv.getMeta().get("client_request_timestamp"));
            } else {
              meta.put("server_request_trace_id", UUID.randomUUID().toString());
            }
          } else {
            meta.put("server_request_trace_id", UUID.randomUUID().toString());
          }

          envelope.setMeta(meta);
        }
      }
      return response;
    });
  }
}
