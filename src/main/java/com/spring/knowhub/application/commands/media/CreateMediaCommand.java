package com.spring.knowhub.application.commands.media;

import com.spring.knowhub.application.buses.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateMediaCommand implements Command<Long> {
    private String publicId ;
    private String url ;
    private String originalName ;
    private String format ;
    private Long size ;
    private String type ;
    private String folder ;
}
