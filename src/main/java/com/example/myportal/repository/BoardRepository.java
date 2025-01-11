package com.example.myportal.repository;

import com.example.myportal.domain.Board;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BoardRepository {

    private final EntityManager em;

    @Autowired
    public BoardRepository(EntityManager em) {
        this.em = em;
    }

    public Board create(Board board) {
        em.persist(board);
        return board;
    }

    public Optional<Board> findByBno(Integer bno) {
        List<Board> result = em.createQuery("select b from Board b where b.bno =: bno", Board.class)
                .setParameter("bno", bno)
                .getResultList();

        return result.stream().findAny();
    }

    public List<Board> findAll() {

        return em.createQuery("select b from Board b", Board.class)
                .getResultList();

    }

//    public void update(Board board) {
//        session.update(namespace + ".update", board);
//    }
//
//    @Override
//    public void delete(Integer bno) {
//        session.delete(namespace + ".delete", bno);
//    }
//
//    public List<Board> listPage(int page) {
//
//        if(page <= 0) {
//            page = 1;
//        }
//
//        page = (page - 1) * 10;
//
//        return session.selectList(namespace + ".listPage", page);
//    }
//
//    public List<Board> listCriteria(Criteria cri) {
//        return session.selectList(namespace + ".listCriteria", cri);
//    }
//
//    public int countPaging(Criteria cri) {
//        return session.selectOne(namespace + ".countPaging", cri);
//    }
//
//    public List<Board> listSearch(SearchCriteria cri) {
//        return session.selectList(namespace + ".listSearch", cri);
//    }
//
//    public int listSearchCount(SearchCriteria cri) {
//        return session.selectOne(namespace + ".listSearchCount", cri);
//    }


}
