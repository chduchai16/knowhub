package com.spring.knowhub.application.buses;

public interface CommandHandler <Q,R>{
    boolean supports(Object query);
    R handle(Q query);
}
