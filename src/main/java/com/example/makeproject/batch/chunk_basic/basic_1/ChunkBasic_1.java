//package com.example.makeproject.batch.chunk_basic.basic_1;
//
//import lombok.RequiredArgsConstructor;
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
// * @info : Batch - Chunk 기본
// * @name : ChunkBasic_1
// * @date : 2023/03/09 1:29 AM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @Description : Batch Chunk API 기본1
// *
// * - Chunk : 여러 아이템들을 묶어서 하나의 덩어리로 만들고(Chunk), 각 덩어리들을 트랜잭션 처리를 하고, 각각의 프로세스를 진행함.
// *
// * - ItemReader,Processor,Writer 는 익명클래스로 생성 후 진행했음.
// *
// * - Chunk<Input> , Chunk<Output> 이 존재.
// *      -> input : Reader에서 읽으면 List형태로 저장
// *      -> output : Processor에서 가공,필터링한 데이터가 저장되는곳, Writer에 전달함.
// *  -> SimpleChunkProcessor.java 에서 chunk<in,out> 관련 작업이 이루어짐.
// *
// * - Chunk Size
// *      -> chunk API 사용시, chunkSize를 지정하면 size만큼의 데이터를 처리한다. (size보다 많으면, size만큼 실행후 다음 데이터실행)
// *
// * - Chunk.java 파일 내부에 기본적인 Chunk 동작에 필요한 메서드들이 존재함.
// *      -> 또한 내부에 ChunkIterator 가 존재하여, 여러개의 데이터를 처리함.
// ************/
//@Configuration
//@RequiredArgsConstructor
//public class ChunkBasic_1 {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job chunk_basic_job() {
//        return jobBuilderFactory.get("chunk_1")
//                .start(step1())
//                .next(step2())
//                .build();
//    }
//
//    /**
//     * Chunk 기반 Step
//     */
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1")
//                // chunk 기반 API : Generic 을 통해 어떤 타입인지 명시 가능.
//                .<String, String>chunk(5)
//                .reader(new ListItemReader<>(Arrays.asList("item1","item2","item3","item4","item5")))
//                .processor(new ItemProcessor<String, String>() {
//                    @Override
//                    public String process(String item) throws Exception {
//                        // Process 는 item을 개별적으로 처리함. -> Chunk<O> 아웃풋 API 에 저장후 writer로 전달.
//                        Thread.sleep(300); // 0.3 초 주기로 item 처리.
//                        System.out.println("item = " + item);
//
//                        return "my" + item;
//                    }
//                })
//                .writer(new ItemWriter<String>() {
//                    @Override
//                    public void write(List<? extends String> items) throws Exception {
//                        // 개별 처리가 아닌 List로 일괄처리.
//                        Thread.sleep(300); // 0.3초 주기로 실행.
//                        System.out.println("items = " + items);
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
