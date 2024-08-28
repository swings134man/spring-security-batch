//package com.example.makeproject.batch.domain_detail.job;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.*;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///************
// * @info : Simple Job 예제
// * @name : SimpleJobConfig_2
// * @date : 2023/01/12 3:42 PM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @Description :
// *
// * - 아래 클래스에서 Simple Job이 생성됨.
// * - JobBuilderFactory -> JobBuilder -> SimpleJobBuilder -> SimpleJob
// ************/
//@Configuration
//@RequiredArgsConstructor
//@Slf4j
//public class SimpleJobConfig_2 {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job job() {
//        return jobBuilderFactory.get("simpleJob1")
//                .start(step1())
//                .next(step2())
//                .next(step3())
//                .incrementer(new RunIdIncrementer()) // paramters 자동 증가
//                .validator(new JobParametersValidator() { // job 검증.
//                    @Override
//                    public void validate(JobParameters parameters) throws JobParametersInvalidException {
//
//                    }
//                })
//                .preventRestart() // job 재시작 여부
//                .listener(new JobExecutionListener() { // job 리스너.
//                    @Override
//                    public void beforeJob(JobExecution jobExecution) {
//
//                    }
//
//                    @Override
//                    public void afterJob(JobExecution jobExecution) {
//
//                    }
//                })
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1")
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("step1 has executed");
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
//    @Bean
//    public Step step2() {
//        return stepBuilderFactory.get("step2")
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("step2 has executed");
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
//    @Bean
//    public Step step3() {
//        return stepBuilderFactory.get("step3")
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("step3 has executed");
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//}
