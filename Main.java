import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import utils.FileGenerator;
import utils.PhoneValidator;

import model.Country;

/**
 * @author Gustavo Henrique Dickmann
 * gustavo.dickmann123@gmail.com
 */

public class Main {

	private static List<String> allCountryNumbers = new ArrayList<>();
	
	public static void main(String[] args) {
		FileGenerator.generateCountryCodeFile();
		readPhoneNumbers(args);
    }

	private static void readPhoneNumbers(String[] file) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file[0]));
			String line = reader.readLine();

			while (line != null) {

				/** If a phone number is short, it's automatically a portuguese number */
				if (PhoneValidator.isValidShortNumber(line)) {				
					allCountryNumbers.add("Portugal");
				}
				if (PhoneValidator.isValidLongNumber(line)) {
					PhoneValidator.insertLongNumber(line, allCountryNumbers);
				}

				line = reader.readLine();
			}
			
			outputAllGivenPhoneNumbers();
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void outputAllGivenPhoneNumbers() {
		HashSet<String> uniqueValues = new HashSet<>(allCountryNumbers);
		List<Country> insertedCountries = new ArrayList<>();

		for (String value : uniqueValues) {
			insertedCountries.add(new Country(value, Collections.frequency(allCountryNumbers, value)));
		}

		/** The output must contain a descending order based on how many times a country number was inserted */
		Collections.sort(insertedCountries, Comparator.comparingInt(Country::getTimesInserted).reversed());

		for (Country country : insertedCountries) {
			System.out.println(country.getName() + ": " + country.getTimesInserted());
		}
	}
}
