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

    public List<BoardResponse.ReadDTO> 게시글목록보기() {
        return boardRepository.findAll().stream()
//                .map(board -> new BoardResponse.ReadDTO(board))
                // (클래스::new), ReadDTO객체의 생성자는 1개만 있어야 한다.
                .map(BoardResponse.ReadDTO::new)
                .toList();
    }

    // 게시글상세보기랑 같은 내용이지만 따로 만드는게 좋다.
    public BoardResponse.UpdateFormDTO 게시글수정화면보기(int id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 id의 게시글이 없습니다 : " + id));
        return new BoardResponse.UpdateFormDTO(board);
    }

    public BoardResponse.DetailDTO 게시글상세보기(int id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 id의 게시글이 없습니다 : " + id));
        return new BoardResponse.DetailDTO(board);
    }

    @Transactional
    public void 게시글쓰기(BoardRequest.SaveDTO saveDTO) {
        boardRepository.save(saveDTO.toEntity());
    }

    @Transactional
    public void 게시글삭제(int id) {
        boardRepository.delete(id);
    }

    @Transactional
    public void 게시글수정하기(int id, BoardRequest.UpdateDTO updateDTO) {
        // 1. 조회
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 id의 게시글이 없습니다 : " + id));
        // 2. 업데이트
        board.update(updateDTO.getTitle(), updateDTO.getContent());
    } // 게시글수정하기 메서드 실행 후 JPA가 더티 체킹을 실행
    // 영속화된 객체 상태 변경을 감지하면 -> update + commit 발생 (더티 체킹)
}
