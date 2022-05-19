package utils;

public class PhoneValidator {
    
    private static boolean isShortNumber(String number) {
        return number.trim().length() >= 4 && number.trim().length() <= 6;
    }

    public static boolean isLongNumber(String number) {
        /** Plus marks aren't counted for character limit when it's a long phone number. */
        String formattedNumber = PhoneFormatter.formatLongNumber(number);
        return formattedNumber.trim().length() >= 9 && formattedNumber.trim().length() <= 14;
    }

    /** Short numbers are automatically portuguese numbers. */
    public static boolean isValidShortNumber(String number) {
        if (isShortNumber(number)) {
            if (number.contains(" "))
                return false;
    
            if (number.trim().startsWith("0"))
                return false;

            return true;
        }

        return false;
    }

    public static boolean isValidLongNumber(String number) {
        if (isLongNumber(number)) {
            if (number.trim().startsWith("+")) 
                return false;

            return true;
        }

        return false;
    }
}
