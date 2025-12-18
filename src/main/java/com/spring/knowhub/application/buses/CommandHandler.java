package com.spring.knowhub.application.buses;

public interface CommandHandler <Q,R>{
    boolean supports(Object command);
    R handle(Q command);
}
