package campain;

public class campainException {

    // Exception for duplicate campaign ID
    public static class DuplicateCampaignIdException extends Exception {
        public DuplicateCampaignIdException(String message) {
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
