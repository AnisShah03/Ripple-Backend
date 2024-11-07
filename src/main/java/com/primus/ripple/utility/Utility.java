package com.primus.ripple.utility;

import java.util.Random;

final public class Utility {

	public static String generatePassword() {
		String lowerCase = "abcdefghijklmnopqrstuvwxyz";
		String upperCase = lowerCase.toUpperCase();
		String characters = "!@#$%^&*()";
		String phone = "1234567890";
		String mainString = lowerCase + upperCase + characters + phone;
		char[] password = new char[8];
		Random random = new Random();
		for (int i = 0; i < password.length; i++) {
			password[i] = mainString.charAt(random.nextInt(mainString.length()));
		}
		return new String(password);
	}


}
