import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import utils.PhoneValidator;

public class Main {
	
	public static void main(String[] args) {
		readPhoneNumbers(args);
    }

	private static void readPhoneNumbers(String[] file) {
		BufferedReader reader = null;
		try {
			/** Since the only given input is the file, the first argument is taken */
			reader = new BufferedReader(new FileReader(file[0]));
			String line = reader.readLine();

			List<String> allCountryNumbers = new ArrayList<>();

			while (line != null) {
				/** If a phone number is short, it's automatically a portuguese number */
				if (PhoneValidator.isValidShortNumber(line)) {				
					allCountryNumbers.add("Portuguese");
				}
				if (PhoneValidator.isValidLongNumber(line)) {
					PhoneValidator.insertLongNumber(line, allCountryNumbers);
				}

				line = reader.readLine();
			}

			
			HashSet<String> uniqueValues = new HashSet<>(allCountryNumbers);
			for (String value : uniqueValues) {
				System.out.println(value + " " + Collections.frequency(allCountryNumbers, value));
			}
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
