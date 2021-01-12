package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.domain.Member;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/model/*")
@Log4j
public class ModelController {
	
	@RequestMapping("/ex1") // 이 메소드에 모델을 전달하려면, 모델을 받는다고 명시해 주어야 함
	public void method1(Model model) { // DispatcherServlet이 여기에 모델을 넣어줌
		log.info("method1");
		
//		request.setAttribute("abc", abc);
		model.addAttribute("name","java"); // addAttribute를 사용해 jsp에서 EL문을 사용해 꺼내쓸 수 있음
										   // Model에 붙은 attribute를 스프링이 request로 잘 옮겨줌
	}
	
	@RequestMapping("/ex2")
	public void method2(@ModelAttribute("name") String name) { // 어노테이션("name")안의 이름으로 파라미터로 받은 name이 붙여넣어짐
		log.info("method2");
		
//		model.addAttribute("name",name); -> 이 일을 하지 않아도 됨
	}
	
	@RequestMapping("/ex3")
	public void method3(@ModelAttribute("name") String name,
							@ModelAttribute("age") int age) { // model에 넣어주어야 jsp에서 꺼내 쓸 수 있음
		log.info("method3");
		log.info(name);
		log.info(age);
	}
	
	@RequestMapping("/ex4")
	public void method4(@ModelAttribute("member") Member member) { // 프로퍼티에 잘 세팅, lombok의 toString이 잘 보관해줌, model에 member라는 이름을 붙여주게 됨, jsp에서 member로 꺼내사용할 수 있음
		log.info("method4");
		log.info(member);
		
//		model.addAttribute("member", member); // ModelAttribute어노테이션을 사용하면 이 코드 생략 가능
	}
	
	@RequestMapping("/ex5")
	public void method5(@ModelAttribute Member member) { // model attribute명은 타입명을 따라가게 됨, 타입명의 앞글자를 소문자로 변경한 것과 같음 , 41행 코드를 49행코드처럼 생략해서 사용가능
		log.info("method5");
		log.info(member);
		

	}
	
	@RequestMapping("/ex6")
	public String method6(Member member) { // 기본타입8개와 String타입을 제외하면 다 ModelAttribute로 결정됨, 생략해도 자동으로 ModelAttribute가 됨
		log.info("method4");
		log.info(member);
		
		return "model/ex4";	
	}
}
