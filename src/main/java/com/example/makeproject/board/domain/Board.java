package com.example.makeproject.board.domain;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Table(name = "board_info")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;   /*게시판 아이디*/
    private String title;   /*게시판 제목*/
    private String content; /*게시판 내용*/
    private String writer;  /*작성자*/

    public static Board toEntity(BoardDTO dto) {
        return Board.builder()
                .boardId(dto.getBoardId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
    }
}
