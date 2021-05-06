// -----------------------------------------------------
// COMP 249 Part2
// Written by: Imran Ahmed 401729321
// -----------------------------------------------------

public class NoSuchElementException extends Exception {
	
		/**
		 * Constructor the the nosuchelementexception class
		 */
		public NoSuchElementException() {
			super("Invalid Index! Program will termiante");
		}
		
		
		/**
		 * Parametrized Constructor the the nosuchelementexception class
		 */
		public NoSuchElementException(String s) {
			super(s);
		}
		
		/**
		 * Get message method 
		 */
		public String getMessage() {
			return super.getMessage();
		}

}
