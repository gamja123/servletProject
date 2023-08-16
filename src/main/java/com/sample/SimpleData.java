package com.sample;
/*
 * jsp 페이지 안에서 자바 빈즈를 사용할 수 있도록 하는 액션태그와 페이지를 활용할 수 있도록 하는 태그가 존재했다
 * 
 * <jsp:setProperty> 
 * <jsp:getProperty>
 * 
 *자바 빈즈
 *-자바 클래스 중에서 자바 빈즈 규약에 맞게 작성된 클래스를 의미한다
 *멤버변수와 setter / getter 메소드로 이루어진 클래스를 의미한다
 * 값을 저장하는 ValueObject 로 활용된다
 * */

public class SimpleData {
	private String message;
	
	public SimpleData() {
		message = "열공합시다";
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
