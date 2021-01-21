package com.example.statemachine.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

@WithStateMachine
@Slf4j
public class ListenConfig1 {
    @OnTransition(target = "STATE1")
    public void toState1() {
        log.info("listen from state1 to state2");
    }

    @OnTransition(target = "STATE2")
    public void toState2() {
        log.info("listen from state1 to state2");
    }

    @OnTransition(target = "STATE3")
    public void toState3() {
        log.info("listen from state1 to state3");
    }
}
