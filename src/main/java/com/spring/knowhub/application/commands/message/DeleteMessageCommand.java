package com.spring.knowhub.application.commands.message;

import com.spring.knowhub.application.buses.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteMessageCommand implements Command<Void> {
    private Long messageId;
    private Long userId;
}
