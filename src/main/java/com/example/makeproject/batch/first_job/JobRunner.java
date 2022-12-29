//package com.example.makeproject.batch.first_job;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
///************
// * @info :
// * @name : JobRunner
// * @date : 2022/12/28 6:37 PM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @Description :
// *
// * -- Job Launcher Test 예제
// * - 수동으로 Batch Job 을 실행 하는 예제.
// * - 실행 방식을 커스텀 한것.
// * - yml 에서 batch.enable = false 설정시에만 해당 클래스가 실행가능.
// *
// * 1. ApplicationRunner : Spring Boot 실행시 먼저 실행시키도록 하는 Interface
// * 2. JobParameter 를 생성하기 위해서 사용 할 수도 있음.
// * 3. 실행중에 주입 받는 방법도 존재함.
// *
// ************/
//@Component
//public class JobRunner implements ApplicationRunner {
//
//    @Autowired
//    private JobLauncher jobLauncher; //배치가 초기화 될때 Bean으로 생성되어 있기 때문에 주입가능.
//
//    @Autowired
//    private Job job; // Job 이 Bean 객체로 생성되어 있음 -> batch file
//
//
//    // JobLauncher 를 통해 실행
//    // JobInstance.class
////    @Override
////    public void run(ApplicationArguments args) throws Exception {
////
////        // parameters - 파라미터를 만들 수 있는 빌더 객체 제공.
////        JobParameters jobParameters = new JobParametersBuilder()
////                .addString("name", "user2")
////                .toJobParameters();
////
////        jobLauncher.run(job, jobParameters);
////    }
//
//}
