package com.example.myportal.repository;

import com.example.myportal.domain.Board;
import com.example.myportal.domain.Criteria;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
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

    public Optional<Board> findByBno(Integer bno, boolean bReadMode) {
        log.info("findByBno, bno={}", bno);
        if (bReadMode) {
            Board findBoard = em.find(Board.class, bno);
            findBoard.setViewcnt(findBoard.getViewcnt() + 1);
        }

        List<Board> result = em.createQuery("select b from Board b where b.bno =: bno", Board.class)
                .setParameter("bno", bno)
                .getResultList();

        return result.stream().findAny();
    }

    public List<Board> findAll() {

        return em.createQuery("select b from Board b order by bno desc", Board.class)
                .getResultList();

    }

    public void update(Board board) {
        Board findBoard = em.find(Board.class, board.getBno());
        findBoard.setTitle(board.getTitle());
        findBoard.setContent(board.getContent());
        findBoard.setWriter(board.getWriter());
    }

    public void delete(Integer bno) {
        em.remove(em.find(Board.class, bno));
    }

    public List<Board> listPage(int page) {

        if(page <= 0) {
            page = 1;
        }

        page = (page - 1) * 10;

        return em.createQuery("select b from Board b order by bno desc", Board.class)
                .setFirstResult(page)
                .setMaxResults(10)
                .getResultList();
    }

//    public List<Board> listCriteria(Criteria cri) {
//        return session.selectList(namespace + ".listCriteria", cri);
//    }

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
