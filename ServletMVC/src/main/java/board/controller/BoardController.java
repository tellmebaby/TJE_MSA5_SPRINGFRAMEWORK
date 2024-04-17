package board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import board.dto.Board;
import board.service.BoardService;
import board.service.BoardServiceImpl;
import controller.ModelView;

// /board 경로로 요청이 오면, 처리하는 컨트롤러
public class BoardController {

	private BoardService boardService = new BoardServiceImpl();
	
	public ModelView process(HttpServletRequest request) throws Exception {
		String view = "";
		String requestURI = request.getRequestURI();
		ModelView modelView = new ModelView();
		
		// 게시글 목록 - /board/list.do
		if(requestURI.contains("list.do") ) {
			list(modelView);
		}
		
		// 게시글 조회 - /board/read.do
		if(requestURI.contains("read.do") ) {
			list(modelView, request);
		}
		
		return modelView;
	}
	private void list(ModelView modelView) throws Exception {
		List<Board> boardList = boardService.list();
		// 모델
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("boardList", boardList);
		// 뷰
		String view = "/board/list.jsp";
		modelView.setModel(model);
		modelView.setView(view);
		
		
	}

	private void read(ModelView modelView
						,HttpServletRequest request) throws Exception {
		String reqNo = request.getParameter("no");
		int no = reqNo == null ? 0 : Integer.parseInt(reqNo);
		Board board = boardService.select(no);
		// 모델
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("board", board);
		// 뷰
		String view = "/board/read.jsp";
		modelView.setModel(model);
		modelView.setView(view);
		
		
	}
}
