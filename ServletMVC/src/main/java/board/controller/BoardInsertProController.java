package board.controller;


import javax.servlet.http.HttpServletRequest;

import board.dto.Board;
import board.service.BoardService;
import board.service.BoardServiceImpl;


public class BoardInsertProController {

	private BoardService boardService = new BoardServiceImpl();
	
	public String process(HttpServletRequest request) throws Exception {
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		Board board = new Board(title,writer, content);
		
		int result = boardService.insert(board);
		request.setAttribute("result", result);
		String view = "/board/list.do";
		return view;
	}
}
