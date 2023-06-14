package ch.bbw.pruefung3;

public class EANHelper {

    public static String generateEANCode(String countryCode, String manufacturerCode, String productCode) {
        String ean = countryCode + manufacturerCode + productCode;

        if (ean.length() != 12) {
            throw new IllegalArgumentException("Invalid EAN number");
        }

        int checksum = calculateChecksum(ean);

        return ean + checksum;
    }

    public static int calculateChecksum(String ean) {
        if (ean.length() != 12) {
            throw new IllegalArgumentException("Invalid EAN number");
        }

        int sumEven = 0;
        int sumOdd = 0;

        for (int i = 0; i < ean.length(); i++) {
            int digit = Character.getNumericValue(ean.charAt(i));

            if (i % 2 == 0) {
                sumEven += digit;
            } else {
                sumOdd += digit;
            }
        }

        int checksum = (sumEven * 3 + sumOdd) % 10;
        if (checksum != 0) {
            checksum = 10 - checksum;
        }

        return checksum;
    }

    public static void main(String[] args) {
        // Beispielanwendung der Methode generateEANCode
        String eanCode1 = EANHelper.generateEANCode("2", "21156", "456666");
        System.out.println(eanCode1);
        String eanCode2 = EANHelper.generateEANCode("978", "020137", "962");
        System.out.println(eanCode2);

        // Beispielanwendung der Methode calculateChecksum
        int checksum = EANHelper.calculateChecksum(eanCode1.substring(0,12));
        System.out.println(checksum);
        checksum = EANHelper.calculateChecksum(eanCode2.substring(0,12));
        System.out.println(checksum);
    }
}
