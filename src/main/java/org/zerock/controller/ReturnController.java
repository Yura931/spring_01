package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.domain.Member;

import lombok.extern.log4j.Log4j;

@Controller // spring bean 으로 만들어서 적절한 mvc일을 하게 함
@RequestMapping("/return/*")
@Log4j
public class ReturnController {
	
	@RequestMapping("/ex1")
	public String method1() { // return되는 String은 view(jsp)의 이름
		log.info("method1");
		return "returnView1"; // view만 리턴하는 것은 포워딩 하는 것
	}
	
	@RequestMapping("/ex2")
	public String method2() { // return되는 String은 view(jsp)의 이름
		log.info("method2");
		
		return "redirect:/sample/"; // 리다이렉트 시 컨텍스트루트를 붙이지 않아도 됨
	}
	
	@RequestMapping("/ex3")
	public @ResponseBody String method3() { 
		log.info("method3");
		
		return "returnValue3"; // -> jsp파일이 아닌 text값이 응답 자체가 되는 것 
	}
	
	@RequestMapping("/ex4")
	public void method4() { // 리턴타입이 없는 경우, 요청 경로가 곧 jsp파일이 됨 -> /WEB-INF/views/return/ex4.jsp를 찾기위해 노력
		log.info("method4");
	}
	
	@RequestMapping("/ex5")
	public @ResponseBody Member method5() {
		log.info("method5");
		
		Member member = new Member(); //필드 == 프로퍼티 객체가 가지고있는 정보 name ,age 이 객체정보를 클라이언트에게 보내주려면 , 텍스트로 바꾸어야 함
		member.setName("yura");
		member.setAge(28);
		
//		"yura:28" // 이렇게 표현한다면, 콜론 앞은 이름 뒤는 나이이다 약속하면 가능
//      "name=yura&age=28"
//		{"name":"yura", "age":33} // json을 이용, 프로퍼티는 콤마로구분하고 키와 값은 콜론으로 구분 하나의 객체는 {} 중괄호 안에 / 자바스크립트와 다른 점 한가지는 프로퍼티명도 "" 큰따옴표 안에 작성해주어야 함, 넘버타입은 따옴표 붙이지 않음 
	return member;  // 멤법 객체가 응답 본문이 되어야 함	
	
	// AJAX로 값을 주고받을 때 사용하게 됨, 자바스크립트 객체표현방식이므로 자바스크립트 객체로 변환하기도 쉬움
	// java object -> json
	// json -> javascript -> html에서 사용하기 쉽다는 의미
	
	// javascript -> json  자바크립트가, 자바 오브젝트로 변경되는 중간 다리역할을 함
	// json -> java object
	}
}
