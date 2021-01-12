package org.zerock.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.domain.Book;
import org.zerock.domain.CustomBookEditor;
import org.zerock.domain.Member;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/paramex/*")
@Log4j
public class ParameterController {
	
	@RequestMapping("/ex1")
	public void method(HttpServletRequest request) {
		log.info(request);
		log.info(request.getParameter("name"));
		log.info("method1");
	}
	
	@RequestMapping("/ex2")
	public void method2(@RequestParam("name") String n) { // name이라는 parameter가 n에 잘 들어갔는지 확인
		log.info("method2");
		log.info(n);
	}
	
	@RequestMapping("/ex3")
	public void method3(@RequestParam String name) { // parameter의 이름과 변수의 이름이 같으면 생략 가능
		log.info("method3");
		log.info(name);
	}
	
	@RequestMapping("/ex4")
	public void method4(String name) { //@RequestParam까지 생략하면 ? 생략해도 같은이름의 parameter가 존재하면 값을 얻어올 수 있음
		log.info("method4");
		log.info(name);
	}
	
	@RequestMapping("/ex5")
	public void method5(HttpServletRequest request) { // 하나의 체크박스 이름으로 여러 value가 넘어가게 끔 하는 경우, request에 배열로 들어가게 됨
		log.info("method5");
		log.info(request.getParameterValues("check")); // 여러 값을 꺼낼 때 사용, String[]로 리턴하게 됨, [Ljava.lang.String;@5d733d8d -> Object toString메소드가 출력 됨
		
		String[] list = request.getParameterValues("check");
		for (String s : list) {
			log.info(s);
		}
	}
	
	// 여러개 값을 받으려면
	@RequestMapping("/ex6")
	public void method6(String[] check) { // String[] 타입으로 변수명을 파라미터명으로 넣어주면 됨 
		log.info("method6");

		for (String s : check) {
			log.info(s);
		}
	}
	
	@RequestMapping("/ex7")
	public void method7(@RequestParam("check")ArrayList<String> check) {
		log.info("method7");
		for (String c : check) {
			log.info(c);
		}
	}
	
	// 어떤 객체의 프로퍼티로 담아쓰는경우
	@RequestMapping("/ex8")
	public void method8(HttpServletRequest request) { // 여러 파라미터를 받아오는 경우  / 파라미터가 리퀘스트 객체만 가지고 있음, 옮겨담는 일을 하지 않는 객체이기때문에 initbinder가 일을 하지 않음
		String name = request.getParameter("name");
		String ageStr = request.getParameter("age");
		int age = Integer.parseInt(ageStr);
		
		Member member = new Member();
		member.setName(name);
		member.setAge(age);
		
		log.info("method8");
		log.info(member); 
		
	}
	
	@RequestMapping("/ex9")
	public void method9(Member member) { // 객체의 프로퍼티에 맞는 파라미터를 꺼내서 완성해주면 됨 , 멤버가 가지고 있는 기본 프로퍼티, 8개의 데이터 타입이나 String타입인 경우 자동으로 프로퍼티로 만들어 줌, request param에 담기전에 어떠한 일을 해야함 -> 이 일을 하는 애가 initBinder
		log.info("method9");
		log.info(member);
		// 꺼내는 일을 직접하지 않아도 스프링이 알아서 만들어 줌, 굉장히 간편해 짐!
	}
	
	@InitBinder // 리퀘스트 매핑이 붙어있는 것 중에, 파라미터가 옮겨가기 전에 일을 하게 됨, 어떤 일을 하는 WebDataBinder라는 파라미터를 받음
	public void initBinder1(WebDataBinder binder) { // 적절한 바인더를 붙여주게 됨
		log.info("initbinder1"); 
		
		// requiredType은 propertyEditor를 사용
		// binder.registerCustomEditor(requiredType, propertyEditor); // 어떤 타입일 땐 어떤 에디터가 사용 되도록
		binder.registerCustomEditor(Book.class, new CustomBookEditor()); 
	}
	
	@RequestMapping("/ex10")
	public void method10(@RequestParam("book") Book book) { // book=java-spring -> 이런식으로 값을 넣어줄 때 registerCustomEditor를 이용해 프로퍼티를 커스텀해줄 수 있음, 어떤형식으로 값을 넣어줄 것인지?
		log.info("method10");
		log.info(book);
		// book이라는 파라미터가 title-writer라는 형태로 들어오면 custombookeditor를 통해 바뀌게 됨 -> title=java, writer=spring 이런식의 결과가 나옴
	}
}








