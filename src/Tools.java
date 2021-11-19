/*
 * store utility methods
 */

import java.util.Scanner;

public  class Tools {

	private static Scanner sc = new Scanner(System.in);

	public Tools() {
		this.sc = sc;
		// TODO scanner not closed?
	}

	//for reading char input
	public static char charScanner (String range) {
		String input = sc.next();
		if(input.matches("[A-Za-z]")) {
			char ch = input.charAt(0);
			if(range.contains(Character.toString(ch))) {
				return ch;
			}
			else {
				System.out.println("Your input is not in the range of '" + range +"';\nplease try again:");
				ch = charScanner(range);
				return ch;
			}
		}
		else {
			System.out.println("Your input is not a Character;\nplease try again:");
			char ch = charScanner(range);
			return ch;
		}
	}



	//for reading an int input
	public static int intScanner (int lowerBound, int upperBound) {
		if(sc.hasNextInt()) {
			int i = sc.nextInt();
			if(i<=upperBound && i>=lowerBound) {
				return i;
			}
			else {
				System.out.println("Your input is not in the range between "+lowerBound+" and "+upperBound+";\nplease try again:");
				i = intScanner(lowerBound, upperBound);
				return i;
			}
		}
		else {
			System.out.println("Your input is not an int;\nplease try again:");
			int i = intScanner(lowerBound, upperBound);
			return i;
		}

	}
	
	public static int intScannerInWindow (int lowerBound, int upperBound, Window w) {
		if(sc.hasNextInt()) {
			int i = sc.nextInt();
			if(i<=upperBound && i>=lowerBound) {
				return i;
			}
			else {
				w.newMessage("Your input is not in the range between "+lowerBound+" and "+upperBound+";\nplease try again:");
				i = intScanner(lowerBound, upperBound);
				return i;
			}
		}
		else {
			w.newMessage("Your input is not an int;\nplease try again:");
			int i = intScanner(lowerBound, upperBound);
			return i;
		}

	}
	/*
	 * TODO slow down the display of info
	 */
	/*
	public void slowPrint () {

	}
	*/
}
