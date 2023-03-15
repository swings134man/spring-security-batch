//package com.example.makeproject.batch.chunk_basic.basic_1;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.support.ListItemReader;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Arrays;
//import java.util.List;
//
///************
// * @info : Batch - Chunk Oriented Tasklet
// * @name : ChunkOrientedTaskletConfig_2
// * @date : 2023/03/09 5:11 PM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @Description :
// *
// * - Tasklet의 구현체이며, Chunk 지향 프로세싱을 담당하는 도메인 객체.
// *
// * -> 순서: TaskletStep -> ChunkOrientedTasklet (provide())-> chunkProivder -> chunkProcessor -> ItemRead -> itemProcessor -> ItemWriter
// *
// *
// ************/
//@Configuration
//@RequiredArgsConstructor
//@Slf4j
//public class ChunkOrientedTaskletConfig_2 {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job cotJob() {
//        return jobBuilderFactory.get("cot_job")
//                .start(step1())
//                .next(step2())
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1")
//                .<String, String>chunk(2)
//                .reader(new ListItemReader<>(Arrays.asList("item1","item2","item3","item4","item5","item6")))
//                .processor(new ItemProcessor<String, String>() {
//                    @Override
//                    public String process(String item) throws Exception {
//                        return  "my_"+item;
//                    }
//                })
//                .writer(new ItemWriter<String>() {
//                    @Override
//                    public void write(List<? extends String> items) throws Exception {
//                        items.forEach(item -> System.out.println(item));
//                    }
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
//                }).build();
//    }
//
//
//}
