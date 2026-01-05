package com.spring.knowhub.application.validators.media;

import com.spring.knowhub.application.commands.media.CreateMediaCommand;
import com.spring.knowhub.application.exceptions.media.CreateMediaException;
import com.spring.knowhub.domain.exceptions.media.InvalidMediaException;
import org.springframework.beans.factory.annotation.Value;

public class CreateMediaValidator {

    @Value("${media.max-size}")
    private static Long maxSize ;

    public static void validate (CreateMediaCommand command) {
        if (command == null) {
            CreateMediaException.objectNull();
        }
        if(command.getSize() != null && command.getSize() < 0) {
            InvalidMediaException.invalidMediaSize(command.getSize() , maxSize) ;
        }

    }
}
