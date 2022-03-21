package com.devsuperior.dscatalog.services.exceptions;

public class EntityNotFindException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public EntityNotFindException(String msg) {
		super(msg);
	}
}
