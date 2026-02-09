package com.spring.knowhub.application.commands.notification;

import com.spring.knowhub.application.buses.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MarkNotificationReadCommand implements Command<Void> {
    private Long userNotificationId;
}
