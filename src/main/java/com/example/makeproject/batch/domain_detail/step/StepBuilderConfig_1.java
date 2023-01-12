//package com.example.makeproject.batch.domain_detail.step;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.job.builder.FlowBuilder;
//import org.springframework.batch.core.job.flow.Flow;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.item.*;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
///************
// * @info : Step Builder - step 에대한 기본정보를 위한 클래스
// * @name : StepBuilderConfig_1
// * @date : 2023/01/13 2:49 AM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @Description :
// *
// * - Step 에 하위빌더들의 모음 예제 클래스임.
// * - StepBuilderFactory를 통해서 StepBuilder 클래스 가 생성되고
// * ---> stepBuilder가 각각의 하위 빌더클래스들을 선택하고 빌드하게됨 그리고 각각의 다른종류의 step 객체를 생성한다!!!!
// * ---> 하위 빌더클래스란? : 아래에 step 메서드들이 그 대표적인 예제이다.
// *
// ************/
//@Configuration
//@RequiredArgsConstructor
//@Slf4j
//public class StepBuilderConfig_1 {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job job() {
//        return jobBuilderFactory.get("step1_job")
//                .incrementer(new RunIdIncrementer())
//                .start(step1())
//                .next(step2())
//                .next(step3())
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
//    }// step1
//
//    // step2 - chunk 기반의 작업처리
//    // chunk 기반 작업에는 3가지의 기능이 존재한다. reader(읽기). processor(데이터가공), write(쓰기)
//    @Bean
//    public Step step2() {
//        return stepBuilderFactory.get("step2")
//                .<String, String>chunk(3)
//                .reader(new ItemReader<String>() {
//                    @Override
//                    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//                        return null;
//                    }
//                })
//                .processor(new ItemProcessor<String, String>() {
//                    @Override
//                    public String process(String item) throws Exception {
//                        return null;
//                    }
//                })
//                .writer(new ItemWriter<String>() {
//                    @Override
//                    public void write(List<? extends String> items) throws Exception {
//
//                    }
//                })
//                .build();
//    }// step2
//
//    //step3 : partition : 멀티 스레드 (관련작업) : 스텝을 여러개로 나눠서 동시작업
//    @Bean
//    public Step step3() {
//        return stepBuilderFactory.get("step3")
//                .partitioner(step1())
//                .gridSize(2)
//                .build();
//    }//step3
//
//    // step4 : 해당 스텝이 실행될때 아래에 포함된 job을 실행시킴 ->>> 즉 스텝이 잡을 품고 있음.
//    @Bean
//    public Step step4() {
//        return stepBuilderFactory.get("step4")
//                .job(job())
//                .build();
//    }// step4
//
//    // step5 : Flow Step : flow 타입으로 실행 -> jobStep, flowStep 은 step 안에서 job, flow를 실행시킴. (전형적인 step)
//    @Bean
//    public Step step5() {
//        return stepBuilderFactory.get("step5")
//                .flow(flow())
//                .build();
//    }// step5
//
//    // step 예제를 위한 메서드
//
//    // step4 가 실행될때 아래의 job이 실행
//    @Bean
//    public Job jobTest() {
//        return this.jobBuilderFactory.get("inner_step4_job")
//                .start(step1())
//                .next(step2())
//                .next(step3())
//                .build();
//    }
//
//    // step5 : Flow 를 실행시키기 위한 flow 객체.
//    @Bean
//    public Flow flow() {
//        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flow");
//        flowBuilder.start(step2()).end();
//        return flowBuilder.build();
//    }
//
//
//
//}
