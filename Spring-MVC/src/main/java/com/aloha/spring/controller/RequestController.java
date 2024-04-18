package com.aloha.spring.controller;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aloha.spring.dto.Board;

@Controller			// Controller 로 지정하고 빈 등록
@RequestMapping("/request")
public class RequestController {

	private static final Logger logger = LoggerFactory.getLogger(RequestController.class);
	
	// 업로드 경로
	@Resource(name = "uploadPath")
	private String uploadPath;
	
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
	
	
	/*--------------------------[요청 경로 매핑]----------------------- */
	/*-------------------------------------------------------------- */
	/*--------------------------[요청 처리]----------------------------- */
	
	@ResponseBody
	@RequestMapping(value="/header", method = RequestMethod.GET)
	public String header(@RequestHeader("Accept") String accept
						,@RequestHeader("User-Agent") String userAgent
						,HttpServletRequest request) {
		// @RequestHeader : 헤더명을 지정하여 헤더 값을 가져오는 어노테이션
		// @RequestHeader ("헤더명") 타입 매개 변수
		logger.info("[GET] - /request/header");
		logger.info("@RequestHeader 를 통한 헤더 정보 가져오기");
		logger.info("Accept - " + accept);
		logger.info("User-Agent - " + userAgent);
		
		String requestAccept = request.getHeader("Accept");
		String requestUserAgent = request.getHeader("User-Agent");
		logger.info("HttpServletRequest 를 통한 헤더 정보 가져오기");
		
		
		return "SUCCESS";
	}

	/**
	 * 요청 본문 가져오기
	 * @param board
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/body", method = RequestMethod.POST)
	public String requestBody(@RequestBody Board board) {
//		public String requestBody(Board board) {
		// @RequestBody
		// : HTTP 요청 메시지의 본문(body)의 내용을 객체로 변환하는 어노테이션
		// 클라이언트에서 contentType: JSON 형식에 데이터를 보낸 경우
		// 객체로 변환하기 위해 사용한다.
		// *생략가능 (주로 생략하고 쓴다.)
		logger.info("[POST] - /request/body");
		logger.info(board.toString());
		
		return "SUCCESS";
	}
	
	@ResponseBody
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public String requestCheck(@RequestParam("hobby") String[] hobbies) {
		// 체크박스 다중 데이터는 배열로 전달 받을 수 있다.
		logger.info("[POST] - /request/check");
		
		for (String hobby : hobbies) {
			logger.info("hobby : " + hobby);
		}
		
		return "SUCCESS";
	}

	
	
	
	@ResponseBody
	@RequestMapping(value = "/user", method = RequestMethod.POST)
//	public String requestUser(String birth) {
		public String requestUser(@DateTimeFormat(pattern="yyyy-MM-dd") Date birth) {
		// 체크박스 다중 데이터는 배열로 전달 받을 수 있다.
		logger.info("[POST] - /request/user");
		logger.info("birth : " + birth);
		
		return "SUCCESS";
	}
	
	
	
	/**
	 * Map 컬렉션으로 요청 파라미터 가져오기
	 * 요청경로 : /request/map?name=김조은&age=20
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/map")
	public String requestMap(@RequestParam Map<String, String> map) {
		String name = map.get("name");
		String age = map.get("age");
		
		logger.info("name : " + name);
		logger.info("age : " + age);
		return "SUCCESS";
	}
	
	// 파일 업로드
	@ResponseBody
	@RequestMapping(value = "/file", method = RequestMethod.POST)
	public String fileUpload(MultipartFile file) throws Exception {
		logger.info("/request/file");
		logger.info("uploadPaht : " + uploadPath);
		
		if( file == null ) return "FAIL";
		
		logger.info("originalFileName : " + file.getOriginalFilename());
		logger.info("size : " + file.getSize());
		logger.info("contentType : " + file.getContentType());
		
		// 파일 데이터
		byte[] fileData = file.getBytes();
		
		// 파일 업로드
		String filePath = uploadPath;
		String fileName = file.getOriginalFilename();
		File uploadFile = new File(filePath, fileName);
		
		FileCopyUtils.copy(fileData, uploadFile); // 파일 업로드(저장)
		// - FileCopyUtils.copy(파일 데이터, 파일 객체);
		// : 내부적으로 InputStream, OutputStream을 이용하여 입력받은 파일을 출력한다.
		
		return "SUCCESS";
	}
	
	
	// 다중 파일 업로드
		@ResponseBody
		@RequestMapping(value = "/file/multi", method = RequestMethod.POST)
		public String fileUpload(@RequestParam("file") MultipartFile[] fileList) throws Exception {
			logger.info("/request/file/multi");
			logger.info("uploadPaht : " + uploadPath);
			
			if( fileList == null ) return "FAIL";
			
			if( fileList.length > 0 ) {
				for (MultipartFile file : fileList) {
					logger.info("originalFileName : " + file.getOriginalFilename());
					logger.info("size : " + file.getSize());
					logger.info("contentType : " + file.getContentType());
					
					// 파일 데이터
					byte[] fileData = file.getBytes();
					
					// 파일 업로드
					String filePath = uploadPath;
					String fileName = file.getOriginalFilename();
					File uploadFile = new File(filePath, fileName);
					
					FileCopyUtils.copy(fileData, uploadFile); // 파일 업로드(저장)
				}
			}
			
			
			return "SUCCESS";
		}
		
		// 게시판 파일 업로드
		@ResponseBody
		@RequestMapping(value = "/file/board", method = RequestMethod.POST)
		public String fileUpload(Board board) throws Exception {
			logger.info("/request/file/multi");
			logger.info("uploadPaht : " + uploadPath);
			logger.info("board : " + board);
			
//			MultipartFile[] fileList = board.getFileList();
			List<MultipartFile> fileList = board.getFileList();
			if( fileList == null ) return "FAIL";
			
//			if( fileList.length > 0 ) {
			if( !fileList.isEmpty() ) {
				for (MultipartFile file : fileList) {
					logger.info("originalFileName : " + file.getOriginalFilename());
					logger.info("size : " + file.getSize());
					logger.info("contentType : " + file.getContentType());
					
					// 파일 데이터
					byte[] fileData = file.getBytes();
					
					// 파일 업로드
					String filePath = uploadPath;
					String fileName = file.getOriginalFilename();
					File uploadFile = new File(filePath, fileName);
					
					FileCopyUtils.copy(fileData, uploadFile); // 파일 업로드(저장)
				}
			}
			
			
			return "SUCCESS";
		}
		
		
		/**
		 * request/ajax.jsp
		 * @return
		 */
		
		@RequestMapping("/ajax")
		public String ajax() {
			
			return "request/ajax";
		}
		
		/**
		 * AJAX 비동기 파일 업로드
		 * @param board
		 * @return
		 * @throws IOException
		 */
		@ResponseBody
		@RequestMapping(value = "/ajax", method = RequestMethod.POST)
		public String ajaxUploadPost(Board board) throws IOException {
			logger.info("/request/ajax");
			logger.info("uploadPath : " + uploadPath);
			logger.info(board.toString());
			
			List<MultipartFile> fileList = board.getFileList();
			
			if( fileList == null ) return "FAIL";
			
			if( !fileList.isEmpty() ) 
				for (MultipartFile file : fileList) {
					logger.info("originalFileName : " + file.getOriginalFilename());
					logger.info("size : " + file.getSize());
					logger.info("contentType : " + file.getContentType());
					
					byte[] fileData = file.getBytes();
					
					String filePath = uploadPath;
					String fileName = file.getOriginalFilename();
					File uploadFile = new File(filePath, fileName);
					FileCopyUtils.copy(fileData, uploadFile);			// 파일 업로드
				}
			return "SUCCESS";
		}
		
}













