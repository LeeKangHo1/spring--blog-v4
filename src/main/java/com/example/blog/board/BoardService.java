package com.example.blog.board;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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

    public BoardResponse.DetailDTO 게시글상세보기(int id) {
        Board board = boardRepository.findById(id);
        return new BoardResponse.DetailDTO(board);
    }

    // 이게 있어야 commit을 한다. 메서드 실행 도중 문제가 생기면 롤백
    @Transactional
    public void 게시글쓰기(BoardRequest.SaveDTO saveDTO) {
        boardRepository.save(saveDTO.getTitle(), saveDTO.getContent());
    }
}
