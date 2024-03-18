
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Random;
import java.util.Scanner;

public class Test_2 {

	public static void main(String[] args) throws IOException {
		
		//Two Single Linked List for player_1 and player_2.
		SingleLinkedList sll_1 = new SingleLinkedList();
		SingleLinkedList sll_2 = new SingleLinkedList();
		
		//Variables for scores and turns.
		int score_1 = 0;
		int score_2 = 0;
		int turn = 1;
		boolean checkingTheBest = false;
		
		Random random = new Random();
		
		//-------------------GAME--------------------------//
		while (turn<=10) {
			
			boolean removeCheck1= false;
			boolean removeCheck2= false;
			boolean removeNumber1= true;
			boolean removeNumber2= true;
			
			//Selecting three numbers randomly between 1 and 6 for two players.
			for(int i=0; i<3; i++) {
				int Player1 = random.nextInt(6)+1;
				sll_1.add(Player1);
				int Player2 = random.nextInt(6)+1;
				sll_2.add(Player2);
			}
			
			//Displaying.
			System.out.println("");
			System.out.print("Turn: " + turn);
			System.out.println("");
			System.out.print("Player1: ");
			sll_1.displayGameAndScore();
			System.out.print(score_1);
			System.out.println("");
			System.out.print("Player2: ");
			sll_2.displayGameAndScore();
			System.out.print(score_2);
			System.out.println("");
			
			//Checking for at least 4 of the same numbers and removing them.
			//Increasing the scores.
			for (int j=1; j<=6; j++) {
				if (sll_1.countOfSameNumber(j) >= 4) {
					removeCheck1=true;
					sll_1.removeByCount(j, 4);
					score_1 = score_1 + 10;
				}
				if (sll_2.countOfSameNumber(j) >= 4) {
					removeCheck2=true;
					sll_2.removeByCount(j, 4);
					score_2 = score_2 + 10;
				}
			}
			
			//Checking for at least 6 consecutive numbers.
			for (int k=1; k<=6; k++) {
				while (sll_1.search(k) == false) {
					removeNumber1= false;
					break;
				}
				while (sll_2.search(k) == false) {
					removeNumber2= false;
					break;
				}
			}
			
			//Removing 6 consecutive numbers.
			//Increasing the scores.
			if (removeNumber1) {
				for (int m=1; m<=6; m++) {
					sll_1.removeByCount(m, 1);
				}
				score_1 = score_1 + 30;
			}
			if (removeNumber2) {
				for (int n=1; n<=6; n++) {
					sll_2.removeByCount(n, 1);
				}
				score_2 = score_2 + 30;
			}
			
			//Displaying for when numbers are removed from the game.
			if(removeCheck1 || removeNumber1 || removeCheck2 || removeNumber2) {
				System.out.println("");
				System.out.print("Player1: ");
				sll_1.displayGameAndScore();
				System.out.print(score_1);
				System.out.println("");
				System.out.print("Player2: ");
				sll_2.displayGameAndScore();
				System.out.print(score_2);
				System.out.println("");
			}
			//Increasing the turn of the game.
			turn++;
		}
		//---------------------------------------------------------//
		
		//Single Linked List for high score table.
		SingleLinkedList playerAndScore = new SingleLinkedList();
		
		Object hst = null;
		//Reading HighScoreTable file and adding the data to the list,
		File file = new File("HighScoreTable.txt");
		try {
		Scanner sc = new Scanner(new FileInputStream(file));
		while (sc.hasNextLine()){
			hst = new HighScoreTable(sc.next(),Integer.valueOf(sc.next()));
			playerAndScore.add(hst);
			
		}
		sc.close();
		}catch(FileNotFoundException fnf){
		    fnf.printStackTrace();
		}
		catch (Exception e) {
		    e.printStackTrace();
		    System.out.println("\nProgram terminated Safely...");
		}
		
		//End the game and calculating the score of the players.
		String result;
		String winner = "";
		
		System.out.println("");
		System.out.println("Game is over.");
		System.out.println("");
		if (score_1>score_2) {
			System.out.println("The winner is player1.");
			result= Integer.toString(score_1);
			winner= "Player1";
		}
		else if (score_1<score_2) {
			System.out.println("The winner is player2.");
			result= Integer.toString(score_2);
			winner= "Player2";
		}
		else {
			System.out.println("The scores are same, we randomly choose one of them as winner.");
			result= Integer.toString(score_2);
			int win = (int) (Math.random()*2+1);
			if (win==1) winner= "Player1";
			else winner= "Player2";
		}
		System.out.println("");
		System.out.println("High Score Table");
		System.out.println("");
		
		//Adding new player and new score to the list.
		Object player = null;
		player = new HighScoreTable(winner,Integer.parseInt(result));
		
		playerAndScore.add(player);
		
		//Sorting the list.
		playerAndScore.sortedList();
		
		//Checking if the player is among the best 10 players.
		checkingTheBest=playerAndScore.checkingHighScore(checkingTheBest, player);
		
		if(checkingTheBest) {
			
			//Adding new player and new score to the HighScoreTable file.
			Writer output;
			output = new BufferedWriter(new FileWriter(file.toString(), true));
			output.append("\r\n");
			output.append(winner + " " + result);
			output.close();
		}
		
		//Displaying the list.
		playerAndScore.displayLnHighScoreTable();
		
		
		
	}
}
