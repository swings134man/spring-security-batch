//package com.example.makeproject.batch.test.param_test;
//
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//
//@Getter
//@Slf4j
//@NoArgsConstructor
//public class CreateDateJobParameter {
//
//    private LocalDate createDate;
//
//    @Value("#{jobParameters[createDate]}")
//    public void setCreateDate(String createDate) {
//        this.createDate = LocalDate.parse(createDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//    }
//
//
//
//}
