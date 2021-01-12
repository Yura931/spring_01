package org.zerock.domain;

import java.awt.Component;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;

public class CustomBookEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		//text: java-spring -> 이런식으로 들어온다고 가정
			//  title-writer -> text가 분리되어야 함
		
		int dash = text.indexOf("-");
		String title = text.substring(0, dash);
		String writer = text.substring(dash+1);
		
		Book book = new Book();
		book.setTitle(title);
		book.setWriter(writer); 
		
		setValue(book);
	}
	
	// book에 텍스트가 올경우 북이라는 객체로 변환시켜주어야 함, web에서는 텍스트로 넘어옴
	
}
