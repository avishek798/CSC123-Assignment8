package com.usman.csudh.bank.core;

public class ConfigFileErrorException extends Exception {

	private static final long serialVersionUID = 1L;

	public ConfigFileErrorException() {
		super();
	}

	public ConfigFileErrorException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ConfigFileErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConfigFileErrorException(String message) {
		super(message);
	}

	public ConfigFileErrorException(Throwable cause) {
		super(cause);
	}
	
	
	
	

}
