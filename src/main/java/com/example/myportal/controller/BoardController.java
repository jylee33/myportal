package com.example.myportal.controller;

import com.example.myportal.domain.Board;
import com.example.myportal.domain.Member;
import com.example.myportal.service.BoardService;
import com.example.myportal.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/register")
    public void registerGET(Board board, Model model) {
        log.info("register get ......");
    }

    @PostMapping("/register")
    public String registerPOST(Board board, RedirectAttributes rttr) throws Exception {
        log.info("regist post ......");
        log.info(board.toString());

        boardService.regist(board);

        rttr.addFlashAttribute("msg",  "success");
        return "redirect:/board/listAll";
    }

    @GetMapping(value = "/listAll")
    public void listAll(Model model) throws Exception {
        log.info("show all list.....");
        List<Board> boardList = boardService.findBoards();
        model.addAttribute("boardList", boardList);
    }

    @GetMapping(value = "/read")
    public void read(@RequestParam("bno") int bno, Model model) throws Exception {
        model.addAttribute("board", boardService.read(bno).get());
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception {
        log.info("remove board.....");
        boardService.remove(bno);
        rttr.addFlashAttribute("msg", "success");
        return "redirect:/board/listAll";
    }

    @GetMapping(value = "/modify")
    public void modifyGET(int bno, Model model) throws Exception {
        model.addAttribute("board", boardService.read(bno).get());
    }

    @PostMapping("/modify")
    public String modifyPOST(Board board, RedirectAttributes rttr) throws Exception {
        log.info("mod post.....");

        boardService.modify(board);
        rttr.addFlashAttribute("msg", "success");

        return "redirect:/board/listAll";
    }

    @GetMapping(value = "/listPage")
    public String listPage(@RequestParam("page") int page, Model model) throws Exception {
        log.info("show list page ..... " + page);
        List<Board> boardList = boardService.listPage(page);
        model.addAttribute("boardList", boardList);

        return "board/listAll";
    }

}
