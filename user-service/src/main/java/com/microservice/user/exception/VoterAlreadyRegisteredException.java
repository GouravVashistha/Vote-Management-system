package com.voterservice.voter.exceptions;

public class VoterAlreadyRegisteredException extends Exception{

	private static final long serialVersionUID = 1L;

	public VoterAlreadyRegisteredException(String msg) {
		super(msg);
	}
}
