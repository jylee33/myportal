package com.example.myportal.repository;

import com.example.myportal.domain.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired BoardRepository boardRepository;

    @Test
    @Transactional
    public void testCreateBoard() {
        Board board = new Board();

        board.setTitle("title");
        board.setContent("content");
        board.setWriter("writer");
        board.setRegdate(new Date());

        int createdBno = boardRepository.create(board);
        Optional<Board> findBoard = boardRepository.findByBno(createdBno, true);

        assertThat(findBoard.get().getBno()).isEqualTo(board.getBno());
        assertThat(findBoard.get().getRegdate()).isEqualTo(board.getRegdate());
    }

    @Test
    public void testUpdateBoard() {

    }
}