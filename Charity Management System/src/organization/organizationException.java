package organization;

public class organizationException {
    
    public static class DuplicateOrganizationIdException extends Exception {
        public DuplicateOrganizationIdException(String message) {
            super(message);
        }
    }
}
