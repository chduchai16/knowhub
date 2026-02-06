package com.spring.knowhub.application.commands.post.postlike;

import com.spring.knowhub.application.buses.Command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeletePostLikeCommand implements Command<Void>{
    private Long id ;
}
