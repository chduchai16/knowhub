package com.spring.knowhub.application.commands.report;

import com.spring.knowhub.application.buses.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteReportCommand implements Command<Void> {
    private Long id;
}
