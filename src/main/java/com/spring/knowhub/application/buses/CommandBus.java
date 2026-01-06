package com.spring.knowhub.application.buses;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CommandBus {
    private final List<CommandHandler<?, ?>> handlers;

    @SuppressWarnings("unchecked")
    public <R> R execute(Object command) {
        Optional<CommandHandler<?, ?>> handler = handlers.stream()
                .filter(h -> h.supports(command))
                .findFirst();

        if (handler.isEmpty()) {
            throw new IllegalStateException("Không có command cho: " + command.getClass());
        }

        return (R) ((CommandHandler<Object, R>) handler.get()).handle(command);
    }
}
