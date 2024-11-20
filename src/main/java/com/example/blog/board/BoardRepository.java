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

    public void update(int id, String title, String content) {
        Query q = em.createNativeQuery("update board_tb set title=?, content=? where id=?");
        q.setParameter(1, title);
        q.setParameter(2, content);
        q.setParameter(3, id);
        q.executeUpdate();
    }

    public void delete(int id) {
        Query q = em.createNativeQuery("delete from board_tb where id=?");
        q.setParameter(1, id);
        q.executeUpdate(); // insert, update, delete 때 사용
    }

    public void save(Board board) {
        // 객체를 만들어서 던지면 insert 자동으로 해준다.
        em.persist(board);
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
