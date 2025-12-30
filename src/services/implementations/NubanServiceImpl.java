package services.implementations;

import services.interfaces.NubanService;

public class NubanServiceImpl implements NubanService {
    private static int serialNumber = 1;

    @Override
    public String generateAccountNumber(int bankCode) {
        int getSerial = serialNumber;
        serialNumber++;

        int checkDigit = calculateCheckDigit(bankCode, getSerial);
        String nineDigit = String.format("%09d", getSerial);

        return nineDigit + checkDigit;
    }

    @Override
    public boolean validateNuban(String accountNumber, int bankcode) {
        int serial = Integer.parseInt(accountNumber.substring(0,9));

        int lastDigit = Integer.parseInt(accountNumber.substring(9));

        int checkDigit = calculateCheckDigit(bankcode, serial);
        return checkDigit == lastDigit;
    }




    private int calculateCheckDigit(int bankCode, int getSerial){
        int [] cbnNumber = {3,7,3,3,7,3,3,7,3,3,7,3};

        long combined = bankCode * 1000000000L + getSerial;

        int sum = 0;
        for(int count = cbnNumber.length - 1; count >= 0; count--){
            int remainder = (int)(combined % 10);
            combined /= 10;
            sum += remainder * cbnNumber[count];
        }

        int lastDigit = sum % 10;
        int checkDigit = 10 - lastDigit;

        if(checkDigit == 10)return 0;
        return checkDigit;
    }
}
