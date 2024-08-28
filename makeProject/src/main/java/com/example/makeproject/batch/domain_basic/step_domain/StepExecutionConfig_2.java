//package com.example.makeproject.batch.step_domain;
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
// * @info : Step Execution 예제
// * @name : StepExecutionConfig_2
// * @date : 2023/01/02 5:50 PM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @Description :
// *
// * - 실행시 성공, 실패 테스트
// *
// ************/
//@Configuration
//@RequiredArgsConstructor
//@Slf4j
//public class StepExecutionConfig_2 {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job job() {
//        return jobBuilderFactory.get("StepExecutionJob")
//                .start(step1())
//                .next(step2())
//                .next(step3())
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1")
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println(" ---------- step1 has execueted ---------- ");
//                    log.debug("this is Slf4j log={}", "step1");
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
//    @Bean
//    public Step step2() {
//        return stepBuilderFactory.get("step2")
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println(" ---------- step2 has execueted  ---------- ");
//                    log.debug("this is Slf4j log={}", "step2");
//
//                    // if Exception Test Want
//                    // Excution table 에는 fail 처리 -> step3 는 실행이 되지 않음.
////                    throw new RuntimeException("Step2 has failed");
//
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
//    @Bean
//    public Step step3() {
//        return stepBuilderFactory.get("step3")
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println(" ---------- step3 has execueted ---------- ");
//                    log.debug("this is Slf4j log={}", "step3");
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
//}
