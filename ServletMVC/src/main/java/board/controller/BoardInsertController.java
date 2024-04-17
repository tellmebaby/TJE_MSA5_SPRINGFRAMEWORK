package board.controller;

import javax.servlet.http.HttpServletRequest;

public class BoardInsertController {

	public String process(HttpServletRequest request) throws Exception {
		String view = "/board/insert.jsp";
		return view;
	}
}
