package com.example.statemachine.controller;

import com.example.statemachine.pojo.TestEvents1;
import com.example.statemachine.pojo.TestStates1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class Controller1 {
    @Autowired
    private StateMachine<TestStates1, TestEvents1> stateMachine1;

    @RequestMapping("/test1")
    public String state1(){
        stateMachine1.start();

        log.info("current state: is {}", stateMachine1.getState().getId().name());
        stateMachine1.sendEvent(TestEvents1.EVENT1);
        log.info("current state: is {}", stateMachine1.getState().getId().name());
        log.info("-----------------------------------------------------------------------");

        stateMachine1.sendEvent(TestEvents1.EVENT1);
        log.info("current state: is {}", stateMachine1.getState().getId().name());
        log.info("-----------------------------------------------------------------------");

        stateMachine1.sendEvent(TestEvents1.EVENT2);
        log.info("current state: is {}", stateMachine1.getState().getId().name());
        log.info("-----------------------------------------------------------------------");

        stateMachine1.sendEvent(TestEvents1.EVENT3);
        log.info("current state: is {}", stateMachine1.getState().getId().name());
        log.info("-----------------------------------------------------------------------");
        return "test";
    }
}
