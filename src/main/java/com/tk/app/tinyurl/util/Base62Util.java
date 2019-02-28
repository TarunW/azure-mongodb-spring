package com.tk.app.tinyurl.util;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.stereotype.Component;
@Component
public class Base62Util {
	
	private static final long LOWER_LIMIT = 1L;

	private static final long UPPER_LIMIT = 62*62*62*62*62*62;

	private String characters;
	
	private RandomDataGenerator random = new  RandomDataGenerator();

	/**
	 * Constructs a Base62 object with the default charset (0..9a..zA..Z).
	 */
	public Base62Util() {
		this("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
	}

	/**
	 * Constructs a Base62 object with a custom charset.
	 * 
	 * @param characters
	 *            the charset to use. Must be 62 characters.
	 * @throws <code>IllegalArgumentException<code> if the supplied charset is not 62 characters long.
	 */
	private Base62Util(String characters) {
		if (!(characters.length() == 62)) {
			throw new IllegalArgumentException("Invalid string length, must be 62.");
		}
		this.characters = characters;
	}
	
	/**
	 * Decodes a Base62 <code>String</code> returning a <code>long</code>.
	 * 
	 * @param b62
	 *            the Base62 <code>String</code> to decode.
	 * @return the decoded number as a <code>long</code>.
	 * @throws IllegalArgumentException
	 *             if the given <code>String</code> contains characters not
	 *             specified in the constructor.
	 */
	public long decodeBase62(String b62) {
		for (char character : b62.toCharArray()) {
			if (!characters.contains(String.valueOf(character))) {
				throw new IllegalArgumentException("Invalid character(s) in string: " + character);
			}
		}
		long ret = 0;
		b62 = new StringBuilder(b62).reverse().toString();
		long count = 1;
		for (char character : b62.toCharArray()) {
			ret += characters.indexOf(character) * count;
			count *= 62;
		}
		return ret;
	}
	
	/**
	 * Encodes a decimal value to a Base62 <code>String</code>.
	 * 
	 * @param b10
	 *            the decimal value to encode, must be nonnegative.
	 * @return the number encoded as a Base62 <code>String</code>.
	 */
	public String encodeBase10(long b10) {
		if (b10 < 0) {
			throw new IllegalArgumentException("b10 must be nonnegative");
		}
		String ret = "";
		while (b10 > 0) {
			ret = characters.charAt((int) (b10 % 62)) + ret;
			b10 /= 62;
		}
		return ret;

	}

	public String generateShortKey() {
		String shortKey = encodeBase10(random.nextLong(LOWER_LIMIT, UPPER_LIMIT));
		System.out.println("Generated Key -- " + shortKey);
		return shortKey;
	}	
	
}