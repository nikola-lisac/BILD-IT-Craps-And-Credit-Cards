package klase;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * Credit card numbers follow certain patterns. A credit card number must have between 13 and 16 digits.
 *  It must start with:
 * 4 for Visa cards
 * 5 for Master cards
 * 37 for American Express cards
 * 6 for Discover cards
 * Program is using Luhn's algorythm for checking the validity of the credit card number
 * Program is slightly modified in comparison to the defined assignment program
 * @author Nikola Lisicic
 */
public class CreditCard {

	/*
	 * Metoda provjerava validnost broja kreditne kartice
	 * @param number je broj kreditne kartice
	 * @return true ako je broj validan
	 * @return false ako broj nije validan
	 */
	public static boolean isValid(long number) {
		boolean valid = false;
		
		/*
		 * ako je duzina broja kreditne kartice izmedju 13 i 16,
		 * i ako je prefiks validan
		 */
		if (getSize(number) >= 13 && getSize(number) <= 16
				&& prefixMatched(number)) {
			
			/*
			 * ako je prema Luhn-ovom algoritmu, 
			 * zbir suma brojeva na parnim i suma brojeva na neparnim pozicijama,
			 * djeljiv sa 10, broj kartice je validan
			 */
			if ((sumOfDoubleEvenPlace(number) + sumOfOddPlace(number)) % 10 == 0) {
				valid = true;
			}
		}

		return valid;
	}

	/*
	 * Metoda provjerava da li prefix broja odgovara validnim prefiksima
	 * i vraca true, ako odgovara, odnosno false ako ne odgovara
	 */
	public static boolean prefixMatched(long number) {
		String[] prefixes = { "4", "5", "6", "37" };//definisani prefiksi koji su validni

		String numberString = number + "";
		
		for (int i = 0; i < prefixes.length; i++) {
			
			//ako prva cifra ili prve dvije cifre odgovaraju nekom od prefiksa, vraca true
			if (numberString.substring(0, prefixes[i].length()).equals(
					prefixes[i])) {
				return true;
			}
		}
		return false;

	}

	/*
	 * Metoda sabire dvostruke vrijednosti cifara na parnim mjestima proslijedjenog broja
	 * i vraca njihovu sumu
	 */
	public static int sumOfDoubleEvenPlace(long number) {

		String numberString = number + "";
		int sum = 0;

		//prolazi sve cifre na parnim pozicijama s desna na lijevo
		for (int i = numberString.length() - 2; i >= 0; i -= 2) {
			
			//vrijednost cifre na parnoj poziciji
			int num = Character.getNumericValue(numberString.charAt(i));
			
			/*
			 * odavanje dvostruke vrijednosti cifre na sumu, 
			 *s tim da, ako je ta vrijednost dvocifrena, dodaje se suma cifara tog dvocifrenog broja
			 *a ako je jednocifrena dodaje se samo ta vrijednost
			 */
			sum += getDigit(2*num);
		}
		return sum;
	}

	/*
	 *  Metoda vraca zbir cifara ako je proslijedjeni broj veci od 10,
	 *  odnosno vraca taj broj ako nije
	 */
	public static int getDigit(int number) {
		if (number >= 10) {
			return (number % 10 + number / 10);//vraca zbir cifara dvocifrenog broja
		} else {
			return number;
		}
	}

	/*
	 * Metoda sabira cifre na neparnim pozicijama proslijedjenog broja
	 * i vraca njihovu sumu
	 */
	public static int sumOfOddPlace(long number) {
		String numberString = number + "";
		int sum = 0;//suma

		//prolazi sve brojeve na neparnim pozicijama s desna na lijevo
		for (int i = numberString.length() - 1; i >= 0; i -= 2) {
			int num = Character.getNumericValue(numberString.charAt(i));//cifra na neparnoj poziciji
			sum += num;//dodaje vrijednost cifre na sumu
		}
		return sum;
	}

	//metoda vraca broj cifara prosledjenog broja
	public static int getSize(long number) {
		String numberString = number + "";
		return numberString.length();
	}

	public static void main(String[] args) {
		Scanner input = null;
		try {
			input = new Scanner(System.in);
			
			System.out.print("Enter a credit card number as a long integer: ");
			//broj kreditne kartice
			long creditCardNumber = input.nextLong();
			
			//ako je validan broj kartice ispisuje odgovarajucu poruku
			if (isValid(creditCardNumber)) {
				System.out.println("The credit card number is valid.");
			} 
			
			else {
				System.out.println("The credit card number is invalid.");
			}
		} catch (InputMismatchException e) {
			System.out.println("Input error.");
		}

	}

}
