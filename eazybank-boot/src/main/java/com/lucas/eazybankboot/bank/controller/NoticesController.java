package com.lucas.eazybankboot.bank.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.lucas.eazybankboot.bank.model.entity.Notice;
import com.lucas.eazybankboot.bank.repository.jpa_interface.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class NoticesController {

    private final NoticeRepository noticeRepository;

    @GetMapping("/notices")
    public ResponseEntity<List<Notice>> getNotices() {
        List<Notice> notices = noticeRepository.findAllActiveNotices();
        if (notices != null ) {
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS)) // 설정에따라 브라우저에 캐시저장 해당시간동안 서버에 요청하지 않음
                    .body(notices);
        }else {
            return null;
        }
    }

}
