package event;

public class eventException {

    // Exception for duplicate event ID
    public static class DuplicateEventIdException extends Exception {
        public DuplicateEventIdException(String message) {
            super(message);
        }
    }

    // Exception for invalid organization ID (foreign key not found)
    public static class InvalidOrganizationException extends Exception {
        public InvalidOrganizationException(String message) {
            super(message);
        }
    }

    // Exception for invalid date format
    public static class InvalidDateFormatException extends Exception {
        public InvalidDateFormatException(String message) {
            super(message);
        }
    }
}
