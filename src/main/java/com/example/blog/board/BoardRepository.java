package com.example.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    // 책임: DB와 상호작용

    // JPA는 EntityManager로 DB에 접근한다. (자바에서 DBConnection)
    private final EntityManager em;

    public void save(String title, String content) {
        Query q = em.createNativeQuery("insert into board_tb (title, content, created_at) values(?, ?, now())");
        q.setParameter(1, title);
        q.setParameter(2, content);
        q.executeUpdate(); // 쿼리문 실행 해
        // 잘 됬을 때 return 1 하지 않는다. -> SRP에 따라 처리할 다른 클래스 생성
        // 여기서 커밋하는게 아니다. insert문 실행만 된 상태
    }

    public List<Board> findAll() {
        // mybatis에서 mapper 설정과 유사, rs.next() 등 이런거 안해도 돼
        Query q = em.createNativeQuery("select * from board_tb order by id desc", Board.class);
        return q.getResultList();
    }

    public Board findById(int id) {
        Query q = em.createNativeQuery("select * from board_tb where id = ?", Board.class);
        // 물음표 완성하기 (물음표 순서, 물음표에 바인딩될 변수값)
        q.setParameter(1, id);
        return (Board) q.getSingleResult();
    }
}
