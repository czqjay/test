package com.sunit.global.util.exception;

public class ExceptionManager {

	public static void throwEx(String msg,boolean isThrow) throws SLHException{
		if(isThrow){
			throw new SLHException(msg); 
		}
	}
	
	public static void throwRuntimeEx(String msg,boolean isThrow) {
		if(isThrow){
			throw new RuntimeException(msg); 
		}
	}
	
}
