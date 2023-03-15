//package com.example.makeproject.batch.practice.test;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///************
// * @info : Batch - Test
// * @name : JobTestConfig
// * @date : 2023/03/16 2:19 AM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @Description :
// ************/
//@Configuration
//@RequiredArgsConstructor
//@Slf4j
//public class JobTestConfig {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job testJob(){
//        return jobBuilderFactory.get("test_job_config")
//                .start(step1())
//                .next(step2())
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1")
//                .tasklet((stepContribution, chunkContext) -> {
//                    System.out.println("Step1 has executed");
//                    return RepeatStatus.FINISHED;
//                }).build();
//    }
//
//    @Bean
//    public Step step2() {
//        return stepBuilderFactory.get("step2")
//                .tasklet((stepContribution, chunkContext) -> {
//                    System.out.println("Step2 has executed");
//                    return RepeatStatus.FINISHED;
//                }).build();
//    }
//}
