//package com.example.makeproject.batch.first_job;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameters;
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
//import java.util.Map;
//
///************
// * @info : job parameter Ex class
// * @name : JobParameterConfiguration
// * @date : 2022/12/29 7:02 PM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @Description :
// *
// *
// ************/
//
//@Configuration
//@RequiredArgsConstructor
//public class JobParameterConfiguration {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job BatchJob() {
//        return jobBuilderFactory.get("JobParam")
//                .start(step1())
//                .next(step2())
//                .build();
//    }
//
//    // StepContribution - stepExcution - JobExution 내부에 JobParameters 가 존재함. (참조)
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//
//                        // contribution
//                        JobParameters jobParameters = contribution.getStepExecution().getJobExecution().getJobParameters();
//                        jobParameters.getString("name");
//                        jobParameters.getLong("seq");
//                        jobParameters.getDate("date");
//                        jobParameters.getDouble("age");
//
//                        // chunkContext - Map
//                        Map<String, Object> jobParameters1 = chunkContext.getStepContext().getJobParameters();
//                        jobParameters1.get("name");
//                        jobParameters1.get("seq");
//
//
//                        System.out.println("step1 has executed");
//                        return RepeatStatus.FINISHED;
//                    }
//                })
//                .build();
//    }
//
//    @Bean
//    public Step step2() {
//        return stepBuilderFactory.get("step1")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("step2 has executed");
//                        return RepeatStatus.FINISHED;
//                    }
//                })
//                .build();
//    }
//
//}//class
