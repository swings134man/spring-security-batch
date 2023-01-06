//package com.example.makeproject.batch.domain_basic.job_domain.launcher;
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
// * @info : job launcher test cofig class
// * @name : JobLauncherConfig
// * @date : 2023/01/06 10:26 PM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @Description :
// *
// *  - 스프링부트가 batch를 실행하는것이 아닌 launcher가 실행하게 하는 방법에 대한 package
// *  - 배치 동기적, 비동기적 실행을 위한 Test
// *  - YML : batch.job.enable=false 설정.
// *
// *  - Job을 실행 시키는 Launcher는 Controller 내부에 존재함
// *  - Logic
// *    -> Request 가 들어옴(파라미터) -> 해당 파라미터를 배치 파라미터로 설정 후 -> 배치 실행
// ************/
//@Configuration
//@RequiredArgsConstructor
//@Slf4j
//public class JobLauncherConfig {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job job() {
//        return jobBuilderFactory.get("launcherJob")
//                .start(step1())
//                .next(step2())
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1")
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("step1 was executed");
//                    Thread.sleep(3000); // 3s
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
//    @Bean
//    public Step step2() {
//        return stepBuilderFactory.get("step2")
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("step2 was executed");
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
//
//}
