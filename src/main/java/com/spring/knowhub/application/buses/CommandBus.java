package com.spring.knowhub.application.buses;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommandBus {
    private final List<CommandHandler<?, ?>> handlers;

    @SuppressWarnings("unchecked")
    public <R> R execute(Object command) {
        return (R) handlers.stream()
                .filter(h -> h.supports(command))
                .findFirst()
                .map(h -> ((CommandHandler<Object, R>) h).handle(command))
                .orElseThrow(() -> new IllegalStateException("Không có command cho: " + command.getClass()));
    }
}
