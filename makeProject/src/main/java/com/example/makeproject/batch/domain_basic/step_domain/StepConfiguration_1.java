//package com.example.makeproject.batch.step_domain;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///************
// * @info : Step 1번 Step 기본 도메인 - Tasklet 방식
// * @name : StepConfiguration
// * @date : 2023/01/02 5:05 PM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @Description :
// *
// * 1. Step 기본 도메인
// * 2. Tasklet 커스텀 객체 주입.
// *
// * - Tasklet 만들고 -> Step 만들고 -> SimpleJob 만들어서 그안에 Steps 를 주입.
// ************/
//@Configuration
//@RequiredArgsConstructor
//public class StepConfiguration_1 {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job job() {
//        return jobBuilderFactory.get("StepJob")
//                .start(step1())
//                .next(step2())
//                .next(step3())
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("Step 1 has been executed");
//                        return RepeatStatus.FINISHED;
//                    }
//                })
//                .build();
//    }
//
//    @Bean
//    public Step step2() {
//        return stepBuilderFactory.get("step2")
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("step 2 has been executed");
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
//    // Custom Tasklet - CustomStepTasklet.class
//    @Bean
//    public Step step3() {
//        return stepBuilderFactory.get("step3")
//                .tasklet(new CustomStepTasklet1())
//                .build();
//    }
//
//}//class
