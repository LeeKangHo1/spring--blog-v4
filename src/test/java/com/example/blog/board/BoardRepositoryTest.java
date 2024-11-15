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

    @Test // test는 _ 사용하는 관례가 있다.
    public void findAll_test() {
        // given (메서드의 매개 변수)
        // 테스트 코드는 findAll_test() ( ) 안에 매개 변수를 못 씀.

        // when
        List<Board> boardList = boardRepository.findAll();
        System.out.println(); // debug 하려면 아무 메서드 적고 여기에 핀 찍으면 된다.

        // eye
        for (Board board : boardList) {
            System.out.println(board.getId());
            System.out.println(board.getTitle());
            System.out.println(board.getContent());
            System.out.println(board.getCreatedAt());
            System.out.println("============================");
        }
    }
}
