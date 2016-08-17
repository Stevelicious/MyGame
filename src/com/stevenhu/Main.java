package com.stevenhu;


public class Main {
	
	public static void main(String[] args) throws InterruptedException {

		BoardLogic board = new BoardLogic();
		board.createBoard(98, 28);
		
		while (true) {
			
			board.createGame(BoardLogic.Difficulty.HARD);
		
//		    Gameplay
			while (!board.isGameOver()) {
				board.gamePlay();
			}

//		    Game over
			board.printGameOver();
			
		}
		
	}
	
	
	
	
}
