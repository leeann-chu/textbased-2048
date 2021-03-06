/* Name: LeeAnn Chu
 * Netid: lchu7
 * Assignment: Project 1
 * Lab section: MW 2:00
 */
import java.util.Scanner;

public class text2048 extends utilities {
	public static String input = "";
	public static String[] valMovResponse = new String[] {"\nWhich was a VALID move!", "\nWhich was an INVALID move!"};
	public static int strIndex = 1;
	
	public static int moveNum = 0;
	public static Scanner s = new Scanner(System.in);

	public static void move(String input) {
		if ((input.equals("a")) || (input.equals("d")) || (input.equals("w")) || (input.equals("s"))) { // VALID MOVES //
			if (input.equals("a")) {
				swipe(1); //translate the letters to directions
			} else if (input.equals("d")) {
				swipe(2);
			} else if (input.equals("w")) 
				swipe(3);
			else if (input.equals("s")) {
				swipe(4);
			}
			if (isFull() == true) { //if the board is full game over!
				cont = false;
				firstCall = true;
				getBoard();
				System.out.print("\n[GAME OVER]");
				System.out.print("\nYou entered: \"" + input +"\"");
				System.out.print(valMovResponse[1]);
				System.out.print("\nTotal Moves Made: " + moveNum);
				System.out.print("\nScore: " + findMax());
			}
			if (validMovs > 0) { //If a valid move was made, add a number to the board and increase the move number count
				strIndex = 0;
				addToBoard();
				moveNum++;
			} else 
				strIndex = 1; //Also print out "valid move"

		} else if (input.equals("r")) { // RESET //
			getBoard(); /* this seems really unintuitve and I don't like it
			 			 * but in piazza he states "print board with
			 			 * every valid input" so here's the board again */
			System.out.print("\nYou entered: \"" + input +"\"");
			System.out.print(valMovResponse[1]); /* this is an array and and index so that I can set if the move is valid or not
												  * before the board is printed out, but still output the response after it is */
			System.out.print("\nAre you sure you want to reset? (y/n)"); 
			System.out.print("\nInput: ");
			input = s.nextLine();
			while (!input.toLowerCase().equals("y") && !input.toLowerCase().equals("n")) {
				getBoard(); /* it just seems silly to have to print it out 
				 			 * over and over again */
				System.out.print("\nAnswer with either a \"y\" or a \"n\"");
				System.out.print("\nInput: ");
				input = s.nextLine();
			}
			if (input.equals("y")) {
				System.out.print("\nYou entered: \"" + input +"\"");
				System.out.print(valMovResponse[1]); /* and you see this code a lot because in the requirements he specified "after 
													  * every single keypress so here it is again */
				System.out.print("\nHere's your new board!\n");
				moveNum = 0;
				play(); // recreates the beginning board and resets moveNum
			}
			if (input.equals("n")) {
				getBoard(); /* I mean, technically the project page just says "...to reflect the updated board"
				 			 * and the board isn't actually updating */
				System.out.print("\nYou entered: \"" + input +"\"");
				System.out.print(valMovResponse[1]);
				System.out.print("\nOkay! Make your next move: "); 
				System.out.print("\nInput: ");
				input = s.nextLine(); 
				move(input);
			}
			
		} else if (input.equals("q")) { // QUIT //
			getBoard(); 
			System.out.print("\nYou entered: \"" + input +"\"");
			System.out.print(valMovResponse[1]);
			System.out.print("\nAre you sure you want to quit? (y/n)"); 
			System.out.print("\nInput: ");
			input = s.nextLine();
			while (!input.toLowerCase().equals("y") && !input.toLowerCase().equals("n")) {
				getBoard(); 
				System.out.print("\nYou entered: \"" + input +"\"");
				System.out.print(valMovResponse[1]);
				System.out.print("\nAnswer with either a \"y\" or a \"n\"");
				System.out.print("\nInput: ");
				input = s.nextLine();
			}
			if (input.equals("y")) {
				getBoard();
				System.out.print("\nYou entered: \"" + input +"\"");
				System.out.print(valMovResponse[1]);
				System.out.print("\nBye! Thanks for playing");
				cont = false; // if after the confirmation the user still wants to quit, the game stops
			}
			if (input.equals("n")) {
				getBoard();
				System.out.print("\nYou entered: \"" + input +"\"");
				System.out.print(valMovResponse[strIndex]);
				System.out.print("\nOkay! Make your next move: "); 
				System.out.print("\nInput: ");
				input = s.nextLine(); 
				move(input);
			}
		}
		else {
			getBoard();
			System.out.print("\nYou entered: \"" + input +"\"");
			System.out.print(valMovResponse[1]);
			System.out.print(" Try Again: "); /* this is not a valid input so I don't need to print the board again*/
			input = s.nextLine(); 
			move(input);
		}
		if (cont == true) {
			String max = Integer.toString(findMax()); /* findMax() before turning all the numbers to asterisks */
			getBoard();
			System.out.print("\nYou entered: \"" + input +"\"");
			System.out.print(valMovResponse[strIndex]);
			System.out.print("\nMoves made: " + moveNum);
			System.out.print("\nScore: " + max);
		}
	}

	public static void main(String[] args) {
		System.out.println();
		System.out.println("***************************************************");
		System.out.println("*                    >> 2048 <<                   *");
		System.out.println("***************************************************");
		System.out.println("* [HOW TO PLAY]: Use your A,D,W,S keys to move    *\n* the tiles left, right, up, down. When two tiles *\n* with the same number meet, they merge into one! *");
		System.out.println("***************************************************");
		createBoard();
		play();
	}

	public static void play() {
		clearBoard();
		addToBoard();
		addToBoard();
		firstCall = true;
		getBoard();
		while (cont == true) {
			System.out.print("\nInput: ");
			input = s.nextLine();
			move(input);
			
		}
	}

}
