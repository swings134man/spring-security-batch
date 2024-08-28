package com.example.makeproject.board.service;

import com.example.makeproject.board.domain.Board;
import com.example.makeproject.board.domain.BoardDTO;
import com.example.makeproject.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    private final BoardRepository repository;
    private final ModelMapper modelMapper;
    private final Logger logger = LoggerFactory.getLogger(getClass());


    public List<BoardDTO> findAll () {
        // 조회
        List<Board> entity = repository.findAll();

        // List 바인딩.
        List<BoardDTO> resultDTO = entity.stream()
                                            .map(data -> modelMapper.map(data, BoardDTO.class))
                                            .collect(Collectors.toList());

        return resultDTO;
    }


    public BoardDTO savePost2(BoardDTO boardDTO) {
        // DTO to Entity
        Board entity = modelMapper.map(boardDTO, Board.class);
        logger.info("To Entity : " + entity.toString());

        // save
        Board saveEntity = repository.save(entity);
        logger.info("save Entity : " + saveEntity.toString());

        // Entity To DTO
        BoardDTO dto = modelMapper.map(saveEntity, BoardDTO.class);
        logger.info("To DTO : " + dto);

        return dto;

    }

    public BoardDTO savePost(BoardDTO boardDTO) {
        // DTO to Entity
        Board entity = Board.toEntity(boardDTO);
        logger.info("To Entity : " + entity.toString());

        // save
        Board saveEntity = repository.save(entity);
        logger.info("save Entity : " + saveEntity.toString());

        // Entity To DTO
        BoardDTO dto = BoardDTO.toDTO(saveEntity);
        logger.info("To DTO : " + dto);

        return dto;

    }




}
