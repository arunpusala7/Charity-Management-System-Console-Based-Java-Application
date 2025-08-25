package report;

public class reportException {

    // Exception for duplicate report ID
    public static class DuplicateReportIdException extends Exception {
        public DuplicateReportIdException(String message) {
            super(message);
        }
    }

    // Exception for non-existing organization ID
    public static class OrganizationNotFoundException extends Exception {
        public OrganizationNotFoundException(String message) {
            super(message);
        }
    }
}
