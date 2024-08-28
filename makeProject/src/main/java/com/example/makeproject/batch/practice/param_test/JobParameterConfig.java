//package com.example.makeproject.batch.test.param_test;
//
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.JobScope;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///************
// * @info : job parameter Test Class - 실행
// * @name : JobParameterConfig
// * @date : 2023/01/06 6:10 PM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @Description :
// ************/
//@Configuration
//@RequiredArgsConstructor
//@Slf4j
//public class JobParameterConfig {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    private final CreateDateJobParameter jobParameter;
//
//    @Bean
//    @JobScope
//    public CreateDateJobParameter jobParameter() {
//        return new CreateDateJobParameter();
//    }
//
//    @Bean
//    public Job job() {
//        return jobBuilderFactory.get("jobParamJob")
//                .start(step1(null))
//                .build();
//    }
//
//    @Bean
//    @JobScope
//    public Step step1(@Value("#{jobParameters[requestDate]}") String jobParam) {
//        return stepBuilderFactory.get("step1")
//                .tasklet(tasklet1())
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public Tasklet tasklet1() {
//        return (contribution, chunkContext) -> {
//            System.out.println("tasklet has been executed");
//            return RepeatStatus.FINISHED;
//        };
//    }
//
//}
