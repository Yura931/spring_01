package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/reqmap/*")
public class RequestMappingController {
	
	@RequestMapping("")
	public void method1() {
		log.info("method1");
	}
	
	@RequestMapping("/ex2") // 다른 경로에 요청을 보내고 싶을 때
	public void method2() {
		log.info("method2");
	}
	
	@RequestMapping("/ex3")
	public void method3() {
		log.info("method3");
	}
	
	@RequestMapping(value = "/ex4", method = RequestMethod.POST) // 이 경로로 오는 요청중 GET방식으로 오는 요청만 처리하겠다는 의미, post방식으로 이 경로 요청시 ex4가 아닌 가장 길게 매핑되어 있는 "/" 이 경로를 가진 method1이 일 하게 됨
	public void method4() {
		log.info("method4");
	}
	
	@RequestMapping(value = "/ex5", method = RequestMethod.POST)
	public void method5() {
		log.info("method5");
	}
	
	@GetMapping("/ex6") // @RequestMapping(method={GET}) -> 이 어노테이션이 포함되어 있음 
	public void method6() {
		log.info("method6");
	}
	
	@PostMapping("/ex7") // @RequestMapping(method={POST}) -> 이 어노테이션이 포함되어 있음
	public void method7() {
		log.info("method7");
	}
	
	// 일치하는 메소드가 일을 하도록 함
	// servlet이라는 추상클래스를 상속받지 않아도 되고, 그냥 클래스! Plan of Java Object 물론 어노테이션이 잔뜩 첨가되어있긴 하지만 ..
	// 어떤 요청에 따른 핸들러를 만들거나 서블릿을 만들어야 했지만 , 간단히 메소드 레벨만 만들어도 되기 때문에 코드가 훨씬 간결해짐
	// 다른 요청을 갖고싶다! 메소드만 만들어주면 됨, 편리하게 작업 가능
	// get, post방식으로 오는 요청 위 메소드가 처리하게 됨, 방식에 따라 다른 일을 하게 하고 싶다면 메소드에 명시해주면 됨
}
