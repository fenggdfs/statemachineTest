package com.example.statemachine.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Service1 {
    public void getState(String source, String target, String event){
        log.info("log action state {} to state {}, action is {}", source, target, event);
    }
}
