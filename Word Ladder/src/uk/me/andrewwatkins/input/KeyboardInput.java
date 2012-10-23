package uk.me.andrewwatkins.input;

import java.util.Scanner;

/**
 * This class handles all Input from the console
 * @author Andrew Watkins
 */
public class KeyboardInput {

	private static KeyboardInput inst = new KeyboardInput();
	private Scanner input;
	
	private KeyboardInput() {
		input = new Scanner(System.in);
	}
	
	/**
	 * Gets the instance of KeyboardInput
	 * @return KeyboardInput instance
	 */
	public static KeyboardInput getInstance() {
		return inst;
	}
	
	/**
	 * Prints an error in a nice format
	 * @param err The error message to print
	 */
	private void error(String err) {
		System.err.print("Error: ");
		System.err.println(err);
	}
	
	/**
	 * Tries to read an integer value typed into the console
	 * @return The next line parsed as an integer if possible, or Integer.MIN_VALUE if not
	 */
	public int readInt() {
		int retVal;
		try {
			retVal = input.nextInt();
		} catch (Exception e) {
			error("Expected an integer, received otherwise");
			retVal = Integer.MIN_VALUE;
		}
		return retVal;
	}
	
	/**
	 * Tries to read a byte value typed into the console
	 * @return The next line parsed as a byte if possible, or Byte.MIN_VALUE if not
	 */
	public byte readByte() {
		byte retVal;
		try {
			retVal = input.nextByte();
		} catch (Exception e) {
			error("Expected a byte, received otherwise");
			retVal = Byte.MIN_VALUE;
		}
		return retVal;
	}
	
	/**
	 * Tries to read a short value typed into the console
	 * @return The next line parsed as a short if possible, or Short.MIN_VALUE if not
	 */
	public short readShort() {
		short retVal;
		try {
			retVal = input.nextShort();
		} catch (Exception e) {
			error("Expected a short, received otherwise");
			retVal = Short.MIN_VALUE;
		}
		return retVal;
	}
	
	/**
	 * Tries to read a long value typed into the console
	 * @return The next line parsed as a long if possible, or Long.MIN_VALUE if not
	 */
	public long readLong() {
		long retVal;
		try {
			retVal = input.nextLong();
		} catch (Exception e) {
			error("Expected a long, received otherwise");
			retVal = Long.MIN_VALUE;
		}
		return retVal;
	}
	
	/**
	 * Tries to read a float value typed into the console
	 * @return The next line parsed as a float if possible, or Float.MIN_VALUE if not
	 */
	public float readFloat() {
		float retVal;
		try {
			retVal = input.nextFloat();
		} catch (Exception e) {
			error("Expected a float, received otherwise");
			retVal = Float.MIN_VALUE;
		}
		return retVal;
	}
	
	/**
	 * Tries to read a double value typed into the console
	 * @return The next line parsed as a double if possible, or Double.MIN_VALUE if not
	 */
	public double readDouble() {
		double retVal;
		try {
			retVal = input.nextDouble();
		} catch (Exception e) {
			error("Expected a double, received otherwise");
			retVal = Double.MIN_VALUE;
		}
		return retVal;
	}
	
	/**
	 * Tries to read a boolean value typed into the console
	 * @return True if t, or y is the first letter of the entered word, false otherwise
	 */
	public boolean readBool() {
		boolean retVal;
		char inpChar = readLowerCaseChar();
		switch (inpChar) {
			case 't': case 'y': retVal = true; break;
			case 'f': case 'n': retVal = false; break;
			default: error("Expected a boolean, received otherwise"); retVal = false;
		}
		return retVal;
	}

	/**
	 * Tries to read a char value typed into the console
	 * @return The first letter of the next line if possible, or Character.MIN_VALUE if not
	 */
	public char readChar() {
		char retVal;
		try {
			retVal = input.next().charAt(0);
		} catch (Exception e) {
			error("Expected a char, received otherwise");
			retVal = Character.MIN_VALUE;
		}
		return retVal;
	}
	
	/**
	 * Tries to read a string value typed into the console
	 * @return The next line
	 */
	public String readString() {
		String retVal;
		retVal = input.next();
		return retVal;
	}
	
	/**
	 * Tries to read a char value typed into the console in lower case
	 * @return The first letter of the next line if possible in lower case, or Character.MIN_VALUE if not
	 */
	public char readLowerCaseChar() {
		return Character.toLowerCase(readChar());
	}
}
