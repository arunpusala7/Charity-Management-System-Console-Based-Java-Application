package volunteer;

public class volunteerException {

    // Exception for duplicate volunteer ID
    public static class DuplicateVolunteerIdException extends Exception {
        public DuplicateVolunteerIdException(String message) {
            super(message);
        }
    }

    // Exception for invalid email format
    public static class InvalidEmailFormatException extends Exception {
        public InvalidEmailFormatException(String message) {
            super(message);
        }
    }

    // Exception for invalid phone number
    public static class InvalidPhoneNumberException extends Exception {
        public InvalidPhoneNumberException(String message) {
            super(message);
        }
    }
}
