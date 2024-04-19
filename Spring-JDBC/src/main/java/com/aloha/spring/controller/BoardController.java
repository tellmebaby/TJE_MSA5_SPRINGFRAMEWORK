package com.aloha.spring.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aloha.spring.dto.Board;
import com.aloha.spring.service.BoardService;

@Controller							// 컨트롤러로 빈 등록
@RequestMapping("/board")
public class BoardController {

	private static final Logger logger
		= LoggerFactory.getLogger(BoardController.class);

	@Autowired			// 의존성 자동 주입
	private BoardService boardService;
	
	/**
	 * 게시글 목록
	 * @param model
	 * @return
	 * @throws Exception
	 */
	//@RequestMapping(value = "/list", method = RequestMethod.GET)
	@GetMapping("/list")			// Spring 4.3버전 사용 가능
	public String list(Model model) throws Exception {
		List<Board> boardList = boardService.list();
		model.addAttribute("boardList", boardList);
		return "board/list";			// board/list.jsp 화면 응답
	}
	
	/**
	 * 게시글 조회
	 * @param model
	 * @param no
	 * @return
	 */
	@GetMapping("/read")
	public String select(Model model, int no) {
		Board board = boardService.select(no);
		model.addAttribute("board" , board);
		
		return "board/read";
	}
	
	// 게시글 등록 			- /board/insert - [GET]
	/**
	 * 게시글 등록
	 * @param model
	 * @return
	 */
	@GetMapping("/insert")
	public String insert(Model model) {

		return "board/insert";
	}
	
	// 게시글 등록 처리		- /board/insert - [POST]
	/**
	 * 게시글 등록 처리
	 * @param model
	 * @param board
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/insertPro")
	public String insertPro(Model model, Board board) throws Exception{
		int result = boardService.insert(board);
		
		if (result == 1) {
			System.out.println("등록 성공");
			model.addAttribute("board", board);
		}else {
			System.out.println("등록 실패");
			return "/insert";
		}
		
		return "redirect:/board/list";
	}
	
	// 게시글 수정			- /board/update - [GET]
	/**
	 * 게시글 수정
	 * @param model
	 * @param no
	 * @return
	 */
	@GetMapping("/update")
	public String update(Model model, int no) {
		Board board = boardService.select(no);
		model.addAttribute("board" , board);
		
		return "board/update";
	}
	
	// 게시글 수정 처리		- /board/update - [POST]
	/**
	 * 게시글 수정 처리
	 * @param model
	 * @param board
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/updatePro")
	public String updatePro(Model model, Board board) throws Exception{
		int result = boardService.update(board);
		
		if (result == 1) {
			System.out.println("수정 성공");
			logger.info("게시글 수정 성공 : " + result);
		}else {
			System.out.println("수정 실패");
			return "/insert";
		}
		
		return "redirect:/board/list";
	}
	
	// 게시글 삭제 처리		- /board/delete - [POST]
	/**
	 * 게시글 삭제 처리
	 * @param model
	 * @param no
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/delete")
	public String delete(Model model, int no) throws Exception{
		int result = boardService.delete(no);
		
		if (result == 1) {
			System.out.println("삭제 성공");
			logger.info("게시글 삭제 성공 : " + result);
		}else {
			System.out.println("삭제 실패");
			return "redirect: /read";
		}
		
		return "redirect:/board/list";
	}
	
}
