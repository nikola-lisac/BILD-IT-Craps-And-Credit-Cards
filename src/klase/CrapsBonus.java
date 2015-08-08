package klase;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * (Game: chance of winning at craps) 
 * Revise Exercise 6.30 to run it 10,000 time
 * and display the number of winning games.
 * @author Nikola Lisicic
 */
public class CrapsBonus {
	
	public static int numberOfWins = 0;
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
		
		return sum;
	}
	
	/*
	 * Metoda zapocinje novu igru i povecava brojac pobjeda ako je korisnik pobjedio
	 */
	public static void newGame() {
		if(playerHasWon(rollDice())){
			numberOfWins++;
		}
		
	}
	
	
	public static void main(String[] args) {
		Scanner input = null;
		try {
			
			input = new Scanner(System.in);
			System.out.print("Enter the number of games you want to play: ");
			
			int numOfGames = input.nextInt();//koliko ce puta igra biti odigrana
			
			for(int i=0;i<numOfGames;i++){
				newGame();//odigravanje igre
			}
			
			//racunanje procenta pobjeda
			double winningPercentage = ((double)numberOfWins/numOfGames)*100;
			
			//ipis broja i procenta pobjeda
			System.out.println("Number of wins in "+numOfGames+" games: "+numberOfWins);
			System.out.println("Percentage of winning is: "+winningPercentage+" %");
			
		} catch(InputMismatchException e){
			System.out.println("Input error");
		}
	}

}
