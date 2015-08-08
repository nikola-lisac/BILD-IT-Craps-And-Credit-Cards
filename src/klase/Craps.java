package klase;
/*
 * Craps is a popular dice game played in casinos. 
 * Write a program to play a variation of the game, as follows:
 * Roll two dice. Each die has six faces representing values 1, 2, …, and 6, respectively. 
 * Check the sum of the two dice. If the sum is 2, 3, or 12 (called craps), you lose; 
 * if the sum is 7 or 11 (called natural), you win; 
 * if the sum is another value (i.e., 4, 5, 6, 8, 9, or 10), a point is established. 
 * Continue to roll the dice until either a 7 or the same point value is rolled. 
 * If 7 is rolled, you lose. Otherwise, you win. Your program acts as a single player.
 * @author Nikola Lisicic
 */

public class Craps {
	
	
	/*
	 * Metoda koja rjesava situaciju point-a
	 * @param point predstavlja vrijednost koju korisnik mora dobiti da bi pobjedio
	 * petlja se vrti sve dok suma bacanja dve kockice ne bude jednaka pointu i tad metoda vraca true
	 * odnosno korisnik je pobjedio, ili dok suma bacanja dve kockice ne bude 7, 
	 * tada vraca false i racunar je pobjedio
	 */
	public static boolean pointRoll(int point){
		while(true){
			
			int newResult=rollDice();//novo bacanje kockica
			
			if(newResult == point){
				return true;
			}
			
			else if(newResult == 7){
				return false;
			}
			
		}
	}
	/*
	 * Metoda za provjeru pobjednika
	 * @param result predstavlja sumu dve bacene kockice
	 * ako je pobjednik korisnik vraca true, ako je racunar vraca false
	 */
	public static boolean playerHasWon(int result) {
		
		//za ove vrijednosti automatski pobjedjuje racunar
		if(result == 2 || result==3 ||result==12){
			return false;
		}
		
		//za ove vrijednosti automatski pobjedjuje korisik
		else if (result == 7 || result ==11){
			return true;
		}
		
		/*
		 * za ostale vrijednosti ponovo se bacaju kockice, s ciljem dobijanja iste sume
		 * ako se dobije ista suma, pobjednik je korisnik
		 * ako se dobije 7, pobjednik je racunar
		 */
		else {
			System.out.println("Point is: "+result);
			if(pointRoll(result)){//pozivanje metode za rjesenje situacije point-a
				return true;
			}
			else {
				return false;
			}
		}
		
	}
	
	/*
	 * Metoda za inicijalno bacanje kockica
	 * @return suma vrijednosti bacenih kockica
	 */
	public static int rollDice(){
		int dieOne = (int) ((Math.random() * 6) + 1);//prva kocka
		int dieTwo = (int) ((Math.random() * 6) + 1);//druga kocka
		
		int sum = dieOne + dieTwo;//zbir vrijednosti na kockama
		
		System.out.println("You rolled "+dieOne+" + "+dieTwo+" = "+sum);
		return sum;
	}
	
	/*
	 * Metoda zapocinje novu igru i obavjesava korisnika o ishodu igre
	 */
	public static void newGame() {
		if(playerHasWon(rollDice())){
			System.out.println("You win!");
		}
		else{
			System.out.println("You lose!");
		}
	}

	public static void main(String[] args) {
		
			newGame();//zapocinjanje nove igre
		
	}

}
