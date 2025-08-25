package original_c_m_s;

public class DonationException {

    // Exception for invalid donor_id
    public static class DonorNotFoundException extends Exception {
        public DonorNotFoundException(String message) {
            super(message);
        }
    }

    // Exception for invalid amount
    public static class InvalidAmountException extends Exception {
        public InvalidAmountException(String message) {
            super(message);
        }
    }

    // Exception for invalid campaign_id
    public static class InvalidCampaignException extends Exception {
        public InvalidCampaignException(String message) {
            super(message);
        }
    }
}
