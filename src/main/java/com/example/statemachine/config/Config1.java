package com.example.statemachine.config;

import com.example.statemachine.pojo.TestEvents1;
import com.example.statemachine.pojo.TestStates1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@EnableStateMachine
@Configuration
@Slf4j
public class Config1 extends EnumStateMachineConfigurerAdapter<TestStates1, TestEvents1> {

    @Override
    public void configure(StateMachineStateConfigurer<TestStates1, TestEvents1> stateConfigurer) throws Exception {
        stateConfigurer.withStates()
            .initial(TestStates1.STATE1) // 初始状态
            .states(EnumSet.allOf(TestStates1.class)); // 所有状态
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<TestStates1, TestEvents1> transitions) throws Exception {
        transitions.withExternal()
            .source(TestStates1.STATE1).target(TestStates1.STATE2).event(TestEvents1.EVENT1).action(this::action)
            .and().withExternal()
            .source(TestStates1.STATE2).target(TestStates1.STATE3).event(TestEvents1.EVENT2).action(this::action)
            .and().withExternal()
            .source(TestStates1.STATE3).target(TestStates1.STATE1).event(TestEvents1.EVENT3).action(this::action);
    }

    private void action(StateContext<TestStates1, TestEvents1> stateContext) {
        String source = stateContext.getSource().getId().toString();
        String target = stateContext.getTarget().getId().toString();
        String event = stateContext.getEvent().toString();
        log.info("log action state {} to state {}, action is {}", source, target, event);
    }
}
