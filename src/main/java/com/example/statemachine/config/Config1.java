package com.example.statemachine.config;

import com.example.statemachine.pojo.TestEvents1;
import com.example.statemachine.pojo.TestStates1;
import com.example.statemachine.service.Service1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@EnableStateMachine
@Configuration
@Slf4j
public class Config1 extends EnumStateMachineConfigurerAdapter<TestStates1, TestEvents1> {
    @Autowired
    private Service1 service1;


    // 状态机本身的配置
    @Override
    public void configure(StateMachineConfigurationConfigurer<TestStates1, TestEvents1> config)
        throws Exception {
        config
            .withConfiguration()
            .autoStartup(true); // 自启动
//            .listener(listener()); // 状态机 监听器
    }

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

//    @Bean
//    public StateMachineListener<TestStates1, TestEvents1> listener() {
//        return new StateMachineListenerAdapter<TestStates1, TestEvents1>() {
//            @Override
//            public void stateChanged(State<TestStates1, TestEvents1> from, State<TestStates1, TestEvents1> to) {
//                log.info("state {} to state {}", from.getId(), to.getId());
//            }
//        };
//    }

    private void action(StateContext<TestStates1, TestEvents1> stateContext){
        String source = stateContext.getSource().getId().toString();
        String target = stateContext.getTarget().getId().toString();
        String event = stateContext.getEvent().toString();
        service1.getState(source, target, event);
    }
}
