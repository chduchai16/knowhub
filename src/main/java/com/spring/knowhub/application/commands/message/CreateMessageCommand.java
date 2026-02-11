package com.spring.knowhub.application.commands.message;

import com.spring.knowhub.application.buses.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMessageCommand implements Command<Long> {
    private Long senderId;
    private Long receiverId;
    private String content;
    private List<Long> mediaIds;
}
