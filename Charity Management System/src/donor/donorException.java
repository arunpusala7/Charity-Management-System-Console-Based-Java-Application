package donor;

public class donorException {

	    // Exception for duplicate donor ID
	    public static class DuplicateDonorIdException extends Exception {
	        public DuplicateDonorIdException(String message) {
	            super(message);
	        }
	    }

	    // Exception for invalid phone number
	    public static class InvalidPhoneNumberException extends Exception {
	        public InvalidPhoneNumberException(String message) {
	            super(message);
	        }
	    }

	    // Exception for invalid email format
	    public static class InvalidEmailFormatException extends Exception {
	        public InvalidEmailFormatException(String message) {
	            super(message);
	        }
	    }
	}




