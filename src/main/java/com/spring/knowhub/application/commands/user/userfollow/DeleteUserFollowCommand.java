package com.spring.knowhub.application.commands.user.userfollow;

import com.spring.knowhub.application.buses.Command;

import lombok.Data;

@Data
public class DeleteUserFollowCommand implements Command<Void> {
    private String userName;
    private String followerName;
}
