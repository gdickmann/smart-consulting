package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
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

    public static boolean isValidShortNumber(String number) {
        if (isShortNumber(number)) {
            /** Shorts numbers can't contain white spaces */
            if (number.contains(" "))
                return false;

            /** Shorts numbers can't start with 0 */
            if (number.trim().startsWith("0"))
                return false;

            return true;
        }

        return false;
    }

    public static boolean isValidLongNumber(String number) {
        if (isLongNumber(number)) {
            /** Phone numbers can't contain "+" and "00" at the beginning */
            if (containsInvalidBeginning(number))
                return false;

            /** Phone numbers can't contain special characters (except the plus mark at the beginning) and lettrs. */
            if (longNumberContainsInvalidCharacters(number))
                return false;

            /** Phone numbers can't contain white space between plus mark/00 and country code. */
            if (containsInvalidWhiteSpace(number))
                return false;

            return true;
        }

        return false;
    }

    private static boolean longNumberContainsInvalidCharacters(String number) {
        String formattedNumber = PhoneFormatter.formatLongNumber(number);

        Pattern pattern = Pattern.compile("[^0-9]+", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(formattedNumber);

        boolean containsSpecialCharacter = matcher.find();

        return containsSpecialCharacter;
    }

    private static boolean containsInvalidBeginning(String number) {
        return number.trim().startsWith("+") && number.substring(1, 3).equals("00");
    }

    private static boolean containsInvalidWhiteSpace(String number) {
        boolean invalidPlusMark = !containsInvalidBeginning(number) && number.substring(0, 2).contains(" ");
        boolean invalidZeroBeginning = !containsInvalidBeginning(number) && number.substring(0, 3).contains(" ");

        return invalidPlusMark || invalidZeroBeginning;
    }

    public static String getCountryCode(String number) {
        /** Phone numbers only contains country code if it starts with a plus mark. */
        if (number.trim().startsWith("+")) {
            String[] countryCode = number.split("\\s+");
            /** Remove the plus mark from country code */
            return countryCode[0].replaceAll("\\+","");
        }

        return number;
    }

    public static void insertLongNumber(String file, List<String> phoneNumbers) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("./countryCodes.txt"));
			String line = reader.readLine();

			while (line != null) {
                String countryCode = PhoneFormatter.extractCountryCodeFromTextFile(line);                
                String phoneNumberCountryCode = getCountryCode(file);
                
                if (phoneNumberCountryCode.equals(countryCode)) {
                    phoneNumbers.add(PhoneFormatter.extractCountryNameFromTextFile(line));
                }
                
				line = reader.readLine();
			}                        

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
