package com.spring.knowhub.application.commands.post.post;

import com.spring.knowhub.application.buses.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeletePostCommand implements Command<Void> {
    private Long id ;
}
