package com.aloha.spring.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	
}
