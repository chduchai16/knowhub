package com.spring.knowhub.application.commands.media;

import com.spring.knowhub.application.buses.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CreateMediasCommand implements Command<List<Long>> {
    private List<CreateMediaCommand> medias;
}
