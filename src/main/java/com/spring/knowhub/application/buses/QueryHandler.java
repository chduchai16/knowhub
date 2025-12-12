package com.spring.knowhub.application.buses;

public interface QueryHandler <Q,R>{
    boolean supports(Object query);
    R handle(Q query);
}
