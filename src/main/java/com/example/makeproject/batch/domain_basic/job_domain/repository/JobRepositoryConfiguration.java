//package com.example.makeproject.batch.domain.job_domain.repository;
//
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.*;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///************
// * @info : job repository 실행 클래스
// * @name : JobRepositoryConfiguration
// * @date : 2023/01/06 2:35 AM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @Description :
// ************/
//@Configuration
//@RequiredArgsConstructor
//@Slf4j
//public class JobRepositoryConfiguration {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//    private final JobExecutionListener jobRepositoryListener;
//
//    @Bean
//    public Job job() {
//
//        return jobBuilderFactory.get("JRepositoryTest")
//                .start(step1())
//                .next(step2())
//                .listener(jobRepositoryListener)
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1")
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("----- step1 has executed------");
//
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
//    @Bean
//    public Step step2() {
//        return stepBuilderFactory.get("step2")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("----- step2 has executed------");
//                        return RepeatStatus.FINISHED;
//                    }
//                })
//                .build();
//    }
//}
