package com.Spring_Security.JWT_token.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Spring_Security.JWT_token.Entity.AuthenticationRequest;
import com.Spring_Security.JWT_token.Entity.AuthenticationResponse;
import com.Spring_Security.JWT_token.Service.MyUserDetailsService;
import com.Spring_Security.JWT_token.Util.JwtUtil;

@RestController
public class HelloResource {
	

	
	@Autowired
	private MyUserDetailsService UserDetailsService;
	
	@Autowired
	 private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtTokenutil;
	

	
	@GetMapping("/authenticate/hello")
	public String hello() {
		return "Assalamualaikum wr wb !!" ;
		}
	
	@RequestMapping(value = "/authenticate" , method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody  AuthenticationRequest authenticationRequest) throws Exception {
		try
		{
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword(), null)
				);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect Username or Password",e);
			// TODO: handle exception
		}
		
		final UserDetails userDetails = UserDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		
		final String jwt = jwtTokenutil.generateToken(userDetails);
				
		
			return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }
}