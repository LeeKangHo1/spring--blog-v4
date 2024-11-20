package com.example.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardRepository.class) // 이건 내가 만든 DB 자원이라 적어야 한다.
@DataJpaTest // DB 관련된 자원(EntityManager같은)들을 메모리(IoC)에 올린다.
public class BoardRepositoryTest {

    // 여기선 생성자 만들어서 못하니까 Autowired만 가능
    @Autowired
    private BoardRepository boardRepository;

//    @Test
//    public void update_test() {
//        // given
//        int id = 1;
//        String title = "testTitle";
//        String content = "testContent";
//        // when
//        boardRepository.update(id, title, content);
//        // eye
////        Board board = boardRepository.findById(id);
//        System.out.println(board.getId());
//        System.out.println(board.getTitle());
//        System.out.println(board.getContent());
//        System.out.println(board.getCreatedAt());
//    }
//
//    @Test
//    public void delete_test() {
//        // given
//        int id = 1;
//        // when
//        boardRepository.delete(id);
//        // eye, 없는 걸로 select하면 예외가 생겨서 findAll로
//        // 정석은 select하고 예외처리 코드도 작성
//        List<Board> boardList = boardRepository.findAll();
//        System.out.println("size : " + boardList.size());
//    }
//
//    @Test
//    public void save_test() {
//        // given
//        String title = "제목6";
//        String content = "내용6";
//
//        // when
////        boardRepository.save(title, content);
//
//        // eye
//        // findById을 만들어 뒀기 때문에 여기서 테스트 가능
//        // -> 기능을 순서대로 개발하면서 하나하나 테스트 해야 하는 이유
//        Board board = boardRepository.findById(6);
//        System.out.println(board.getId());
//        System.out.println(board.getTitle());
//        System.out.println(board.getContent());
//
//    } // rolleback (@Transactional) (@DataJpaTest 안에 포함)
//
//    @Test // test는 _ 사용하는 관례가 있다.
//    public void findAll_test() {
//        // given (메서드의 매개 변수)
//        // 테스트 코드는 findAll_test() ( ) 안에 매개 변수를 못 씀.
//
//        // when
//        List<Board> boardList = boardRepository.findAll();
//        System.out.println(); // debug 하려면 아무 메서드 적고 여기에 핀 찍으면 된다.
//
//        // eye
//        for (Board board : boardList) {
//            System.out.println(board.getId());
//            System.out.println(board.getTitle());
//            System.out.println(board.getContent());
//            System.out.println(board.getCreatedAt());
//            System.out.println("============================");
//        }
//    }
}
