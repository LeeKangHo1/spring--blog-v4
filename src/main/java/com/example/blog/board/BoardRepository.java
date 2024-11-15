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

    public List<Board> findAll() {
        // mybatis에서 mapper 설정과 유사, rs.next() 등 이런거 안해도 돼
        Query q = em.createNativeQuery("select * from board_tb order by id desc", Board.class);
        return q.getResultList();
    }

    public Board findById(int id) {
        Query q = em.createNativeQuery("select * from board_tb where id = ?", Board.class);
        return (Board) q.setParameter(1, id).getSingleResult();
    }

}
