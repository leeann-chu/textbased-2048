/* Name: LeeAnn Chu
 * Netid: lchu7
 * Assignment: Project 1
 * Lab section: MW 2:00
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class utilities {
	public static Random r = new Random(); 
	public static boolean cont = true; /* is the game continuing */
	public static boolean full = true; /* is the board full or not */
	public static boolean firstCall = false; /* when getBoard is first called */
	
	public static ArrayList<ArrayList<String>> board = new ArrayList<ArrayList<String>>(); // Initializes 2D array of strings which holds our board
	public static int validMovs = 0; /* how many valid moves were made */
	public static int tries = 0; /* how many tries addToBoard attempts */
	public static int curInt = 0; /* Current Integer swipe() and move() are looking at */
	public static int nInt = 0; /* Next Integer swipe() and move() are looking at */
	
	public static void  clearScreen() { // "prints many empty lines" to pretend the screen is refreshing
		for (int i = 0; i <= 20; i++) {
			System.out.println();
		}
	}
	
	public static int findMax() { // Finds the maximum number on the board and returns it
		int max = 0;
		for (int i = 0; i < board.size(); i++) { /* Turn all Asterisk into a 0 */
			for(int j = 0; j < board.size(); j++) {
				if (board.get(i).get(j).equals("*")) 
					board.get(i).set(j, "0");		
			}
		}
		for (int i = 0; i < board.size(); i++) { 
			for(int j = 0; j < board.size(); j++) {
				if (Integer.parseInt(board.get(i).get(j)) > max){ 
					max = Integer.parseInt(board.get(i).get(j));
				}
			}
		}
		return max;
	}
	
	public static boolean isFull() { //Determines if the board is full and the game should end
		full = true;
		for (int i = 0; i < board.size(); i++) { 
			for(int j = 0; j < board.size(); j++) {
				if (board.get(i).get(j).equals("*") || board.get(i).get(j).equals("0")) 
					full = false;
			}
		}
		return full;
	}

	public static int weightedRanNum() { // Generates either a 2 or a 4 with 20% for a 4 and 80% for a 2
		int rnd = r.nextInt(100);
		if (rnd >= 80)
			return 4;
		else
			return 2;
	}

	public static void addToBoard() { //Adds to board unless it is full		
		int randRow = r.nextInt(4);
		int randColumn = r.nextInt(4);
		validMovs = 0;

		if (board.get(randRow).get(randColumn).equals("*") || board.get(randRow).get(randColumn).equals("0")) {
			board.get(randRow).set(randColumn, Integer.toString(weightedRanNum()));
			tries = 0; /* if it successfully can add a number to the board, number of tries is reset */
		} else if (tries <= 15) { /* if it cannot, increments tries */
			tries++;
			addToBoard();
		} else if (tries == 16) { /* if it has tried 16 times, it manually searches through the board for an empty space */
			tries = 0;
			OuterLoop: for (int i = 0; i < board.size(); i++) {
				for (int j = 0; j < board.size(); j++) {
					if (board.get(i).get(j).equals("*") || board.get(i).get(j).equals("0")) {
						board.get(i).set(j, Integer.toString(weightedRanNum()));
						break OuterLoop; //if it is successful it breaks out of both loops and adds the number
					} else 
						tries++; /* if it is unsuccessful, it increments tries */
				}
				if (tries >= 16) { /* if, after searching through the board, tries exceeds 16; Game Over */
					System.out.print("\n[GAME OVER]");
					cont = false;
				}
			}
		} else if (tries > 16) { /* if tries exceeds 16; Game Over (this is added in case it got lucky and skipped the manual search section*/
			System.out.print("\n[GAME OVER]");
			cont = false;
		}
	}

	public static void getBoard() { //Prints the board
		if (firstCall == false) { /* when first starting game prints board without spaces*/
			clearScreen();
		}
		firstCall = false;
		for (int i = 0; i <= 5; i++) {
			System.out.printf("%4s", "-");
		}
		System.out.println();
		for (int i = 0; i < board.size(); i++) {
			for (int j = 0; j < board.size(); j++) {
				if (j == 0) 
					System.out.printf("%4s", "|");
				if (board.get(i).get(j).equals("0")) {
					board.get(i).set(j, "*");
					System.out.printf("%4s", board.get(i).get(j));
				} else 
					System.out.printf("%4s", board.get(i).get(j));
				if (j == board.size() - 1) 
					System.out.printf("%4s", "|");
			}
			System.out.println();
		}
		for (int i = 0; i <= 5; i++) {
			System.out.printf("%4s", "-");
		}
	}

	public static void createBoard() { //Empties the board
		board.add(new ArrayList<>(Arrays.asList("*", "*", "*", "*")));
		board.add(new ArrayList<>(Arrays.asList("*", "*", "*", "*")));
		board.add(new ArrayList<>(Arrays.asList("*", "*", "*", "*")));
		board.add(new ArrayList<>(Arrays.asList("*", "*", "*", "*")));
	}
	
	public static void clearBoard() { // Clears the board
		for (int i = 0; i < board.size(); i++) { 
			for(int j = 0; j < board.size(); j++) {
				board.get(i).set(j, "*");		
			}
		}
	}

	public static void move(int dir) { // Shifting numbers in the correct direction
		if (dir == 1) { // for the left direction
			for (int i = 0; i < board.size(); i++) {
				for (int j = 0; j < board.size(); j++) {
					curInt = Integer.parseInt(board.get(i).get(j)); /* Get the current integer */
					if (j != board.size() - 1) 
						nInt = Integer.parseInt(board.get(i).get(j + 1)); /* if it's not on the edge of the board, next Integer is the next integer over*/
					 else 
						nInt = 0;
					if (curInt == 0) { /* if the current integer is a 0, replace it with the next integer, and replace where the next Integer was with a 0 */
						if (j != board.size() - 1 ) {
							board.get(i).set(j + 1, "0");
							board.get(i).set(j, Integer.toString(nInt));
							if (nInt != 0) { /* if the movement is not adding two 0s together, it's considered a valid move */
								validMovs++;
							}
						}
					} else /* else, leave it alone */
						board.get(i).set(j, Integer.toString(curInt));
				} 
			} 
		}
		
		if (dir == 2) { // for the right direction
			for (int i = 0; i < board.size(); i++) {
				for (int j = board.size() - 1; j >= 0; j--) {
					curInt = Integer.parseInt(board.get(i).get(j));
					if (j != 0) 
						nInt = Integer.parseInt(board.get(i).get(j - 1));
					 else 
						nInt = 0;
					if (curInt == 0) {
						if (j != 0) {
							board.get(i).set(j - 1, "0");
							board.get(i).set(j, Integer.toString(nInt));
							if (nInt != 0) {
								validMovs++;
							}
						} 
					} else
						board.get(i).set(j, Integer.toString(curInt));
				} 
			} 
		} 
		
		if (dir == 3) { // for the up direction
			for (int j = 0; j < board.size(); j++) {
				for (int i = 0; i < board.size(); i++) {
					curInt = Integer.parseInt(board.get(i).get(j));
					if (i != board.size() - 1) 
						nInt = Integer.parseInt(board.get(i + 1).get(j));
					 else 
						nInt = 0;
					if (curInt == 0) {
						if (i != board.size() - 1 ) {
							board.get(i + 1).set(j, "0");
							board.get(i).set(j, Integer.toString(nInt));
							if (nInt != 0) {
								validMovs++;
							}
						}
					} else
						board.get(i).set(j, Integer.toString(curInt));
				} 
			} 
		} 
		
		if (dir == 4) { // for the down direction
			for (int j = 0; j < board.size(); j++) {
				for (int i = board.size() - 1; i >= 0; i--) {
					curInt = Integer.parseInt(board.get(i).get(j));
					if (i != 0) 
						nInt = Integer.parseInt(board.get(i - 1).get(j));
					 else 
						nInt = 0;
					if (curInt == 0) {
						if (i != 0) {
							board.get(i - 1).set(j, "0");
							board.get(i).set(j, Integer.toString(nInt));
							if (nInt != 0) {
								validMovs++;
							}
						}
					} else
						board.get(i).set(j, Integer.toString(curInt));
				}
			} 
		}
	} 
	
	public static void swipe(int dir) { // The Master method, decides when to move() and when to add the numbers together
		for (int i = 0; i < board.size(); i++) { /* Turn all Asterisk into a 0 */
			for(int j = 0; j < board.size(); j++) {
				if (board.get(i).get(j).equals("*")) 
					board.get(i).set(j, "0");		
			}
		}
		for (int i = 0; i <= 3; i++) { /* ensures there are no leading zeros */
			move(dir); 
		}

		if (dir == 1) { // For the Left Direction			
			for (int i = 0; i < board.size(); i++) {  
				for (int j = 0; j < board.size() - 1; j++) { /* assigning current integer and next integer */
					curInt = Integer.parseInt(board.get(i).get(j));
					nInt = Integer.parseInt(board.get(i).get(j + 1));
					if (curInt == nInt) { /* if they match, add them */
						curInt += nInt; /* [ADDING] */
						if (curInt != 0 ) {
							validMovs++; /* Consider it a valid move if it's adding two numbers together and neither are 0 */
						}
						board.get(i).set(j + 1, "0"); /* and set the nextInt to 0 */
						}
					board.get(i).set(j, Integer.toString(curInt)); /* and set the curInt to it's updated value */
					move(1);
				}
			} 
		} 
		
		if (dir == 2) { // For the right direction
			for (int i = 0; i < board.size(); i++) { 
				for (int j = board.size() - 1; j > 0; j--) {
					curInt = Integer.parseInt(board.get(i).get(j));
					nInt = Integer.parseInt(board.get(i).get(j - 1));
					if (curInt == nInt) { 
						curInt += nInt;
						if (curInt != 0 ) {
							validMovs++;
						}
						board.get(i).set(j - 1, "0");	
					}
					board.get(i).set(j, Integer.toString(curInt));
					move(2);
				} 
			} 	
		} 

		if (dir == 3) { //for the up direction 
			for (int j = 0; j < board.size(); j++) {  
				for (int i = 0; i < board.size() - 1; i++) { 
					curInt = Integer.parseInt(board.get(i).get(j));
					nInt = Integer.parseInt(board.get(i + 1).get(j));
					if (curInt == nInt) { 
						curInt += nInt;
						if (curInt != 0 ) {
							validMovs++;
						}
						board.get(i + 1).set(j, "0"); 
					}
					board.get(i).set(j, Integer.toString(curInt));
					move(3);
				}  
			} 
		} 

		if (dir == 4) { //for the down direction 
			for (int j = 0; j < board.size(); j++) { 
				for (int i = board.size() - 1 ; i > 0; i--) { 
					curInt = Integer.parseInt(board.get(i).get(j));
					nInt = Integer.parseInt(board.get(i - 1).get(j));
					if (curInt == nInt) { 
						curInt += nInt;
						if (curInt != 0 ) {
							validMovs++;
						}
						board.get(i - 1).set(j, "0"); 
					}
					board.get(i).set(j, Integer.toString(curInt));
					move(4);
				} 
			} 	
		} 
	} 
}
