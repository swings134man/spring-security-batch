package com.example.makeproject.board.controller;

import com.example.makeproject.board.domain.BoardDTO;
import com.example.makeproject.board.service.BoardService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService service;

    @ApiOperation(value = "게시판 작성 api", notes = "게시판글 작성시 저장한다.")
    @PostMapping("/api/savePost")
    public BoardDTO savePost(@RequestBody BoardDTO inDTO) {
//        BoardDTO dto = BoardDTO.builder()
//                .boardId(1L)
//                .title("title1")
//                .content("content1")
//                .writer("writer1")
//                .build();
        BoardDTO result = service.savePost2(inDTO);
        return result;
    }

    @ApiOperation(value = "게시판 전체 조회 api", notes = "조건없이 게시판글 전체 조회한다.(List<BoardDTO>)")
    @GetMapping("/api/findAll")
    public List<BoardDTO> findAll() {
        return service.findAll();
    }
}
