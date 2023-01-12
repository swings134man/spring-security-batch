//package com.example.makeproject.batch.domain_detail.job.apis;
//
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersInvalidException;
//import org.springframework.batch.core.JobParametersValidator;
//
///************
// * @info : Validator APIS CUSTOM 클래스
// * @name : CustomJobParametesValidator
// * @date : 2023/01/12 6:01 PM
// * @author : SeokJun Kang(swings134@gmail.com)
// * @version : 1.0.0
// * @Description :
// *
// * - ValidatorConfig_1 클래스에서 사용
// * - Simple Job의 Validator 기능을 커스텀하여 사용하기 위한 클래스임.
// *
// * -  Job을 수행할 때 넘겨준 job parameters 값을 활용하여 검증한다.(key:value)
// *
// * - Job이 수행되기전에 Job Repository의 기능이 시작하기전에 한번(simple job launcher), job이 실행되기전 1번(simple Job -> abstract job) - 총2번 검증하게 됨.
// ************/
//public class CustomJobParametesValidator implements JobParametersValidator {
//
//    @Override
//    public void validate(JobParameters parameters) throws JobParametersInvalidException {
//        // 만약 parameters에 name 이라는 key 가 존재하지 않으면 Exception 발생. -> job이 수행 되지 않는다.
//        // simple job Launcher 에서 걸려서 job이 생성조차 되지 않는다.
//       if(parameters.getString("name") == null) {
//           throw new JobParametersInvalidException("name parameters is not found");
//       }
//    }//over
//}
