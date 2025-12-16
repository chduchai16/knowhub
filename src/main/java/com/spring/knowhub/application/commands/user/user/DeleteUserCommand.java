package com.spring.knowhub.application.commands.user.user;

import com.spring.knowhub.application.buses.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteUserCommand implements Command<Long> {
    private Long userId;
}
