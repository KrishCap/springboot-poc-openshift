package com.example.poc.exception;

public class SpringbootPOCException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String refId;
	
	public SpringbootPOCException(){
		super();
	}
	
	public SpringbootPOCException(String msg){
		super(msg);
	}

	public SpringbootPOCException(String refId, Throwable e){
		super(e);
		this.refId=refId;
	}
	public SpringbootPOCException(String msg, String refId, Throwable e){
		super(msg,e);
		this.refId=refId;
	}
	
	public String getRefId() {
		return refId;
	}
}



