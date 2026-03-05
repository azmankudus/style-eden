package com.example.webapi.shared.common.api.envelope;

import java.util.Map;
import io.micronaut.serde.annotation.Serdeable;
import io.micronaut.core.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Serdeable
public class RequestEnvelope<T> {

    @NotNull
    @Valid
    private T data;

    @Nullable
    private Map<String, Object> meta;

    public RequestEnvelope() {
    }

    public RequestEnvelope(final T data, final Map<String, Object> meta) {
        this.data = data;
        this.meta = meta;
    }

    public T getData() {
        return data;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public Map<String, Object> getMeta() {
        return meta;
    }

    public void setMeta(final Map<String, Object> meta) {
        this.meta = meta;
    }
}
