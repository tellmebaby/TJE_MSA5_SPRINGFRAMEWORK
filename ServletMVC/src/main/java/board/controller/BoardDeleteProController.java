package board.controller;

import javax.servlet.http.HttpServletRequest;

import board.dto.Board;
import board.service.BoardService;
import board.service.BoardServiceImpl;

public class BoardDeleteProController {

private BoardService boardService = new BoardServiceImpl();
	
	public String process(HttpServletRequest request) throws Exception {
		String reqNo = request.getParameter("no");
		int no = reqNo == null ? 0 : Integer.parseInt(reqNo);
		int result = boardService.delete(no);
		request.setAttribute("result", result);
		String view = "/board/list.do";
		return view;
	}
}
