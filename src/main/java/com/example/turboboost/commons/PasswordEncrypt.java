package com.example.turboboost.commons;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncrypt implements PasswordEncoder{

	@Override
	public String encode(CharSequence rawPassword) {
		return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt(8));
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
	}
	


}
