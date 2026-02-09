package com.spring.knowhub.application.commands.comment;

import com.spring.knowhub.application.buses.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteCommentCommand implements Command<Void> {
    private Long id;
    private Long userId; // Để kiểm tra quyền xóa
}
