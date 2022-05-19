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
		String[] number = {"+44 65465444", "+56 918 878 443", "+82 918 878 443", "+666 65465444", "+44 65465444", "3333"};
		List<String> allPhoneNumbers = new ArrayList<>();

		for (String phoneNumber : number) {
			/** If a phone number is short, it's automatically a portuguese number */
			if (PhoneValidator.isValidShortNumber(phoneNumber)) {				
				allPhoneNumbers.add("Portuguese");
			}
			if (PhoneValidator.isValidLongNumber(phoneNumber)) {
				PhoneValidator.insertLongNumber(phoneNumber, allPhoneNumbers);
			}
		}

		HashSet<String> uniqueValues = new HashSet<>(allPhoneNumbers);
		for (String value : uniqueValues) {
			System.out.println(value + " " + Collections.frequency(allPhoneNumbers, value));
		}

    }

	private static void readPhoneNumbers(String[] file) {
		BufferedReader reader = null;
		try {
			/** Since the only given input is the file, the first argument is taken */
			reader = new BufferedReader(new FileReader(file[0]));
			String line = reader.readLine();

			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
