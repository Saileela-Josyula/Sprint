package com.cg.datajpa.mts.exception;

public class OutletNotFoundException extends Exception {
	
	String exceptionError;
	public OutletNotFoundException(String str){
		this.exceptionError=str;
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}
	
}
