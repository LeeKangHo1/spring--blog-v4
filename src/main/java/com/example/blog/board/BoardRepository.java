package com.example.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    // 책임: DB와 상호작용

    // JPA는 EntityManager로 DB에 접근한다. (자바에서 DBConnection)
    private final EntityManager em;

    // 알고리즘 상 update 메서드는 필요 없다.

    public void delete(int id) {
        em.createQuery("delete from Board b where id = :id")
                .setParameter("id", id) // :id가 여기에 mapping
                .executeUpdate();
    }

    public void save(Board board) {
        // 객체를 넣으면 기본키가 없다 -> insert 자동으로 해준다.
        // 비영속
        em.persist(board);
        // 동기화 완료(영속화됨), 기본키와 생성날짜까지 포함된 board가 됨
        // 영속화돼서 하나의 트랜잭션(서비스 메서드) 내에서 SELECT하면 db까지 연락이 안 가고 em에서 바로 return
        // -> 통신? 비용 감소
    }

    public List<Board> findAll() {
        // jpql, 객체지향쿼리 -> 장점. 쿼리문 틀리면 알려준다.
        // Board는 객체니까 대문자 주의, b는 별칭
        return em.createQuery("select b from Board b order by b.id desc", Board.class)
                .getResultList();
    }

    public Optional<Board> findById(int id) {
//        Board board = em.find(Board.class, id);
//        return Optional.ofNullable(board);

        // em.find(Board.class, id) -> id에 해당하는 Board있어?
        // Optional로 예외처리
        return Optional.ofNullable(em.find(Board.class, id));
    }
}
