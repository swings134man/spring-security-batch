//package com.example.makeproject.batch.domain_detail.job.execution_decider;
//
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.job.flow.JobExecutionDecider;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
///************
// * @info : Batch - Execution Decider
// * @name : JobExecutionDeciderConfig
// * @date : 2023/03/01 6:16 PM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @Description : StepExecutionListener를 등록할 필요없이 Transition 처리를 위한 전용 클래스
// *
// * - decider.class(흐름결정) 에서 반환하는 return 값에 따라, 각기 다른 step을 실행.
// ************/
//@Configuration
//@RequiredArgsConstructor
//@Slf4j
//public class JobExecutionDeciderConfig {
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job job() {
//        return jobBuilderFactory.get("job_decider")
//                .start(step())
//                .next(decider())
//                .from(decider()).on("ODD").to(oddStep())
//                .from(decider()).on("EVEN").to(evenStep())
//                .end() //flow
//                .build();
//    }
//
//    /**
//     * Job Execution Deicer : Method
//     * @return CustomDecider.class
//     */
//    @Bean
//    public JobExecutionDecider decider() {
//        return new CustomDecider(); // CustomDecider.class
//    }
//
//    @Bean
//    public Step step() {
//        return stepBuilderFactory.get("step")
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("This is the start Tasklet");
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
//    /**
//     * evenStep Method : if return Value of decider() is "EVEN"
//     * this method will be run.
//     * And Connected by Flow Job
//     * @return null
//     */
//    @Bean
//    public Step evenStep() {
//        return stepBuilderFactory.get("evenStep")
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println(">>> EvenStep has executed");
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
//    /**
//     * oddStep : if return Value if decider() is "ODD"
//     * this method will be run.
//     * And Connected by Flow Job
//     * @return null
//     */
//    @Bean
//    public Step oddStep() {
//        return  stepBuilderFactory.get("oddStep")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println(">>> oddStep has executed");
//                        return RepeatStatus.FINISHED;
//                    }
//                })
//                .build();
//    }
//
//}
