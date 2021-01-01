
public class BattleshipDriver {

	public static void main(String[] args) {
		System.out.println("Hi, let's play Battleship!\n");
		BattleshipGameGrid newGame = new BattleshipGameGrid(); // making a new game
		newGame.addingSANDG(); // calling the adding ships and grenades for user method
		newGame.randomAdding(); // calling the adding for computer method
		System.out.println("\n\n");
		String[][] array = newGame.getArray(); // setting the array to be the array from the battleShipGameGrid class
		boolean winner = false; // declaring boolean called: winner and setting to false

		while(winner != true) { // loop until winner becomes true
			newGame.playerTurn(); // calling the playerTurn method
			
			// checking if either's all ships have been sinked
			if (newGame.getNumOfShipsC() == 0 || newGame.getNumOfShipsH() == 0)
				winner = true; // winner becomes true
			else { // if all ships of either havent been sinked
				newGame.computerTurn(); // calling the computerTurn method
				
				// checking if either's ships have been sinked
				if (newGame.getNumOfShipsC() == 0 || newGame.getNumOfShipsH() == 0)
					winner = true; // winner becomes true
			}
		}
		System.out.println();
		
		// loop to print the array
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				if (array[i][j] != null) { // if its not empty printing s or g
					System.out.print(array[i][j] + " ");
				} else // if empty printing underline
					System.out.print("_ ");
			}
			System.out.println();
		}
		System.out.println("\nThanks for playing Battleship!");
		BattleshipGameGrid.input.close(); // closing the Scanner in the BattleshipGameGrid class called: input */
	}
}
