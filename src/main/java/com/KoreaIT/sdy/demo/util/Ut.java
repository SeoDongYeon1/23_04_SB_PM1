package com.KoreaIT.sdy.demo.util;

public class Ut {
	
	public static String f(String format, Object... args) {
		return String.format(format, args);
	}

	public static boolean empty(Object obj) {
		if(obj==null) {
			return true;
		}
		if(obj instanceof String == false) { // object가 String이니?, 타입 검사
			String str = (String) obj;
			return str.trim().length()==0;
		}
		
		return false;
	}
}
