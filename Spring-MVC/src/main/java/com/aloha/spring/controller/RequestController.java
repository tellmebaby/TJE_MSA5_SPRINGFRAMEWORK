package com.aloha.spring.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller			// Controller 로 지정하고 빈 등록
@RequestMapping("/request")
public class RequestController {

	private static final Logger logger = LoggerFactory.getLogger(RequestController.class);
	
	
	/**
	 * @RequestMapping : 요청 경로 매핑
	 *  - / request/board 로 요청
	 *  - / request/board.jsp 응답
	 * @return
	 */
//	@RequestMapping(value = "/request/board", method = RequestMethod.GET)
//	@RequestMapping("/request/board")
	@RequestMapping("/board")
	public String request() {
		logger.info("[GET] - /request/board");
		return "request/board";
	}
	
	
	/**
	 * 경로 패턴 매핑
	 * @param no
	 * @return
	 */
	@RequestMapping(value = "/board/{no}", method = RequestMethod.GET)
	public String requestPath(@PathVariable ("no") int no) {
		logger.info("[GET] - /request/board/{no}");
		logger.info("no : " + no);
		
		return "request/board";
	}
	
	/**
	 * 요청 메소드 매핑
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/board", method = RequestMethod.POST)
//	public String requestPost(@RequestParam("no") int data) {
	public String requestPost(@RequestParam int no) {
		logger.info("[POST] - /request/post");
		logger.info("no : " + no);
		return "SUCCESS - no : " + no;
	}
	
	/**
	 * 파라미터 매핑
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/board", method = RequestMethod.GET, 
					params = "id")
	public String requestParams(@RequestParam String id) {
		logger.info("[GET] - /request/board?id=board_123");
		logger.info("id : " + id);
		
		return "request/board";
		
	}
	
	/**
	 * 헤더 매핑
	 * @return
	 */
	// headers = "헤더명=값"
	@ResponseBody
	@RequestMapping(value = "/board", method = RequestMethod.POST
					,headers = "Content-Type=application/json")
	public String requestHeader() {
		logger.info("[POST] - /request/board");
		logger.info("헤더 매핑...");
		return "SUCCESS";
	}
	
	
	
	/*
	 * @ResponseBody O : return "데이터"; ---> 응답메시지(본문: 데이터)
	 * @ResponseBody X : return "화면이름"; ---> 뷰 리졸버가 jsp 선택->렌더링->html 응답
	 */
	@RequestMapping(value = "/board", method = RequestMethod.PUT)
	public String requestPut() {
		logger.info("[POST] (PUT) - /request/board");
		logger.info("헤더 매핑...");
		return "redirect:/";
	}
	
	
	/**
	 * 컨텐츠 타입 매핑
	 * - Content-Type 헤더의 값으로 매핑
	 * - consumes = "컨텐츠타입값"
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/board", method = RequestMethod.POST
					,consumes = "application/xml")
	public String requestContentType() {
		logger.info("[POST] - /board/request");
		logger.info("컨텐츠 타입 매핑...");
		return "SUCCESS";
	}
	

	/**
	 * Accept 매핑
	 * - Accept 헤더의 값으로 매핑 (Accept 헤더는 응답 답을 컨텐츠 타입을 지정)
	 * - consumes = "컨텐츠타입값"
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/board", method = RequestMethod.POST
					,produces = "application/json")
	public String requestAccept() {
		logger.info("[POST] - /board/request");
		logger.info("Accept 매핑...");
		return "SUCCESS";
	}
	
	
	
}













