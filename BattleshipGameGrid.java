import java.util.Random;
import java.util.Scanner;

public class BattleshipGameGrid {
	private String[][] array; // Declaring a 2d array of type Position called array
	private String[][] display; // Declaring a 2d array of type Position called display
	private int numOfShipsH; // declaring int variable called numOfShipsH
	private int numOfShipsC; // declaring int variable called numOfShipsC
	static Scanner input = new Scanner(System.in); // Creating Scanner called input
	
	// Default Constructor
	BattleshipGameGrid() {
		array = new String[8][8]; // by default it will have an array of Positions, size 8 x 8
		display = new String[8][8]; // by default the display array would be 8 x 8
		numOfShipsH = 6; // setting the number of ships for Human would be 6
		numOfShipsC = 6; // setting the number of ships for Computer would be 6
	}

	// Accesor methods
	public String[][] getArray() {
		return array;
	}

	public void setArray(String[][] array) {
		this.array = array;
	}

	public String[][] getDisplay() {
		return display;
	}
	
	// Mutator methods
	public void setDisplay(String[][] display) {
		this.display = display;
	}

	public int getNumOfShipsH() {
		return numOfShipsH;
	}

	public void setNumOfShipsH(int numOfShipsH) {
		this.numOfShipsH = numOfShipsH;
	}

	public int getNumOfShipsC() {
		return numOfShipsC;
	}
	
	public void setNumOfShipsC(int numOfShipsC) {
		this.numOfShipsC = numOfShipsC;
	}

	// method to get the letter index
	public int getLetterIndex(String position) {
		char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' }; // creating an array with alphabets A to H

		for (int i = 0; i < alphabet.length; i++) { // loop to find the index of the letter
			if (alphabet[i] == position.charAt(0)) { // if the letter is found
				return i; // return the index
			}
		}
		return -1; // else return -1
	}

	// method to get the number index
	public int getNumIndex(String position) {
		char[] num = { '1', '2', '3', '4', '5', '6', '7', '8'};
		
		for (int i = 0; i < num.length; i++) { // loop to find the index of the number
			if (num[i] == position.charAt(1)) { // if the number is found
				return i; // return the index
			}
		}
		return -1; // else return -1
	}

	public void addingSANDG() { // method to add ships and grenades for user
		for (int i = 1; i <= 6; i++) { // loop to add ships
			boolean validCoordinate = false; // declaring boolean called validCoordinates and setting to false
			do {
				System.out.print("Enter the coordinates of your ship #" + i + ": ");
				String position = input.next().toUpperCase(); // storing the position as uppercase

				// if both indexes are between 0 and 7 inclusively
				if ((getLetterIndex(position) >= 0 && getLetterIndex(position) <= 7)
						&& (getNumIndex(position) >= 0 && getNumIndex(position) <= 7)) {

					// if the array at that index is empty then adding a new position
					if (array[getNumIndex(position)][getLetterIndex(position)] == null) {
						array[getNumIndex(position)][getLetterIndex(position)] = "s"; // adding s
						validCoordinate = true; // validCoordinate becomes true
					} else
						System.out.println("sorry, coordinates already used. try again");
				} else
					System.out.println("sorry, coordinates outside the grid. try again.");
			} while (validCoordinate != true);
			// loop until the person doesnt enter a coordinate that is within grid and not
			// already called
		}
		System.out.println();

		for (int i = 1; i <= 4; i++) { // loop to add grenades
			boolean validCoordinate = false; // declaring boolean called validCoordinates and setting to false
			do {
				System.out.print("Enter the coordinates of your grenade #" + i + ": ");
				String position = input.next().toUpperCase(); // storing the position as uppercase

				// if both indexes are between 0 and 7 inclusively
				if ((getLetterIndex(position) >= 0 && getLetterIndex(position) <= 7)
						&& (getNumIndex(position) >= 0 && getNumIndex(position) <= 7)) {

					// if the array at that index is empty then adding a new position
					if (array[getNumIndex(position)][getLetterIndex(position)] == null) {
						array[getNumIndex(position)][getLetterIndex(position)] = "g";
						validCoordinate = true; // validCoordinate becomes true
					} else
						System.out.println("sorry, coordinates already used. try again");
				} else
					System.out.println("sorry, coordinates outside the grid. try again.");
			} while (validCoordinate != true);
			// loop until the person doesnt enter a coordinate that is within grid and not
			// already called
		}
	}

	// method which places at ships and grenades at random places
	public void randomAdding() {
		Random r = new Random(); // creating a Random object called: r
		int shipsAndGrenadesAdded = 0; // Declaring variable called shipsAndGrenadesAdded and initializing to 0

		while (shipsAndGrenadesAdded != 6) { // until its not equal to 6
			int x = r.nextInt(8); // storing the random location of the row int int variable called x
			int y = r.nextInt(8); // storing the random location of the column in int variable called y

			if (array[x][y] == null) { // if the generated location is empty
				array[x][y] = "S";// adding a new object of type Position
				shipsAndGrenadesAdded++; // increasing the number of ships added
			}
		}

		while (shipsAndGrenadesAdded != 10) { // until its not equal to 10
			int x = r.nextInt(8); // storing the random location of the row int int variable called x
			int y = r.nextInt(8); // storing the random location of the colum int int variable called y

			if (array[x][y] == null) { // if the generated location is empty
				array[x][y] = "G"; // adding a new object of type Position
				shipsAndGrenadesAdded++; // increasing the number of grenades added
			}
		}
		System.out.println("\nOK the computer placed its ships and grenades at random.  Let's play.");
	}

	public void playerTurn() {
		boolean validCoordinate = false; // declaring boolean called: validCoordinate and setting to false
		do {
			System.out.print("position of your rocket: ");
			String position = input.next().toUpperCase(); // storing the position as uppercase
			int r = getNumIndex(position); // storing the row index in int r
			int c = getLetterIndex(position); // storing the column value in int c

			if (display[r][c] != null) { // if the display grid already contains a letter
				System.out.println("position already called.");
				validCoordinate = true; // validCoordinate becomes true
				displayGrid(); // calling the display grid method
				
			} else { // else checking if its ship, grenade or nothing
				// if both indexes are between 0 and 7 inclusively
				if ((c >= 0 && c <= 7) && (r >= 0 && r <= 7)) {
					validCoordinate = true; // validCoordinate becomes true
					if (array[r][c] != null) { // if the position isnt empty
						if (array[r][c] == "s" || array[r][c] == "S") { // checking if its ship
							System.out.print("ship hit.");
							display[r][c] = array[r][c]; // setting to be the same letter
							if (array[r][c] == "s")
								numOfShipsH--; // decrease the number of human ships
							else
								numOfShipsC--; // decrease the number of computer ships

							if (numOfShipsH == 0 || numOfShipsC == 0) { // checking if its zero ships for either
								if (numOfShipsH == 0) { // if all human ships have sinked
									System.out.print(" Computer Wins!");
									break; // breaking out of the do-while loop
								} else { // if all computer ships have sinked
									System.out.print(" You Win!");
									break; // breaking out of the do-while loop
								}
							} else { // if all ships havent sinked for either
								System.out.println();
								displayGrid(); // calling the display grid method
							}
						} else { // its a grenade
							System.out.println("boom! grenade hit.");
							display[r][c] = array[r][c]; // setting to be the same lettr
							displayGrid(); // calling the display grid method
							computerTurn(); // if grenade then call computerTurn method
						}
					} else { // if its empty
						System.out.println("nothing.");
						display[r][c] = "*";
						displayGrid(); // calling the display grid method
					}
				} else
					System.out.println("sorry, coordinates outside the grid. try again.");
			}
		} while (validCoordinate != true);
	}	
	
	public void computerTurn() {
		String validLetters = "ABCDEFGH"; // Creating a String variable called validLetters and storing letters A to H
		Random rand = new Random(); // creating a Random object called: rand
		char randomChar = validLetters.charAt(rand.nextInt(validLetters.length())); // storing the random character in
																					// char randomChar
		int num = rand.nextInt(8); // storing the random number in int variable called: randomNum
		num += 1; // making the values be 1 to 8
		String position = randomChar + "" + num; // storing the position
		System.out.print("position of my rocket: " + position + "\n");

		int r = getNumIndex(position); // storing the row index in int r
		int c = getLetterIndex(position); // storing the column value in int c

		if (display[r][c] != null) { // if the display grid already contains a letter
			System.out.println("position already called.");
			displayGrid(); // calling the display grid method

		} else { // else checking if its ship, grenade or nothing
			if (array[r][c] != null) { // if the position isnt empty
				if (array[r][c] == "s" || array[r][c] == "S") { // checking if its ship
					System.out.print("ship hit.");
					display[r][c] = array[r][c]; // setting to be the same letter
					if (array[r][c] == "s")
						numOfShipsH--; // decrease the number of human ships
					else
						numOfShipsC--; // decrease the number of computer ships

					if (numOfShipsH == 0 || numOfShipsC == 0) { // checking if its zero ships for either
						if (numOfShipsH == 0) { // if all human ships have sinked
							System.out.print(" Computer Wins!");
						} else { // if all computer ships have sinked
							System.out.print(" You Win!");
						}
					} else { // if all ships havent sinked for either
						System.out.println();
						displayGrid(); // calling the display grid method
					}
				} else { // its a grenade
					System.out.println("boom! grenade hit.");
					display[r][c] = array[r][c]; // setting to be the same lettr
					displayGrid(); // calling the display grid method
					playerTurn(); // if grenade then call computerTurn method
				}
			} else { // if its empty
				System.out.println("nothing.");
				display[r][c] = "*";
				displayGrid(); // calling the display grid method
			}
		}
	}

	public void displayGrid() {
		for (int i = 0; i < display.length; i++) {
			for (int j = 0; j < display.length; j++) {
				if (display[i][j] != null) {
					System.out.print(display[i][j] + " ");
				} else
					System.out.print("_ ");
			}
			System.out.println();
		}
	}
}