package com.example.myportal.service;

import com.example.myportal.domain.Board;
import com.example.myportal.domain.Member;
import com.example.myportal.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class BoardService {

    private BoardRepository repository;

    @Autowired
    public BoardService(BoardRepository repository) {
        this.repository = repository;
    }

    public Integer regist(Board board) {
        repository.create(board);
        return board.getBno();
    }

    public List<Board> findBoards() {
        return repository.findAll();
    }

    public Optional<Board> read(Integer bno, boolean bReadMode) {
        return repository.findByBno(bno, bReadMode);
    }

    public void modify(Board board) {
        repository.update(board);
    }

    public void remove(Integer bno) {
        repository.delete(bno);
    }

    public List<Board> listPage(Integer page) {
        return repository.listPage(page);
    }
//
//    public List<BoardVO> listCriteria(Criteria cri) {
//        return dao.listCriteria(cri);
//    }
//
//    public int listCountCriteria(Criteria cri) {
//        return dao.countPaging(cri);
//    }
//
//    public List<BoardVO> listSearchCriteria(SearchCriteria cri) {
//        return dao.listSearch(cri);
//    }
//
//    public int listSearchCount(SearchCriteria cri) {
//        return dao.listSearchCount(cri);
//    }
}
