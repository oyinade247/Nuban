package services.interfaces;

public interface NubanService {
    String generateAccountNumber(int bankCode);
    boolean validateNuban(String accountNumber, int bankcode);
}
