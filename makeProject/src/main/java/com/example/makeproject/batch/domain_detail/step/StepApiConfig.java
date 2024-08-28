//package com.example.makeproject.batch.domain_detail.step;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
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
// * @info : Step의 다양한 API
// * @name : StepApiConfig
// * @date : 2023/01/15 6:06 PM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @Description :
// *
// * - startLimit()
// * - allow-start-if-complete
// * - 2가지 api 를 설정하고 확인한다.
// ************/
//@Configuration
//@RequiredArgsConstructor
//@Slf4j
//public class StepApiConfig {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job job() {
//        return jobBuilderFactory.get("stepApi_job")
//                .start(step1())
//                .next(step2())
//                .build();
//    }
//
//
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
////                        System.out.println("step1 has execute");
//                        System.out.println("stepContribution = " + contribution + "chunkContext = " + chunkContext);
//                        return RepeatStatus.FINISHED;
//                    }
//                })
//                // step이 성공하던, 실패하던 무조건 실행됨.
//                .allowStartIfComplete(true)
//                .build();
//    }
//
//    @Bean
//    public Step step2() {
//        return stepBuilderFactory.get("step2")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
////                        System.out.println("step2 has execute");
//                        System.out.println("stepContribution = " + contribution + "chunkContext = " + chunkContext);
//                        // 테스트를 위한 exception
//                        throw new RuntimeException("step2 was failed");
//
////                        return RepeatStatus.FINISHED;
//                    }
//                })
//                // step 반복 횟수 - 해당 횟수를 넘어가면 Exception 3번 이상 실행되지 않는다.
//                .startLimit(3)
//                .build();
//    }
//
//}
