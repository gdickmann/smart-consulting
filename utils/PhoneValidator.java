package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
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
            if (containsInvalidBeginning(number))
                return false;

            if (longNumberContainsInvalidCharacters(number))
                return false;

            if (containsInvalidWhiteSpace(number))
                return false;

            return true;
        }

        return false;
    }

    /** Phone numbers can't contain special characters (except the plus mark at the beginning) and lettrs. */
    public static boolean longNumberContainsInvalidCharacters(String number) {
        String formattedNumber = PhoneFormatter.formatLongNumber(number);

        Pattern pattern = Pattern.compile("[^0-9]+", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(formattedNumber);

        boolean containsSpecialCharacter = matcher.find();

        return containsSpecialCharacter;
    }

    /** Phone numbers can start with 00 if doesn't start with "+". */
    public static boolean containsInvalidBeginning(String number) {
        return number.trim().startsWith("+") && number.substring(1, 3).equals("00");
    }

    /** Phone numbers can't contain white space between plus mark/00 and country code. */
    public static boolean containsInvalidWhiteSpace(String number) {
        boolean invalidPlusMark = !containsInvalidBeginning(number) && number.substring(0, 2).contains(" ");
        boolean invalidZeroBeginning = !containsInvalidBeginning(number) && number.substring(0, 3).contains(" ");

        return invalidPlusMark || invalidZeroBeginning;
    }

    public static boolean containsCountryCode(String number) {
        return number.trim().startsWith("+");
    }

    public static String getCountryCode(String number) {
        /** Phone numbers only contains country code if it starts with a plus mark. */
        if (containsCountryCode(number)) {
            String[] countryCode = number.split("\\s+");
            /** Remove the plus mark from country code */
            return countryCode[0].replaceAll("\\+","");
        }

        return number;
    }

    public static void insertLongNumber(String file, List<String> phoneNumbers) {
        BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("./countryCodes.txt"));
			String line = reader.readLine();

			while (line != null) {
                String countryCodes = PhoneFormatter.extractCountryCodeFromTextFile(line);                

                String inputCountryCode = getCountryCode(file);
                
                if (inputCountryCode.equals(countryCodes)) {
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
