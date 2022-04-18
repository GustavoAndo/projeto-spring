package com.projeto.escola.seguranca;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Senha {

	public static void main(String[] args) {
	    String encodedPassword = new BCryptPasswordEncoder().encode("12345678");
	    System.out.println(encodedPassword);
	}

}
