package com.example.makeproject.batch.domain_detail.job.apis;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;

import java.text.SimpleDateFormat;
import java.util.Date;

/************
 * @info : Increment Custom Class
 * @name : CustomJobParametersIncrement
 * @date : 2023/01/13 1:59 AM
 * @author : SeokJun Kang(swings134@gmail.com)
 * @version : 1.0.0
 * @Description :
 *
 * - Job의 Increment 메서드 커스텀 클래스.
 * - 해당 커스텀 클래스 실행 job은 ValidatorConfig_1.java 에 2번째 job 메서드임.
 *
 * - 해당 increment custom 방식은 1씩증가(default)가 아닌 날짜객체를 파라미터로 리턴.
 ************/
public class CustomJobParametersIncrement implements JobParametersIncrementer {

    static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-hhmmss");

    // job paramter에 새로운 객체가 리턴된다. (DB 저장) default는 1++ 증가
    // 아래의 커스텀은 날짜로 계산
    @Override
    public JobParameters getNext(JobParameters parameters) {

        String id = format.format(new Date()); // ex) 20230112-103200

        // 파라미터 객체 return
        return new JobParametersBuilder().addString("run.id",id).toJobParameters(); // type: String
    }
}
