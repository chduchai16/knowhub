package com.spring.knowhub.application.buses;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QueryBus {
    private final List<QueryHandler<?, ?>> handlers;

    @SuppressWarnings("unchecked")
    public <R> R execute(Object query) {
        return (R) handlers.stream()
                .filter(h -> h.supports(query))
                .findFirst()
                .map(h -> ((QueryHandler<Object, R>) h).handle(query))
                .orElseThrow(() -> new IllegalStateException("Không có query cho: " + query.getClass()));
    }
}
