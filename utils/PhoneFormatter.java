package utils;

public class PhoneFormatter {
    
    public static String formatLongNumber(String number) {
        
        String formattedNumber = number.replaceAll("\\s+", "");

        if (formattedNumber.startsWith("+")) {
            formattedNumber = formattedNumber.substring(1, formattedNumber.length());
        }

        return formattedNumber;
    }

}
