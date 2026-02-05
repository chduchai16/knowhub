package com.spring.knowhub.application.commands.user.userfollow;

import com.spring.knowhub.application.buses.Command;

import lombok.Data;

@Data
public class CreateUserFollowCommand implements Command<Long> {
    private Long userId;
    private Long followerId;
}
