import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import utils.PhoneFormatter;
import utils.PhoneValidator;

public class Main {
	
	public static void main(String[] args) {
		String number = "446670";
		System.out.println(PhoneValidator.isValidShortNumber(number));
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
