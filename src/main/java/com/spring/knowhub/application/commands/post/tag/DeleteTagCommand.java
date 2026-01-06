package com.spring.knowhub.application.commands.post.tag;

import com.spring.knowhub.application.buses.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteTagCommand implements Command<Void> {
    private Long id ;
}
