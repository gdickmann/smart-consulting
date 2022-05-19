package utils;

public class PhoneFormatter {
    
    public static String formatLongNumber(String number) {

        String formattedNumber = number.replaceAll("\\s+", "");

        /** Plus marks aren't counted for character limit when it's a long phone number. */
        if (formattedNumber.startsWith("+")) 
            formattedNumber = formattedNumber.substring(1, formattedNumber.length());
            
        /** If the phone number starts with "00", it doesn't count for the character liimt. */
        if (formattedNumber.substring(0, 2).equals("00"))
            formattedNumber = formattedNumber.substring(2, formattedNumber.length());

        return formattedNumber;
    }

    public static String extractCountryCodeFromTextFile(String number) {
        return number.replaceAll("\\D+","");
    }
}
