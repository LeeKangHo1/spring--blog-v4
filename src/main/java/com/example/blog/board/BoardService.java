package com.example.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    // 책임: 비즈니스 로직

    private final BoardRepository boardRepository;

    public List<BoardResponse.DTO> 게시글목록보기() {
        List<BoardResponse.DTO> dtos = new ArrayList<>();

        List<Board> boardList = boardRepository.findAll();

        // 딥 카피
        for (Board board : boardList) {
            BoardResponse.DTO dto = new BoardResponse.DTO(board);
            dtos.add(dto);
        }
        return dtos;
    }

    public Board findById(int id) {
        Board board = boardRepository.findById(id);
        return board;
    }
}
