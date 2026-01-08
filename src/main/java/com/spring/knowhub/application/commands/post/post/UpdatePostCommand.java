package com.spring.knowhub.application.commands.post.post;

import com.spring.knowhub.application.buses.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UpdatePostCommand implements Command<Long> {
    private Long id ;
    private Long userId ;
    private String content ;
    private String privacy ;
    private List<Long> mediaIds ;
    private List<Long> tagIds ;
    private String status ;
}
