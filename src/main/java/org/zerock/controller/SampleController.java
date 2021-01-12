package org.zerock.controller;

import java.text.SimpleDateFormat;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;


@Controller // 스프링이 관리하는 빈이 됨, 왜 되느냐 servlet-context.xml  controller역할을 하는 빈 Controller어노테이션 사용, 이 어노테이션 안에 Component어노테이션이 포함되어 있음
@RequestMapping("/sample/*") // 이 경로로 오는 요청을 컨트롤러가 받도록, 정확히 어떤 요청이냐 /sample/로 오는 요청을 SampleController가 받도록
@Log4j
public class SampleController {
	
	@RequestMapping("") // /sample/다음 *에 해당하는 요청이 10행의 Mapping안에 들어있는 값과 같으면 이 메소드가 일을 하게 됨
	public void basic() {
		log.info("basic......");
	}
	
	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
		
		log.info("sample ex02");
		log.info("name: " + name);
		log.info("age:" + age);
		
		return "ex02";
	}
	
	// 133page
	@GetMapping("/ex031")
	public String ex02Array(@RequestParam("ids") String[] ids) {
		log.info("array ids : " + Arrays.deepToString(ids));
		
		return "ex02Array";
	}
	
	// 132page
	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("ids") ArrayList<String> ids) { // ArrayList는 simple type이 아니기때문에 @RequestParam 생략 불가능
		log.info("ids: " + ids);
		
		return "ex02List";
	}
	
	// 130page
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info("" + dto);
		
		return "ex01";
	}
	
	// 134page
	// http://localhost:8080/sample/ex02Bean?list[0].name=aaa&list[1].name=bbb -> 밑에코드로 인코딩 된 것
	// http://localhost:8080/sample/ex02Bean?list%5B0%5D.name=aaa&list%5B1%5D.name=bbb
	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		log.info("list dtos: " + list);
		return "ex02Bean";
	}
	
	/*
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false)); // spring에서 제공해주는 editor임, 생성자에 포맷, 
	} */
	
	
	// ex03?title=test&dueDate=2018-01-01
	// ex03?title=test&dueDate=2018/01/01
	@GetMapping("/ex03")
	public String ex03(TodoDTO todo) {
		log.info("todo: " + todo);
		return "ex03";				
	}
}
