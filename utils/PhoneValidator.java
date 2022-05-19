package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidator {
    
    private static boolean isShortNumber(String number) {
        return number.trim().length() >= 4 && number.trim().length() <= 6;
    }

    public static boolean isLongNumber(String number) {
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

            if (longNumberContainsInvalidCharacters(number))
                return false;

            return true;
        }

        return false;
    }

    public static boolean longNumberContainsInvalidCharacters(String number) {
        String formattedNumber = PhoneFormatter.formatLongNumber(number);

        Pattern pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(formattedNumber);

        boolean containsSpecialCharacter = matcher.find();

        return containsSpecialCharacter;
    }

    /** Phone numbers can start with 00 if doesn't start with "+". */
    public static boolean containsInvalidBeginning(String number) {
        return number.trim().startsWith("+") && number.substring(1, 3).equals("00");
    }

}
