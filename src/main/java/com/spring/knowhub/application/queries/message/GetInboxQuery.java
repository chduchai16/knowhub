package com.spring.knowhub.application.queries.message;

import com.spring.knowhub.application.buses.Query;
import com.spring.knowhub.domain.models.message.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetInboxQuery implements Query<Page<Message>> {
    private Long userId;
    private String search;
    private int page;
    private int limit;
}
