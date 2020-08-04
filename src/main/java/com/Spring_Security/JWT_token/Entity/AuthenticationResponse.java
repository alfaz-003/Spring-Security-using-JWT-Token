package com.Spring_Security.JWT_token.Entity;

public class AuthenticationResponse {
	
	private final String jwt;
	

	public AuthenticationResponse(String jwt) {
		super();
		this.jwt = jwt;
	}


	public String getJwt() {
		return jwt;
	}
	
	

}
