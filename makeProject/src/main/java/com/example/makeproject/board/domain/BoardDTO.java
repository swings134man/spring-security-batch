package com.example.makeproject.board.domain;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

    private Long boardId;   /*게시판 아이디*/
    private String title;   /*게시판 제목*/
    private String content; /*게시판 내용*/
    private String writer;  /*작성자*/


    public static BoardDTO toDTO(Board entity) {
        return BoardDTO.builder()
                .boardId(entity.getBoardId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .build();
    }

}
