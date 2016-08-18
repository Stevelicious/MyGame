package com.stevenhu;


import java.io.FileNotFoundException;

public class Main {
	
	public static void main(String[] args) throws InterruptedException, FileNotFoundException {

		BoardLogic board = new BoardLogic();
		board.createBoard(98, 27);
		
		while (true) {
			
			board.createGame(BoardLogic.Difficulty.EASY);
		
//		    Gameplay
			while (!board.isGameOver()) {
				board.gamePlay();
			}

//		    Game over
			board.printGameOver();
			
		}
		
	}
	
	
	
	
}
