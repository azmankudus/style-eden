package com.example.webapi.shared.common.api.exception;

import com.example.webapi.shared.common.api.envelope.ErrorDetail;
import com.example.webapi.shared.common.api.envelope.ResponseEnvelope;

import io.micronaut.context.MessageSource;
import io.micronaut.context.MessageSource.MessageContext;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Produces
@Singleton
public class ApiExceptionHandler implements ExceptionHandler<ApiException, HttpResponse<ResponseEnvelope<Object>>> {

  private final MessageSource messageSource;
  private final boolean includeStacktrace;

  ApiExceptionHandler(final MessageSource messageSource,
      @Value("${application.include-exception-stacktrace:false}") final boolean includeStacktrace) {
    this.messageSource = messageSource;
    this.includeStacktrace = includeStacktrace;
  }

  @Override
  public HttpResponse<ResponseEnvelope<Object>> handle(final HttpRequest request, final ApiException exception) {

    // Resolve locale from request headers for i18n
    final Locale locale = (Locale) request.getLocale().orElse(null);
    final Optional<String> msg = messageSource.getMessage(exception.getMessageKey(),
        MessageContext.of(locale));

    String finalMessage = msg.orElse(exception.getMessageKey());
    if (exception.getArgs() != null && exception.getArgs().length > 0) {
      finalMessage = MessageFormat.format(finalMessage, exception.getArgs());
    }

    final ErrorDetail error = new ErrorDetail(exception.getCode(), finalMessage, exception.getMessage());
    final ResponseEnvelope<Object> response = ResponseEnvelope.error(List.of(error));

    if (includeStacktrace) {
      final StringWriter sw = new StringWriter();
      exception.printStackTrace(new PrintWriter(sw));
      final Map<String, Object> meta = new HashMap<>();
      meta.put("server_response_exception_stacktrace", sw.toString());
      response.setMeta(meta);
    }

    return HttpResponse.badRequest(response);
  }
}
