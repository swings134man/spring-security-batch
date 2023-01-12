//package com.example.makeproject.batch.domain_detail.job.apis;
//
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.job.DefaultJobParametersValidator;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///************
// * @info : SimpleJob API - Validator API 에 관한 클래스
// * @name : ValidatorConfig_1
// * @date : 2023/01/12 5:57 PM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @Description :
// *
// * - job 은 Validator, increment 2가지 기능을 사용하기 위해 2가지로 나누어짐. (주석처리)
// *
// ************/
//@Configuration
//@RequiredArgsConstructor
//@Slf4j
//public class ValidatorConfig_1 {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    // ValiDator 를 테스트 하기위한 job
////    @Bean
////    public Job job() {
////        return jobBuilderFactory.get("vali_job")
////                .start(step1())
////                .next(step2())
////                .next(step3())
//////                .validator(new CustomJobParametesValidator()) // custom valid calss
//////                .validator(new DefaultJobParametersValidator(new String[]{"name", "date"}, new String[]{"count"})) // batch 제공 기본 validator (필수키 , option키) 둘다 인자를 배열로 주어야 함.
////                .build();
////    }
//
//    // Increment 테스트만을 위한 job
//    @Bean
//    public Job job() {
//        return jobBuilderFactory.get("increment_job")
//                .start(step1())
//                .next(step2())
//                .next(step3())
////                .incrementer(new RunIdIncrementer()) // Batch 제공 기본 Increment
//                .incrementer(new CustomJobParametersIncrement()) // custom increment class
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
