//package com.example.makeproject.batch.domain_detail.step.jobStep;
//
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.*;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.job.DefaultJobParametersExtractor;
//import org.springframework.batch.core.step.job.JobParametersExtractor;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
///************
// * @info : job Step config class
// * @name : JobStepConfig
// * @date : 2023/01/16 6:09 PM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @Description :
// *
// * - StepBuilderFactory - StepBuilder - job(job) -> JobStepBuilder
// *
// * - Parent Job 내부 (- JobStep 내부 -> childJob 내부 -> Step1), (step2)
// *
// * - 실행시 childJob이 갖고 있는 stpe1이 실패한다면 -> step2로 가지않고 Parent Job 전체가 FAILED 된다.
// * -> childJob 성공 -> step2 실패시 parentJob은 실패, childJob = COMPLETED
// *
// ************/
//@Configuration
//@RequiredArgsConstructor
//@Slf4j
//public class JobStepConfig {
//
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    // 최상단 부모 job - 가장 기본이 되는 job
//    // jobStep 실행 후 성공시 -> step2 실행됨.
//    @Bean
//    public Job parentJob() {
//        return this.jobBuilderFactory.get("parentJob")
//                .start(jobStep(null))
//                .next(step2())
//                .build();
//    }
//
//    // job() 통해서 - childJob을 호출
//    // jobStep 은 다른 job을 호출하여 실행 시키도록 하는 역할을 담당함.
//    @Bean
//    public Step jobStep(JobLauncher jobLauncher) {
//        return stepBuilderFactory.get("jobStep")
//                .job(childJob())
//                .launcher(jobLauncher)
//                .parametersExtractor(jobParametersExtractor()) //
//                .listener(new StepExecutionListener() {
//                    @Override
//                    public void beforeStep(StepExecution stepExecution) {
//                        stepExecution.getExecutionContext().putString("name", "user1");
//                    }
//
//                    @Override
//                    public ExitStatus afterStep(StepExecution stepExecution) {
//                        return null;
//                    }
//                })
//                .build();
//    }
//
//    // 일반적인 job 의 기능 담당.
//    // 해당 job 이 COMPLETED 상태가 되어야 ParentJob의 다음 단계로 넘어간다.
//    @Bean
//    public Job childJob() {
//        return this.jobBuilderFactory.get("childJob")
//                .start(step1())
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                        // 실패하는 경우
////                        throw new RuntimeException("step1 was failed");
//                        return RepeatStatus.FINISHED;
//                    }
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
//                        return RepeatStatus.FINISHED;
//                    }
//                })
//                .build();
//    }
//
//    // JobStep() 에서 사용
//    // Step Excution 안에 context 객체 키:밸류
//    // Excution context 내부의 키를 기반으로 값을 가져올 수 있다. -> setKeys 를 통해 context 내부의 Key를 가져옴
//    // context 내부에 Key가 존재해야 한다.
//    public DefaultJobParametersExtractor jobParametersExtractor() {
//        DefaultJobParametersExtractor extractor = new DefaultJobParametersExtractor();
//        extractor.setKeys(new String[] {"name"}); //String[] type
//        return extractor;
//    }
//
//}
