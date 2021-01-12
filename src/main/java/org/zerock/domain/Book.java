package org.zerock.domain;

import lombok.Data;

@Data // lombok으로 get,set메소드 생성
public class Book {
	private String title;
	private String writer;
}
