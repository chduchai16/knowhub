package com.spring.knowhub.application.commands.message;

import com.spring.knowhub.application.buses.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMessageCommand implements Command<Long> {
    private Long messageId;
    private Long userId;
    private String content;
}
