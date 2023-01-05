//package com.example.makeproject.batch.step_domain;
//
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
// * @info : Step Contribution 에 관한 클래스
// * @name : StepContribution_3
// * @date : 2023/01/05 5:57 PM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @Description :
// *
// * 1. StepExecute 에서 apply() 를 통해 각각의 속성들을 업데이트하게됨 -> Step Execute에.
// ************/
//@Configuration
//@RequiredArgsConstructor
//@Slf4j
//public class StepContribution_3 {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job job() {
//        return jobBuilderFactory.get("StepContributionJob")
//                .start(step1())
//                .next(step2())
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1")
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println(" ---------- step1 has execueted ---------- ");
////                    log.debug("this is Slf4j log={}", "step1");
//
//                    // 컨트리뷰션을 통해, step exeuction이 갖고있는 참조객체를 통해서. 각 도메인의 상태값이나, 참조값을 가져와서 사용할 수 있다.
//                    contribution.getStepExecution().getJobExecution().getJobInstance().getJobName();
//
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
////                    log.debug("this is Slf4j log={}", "step2");
//
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
//
//
//}
