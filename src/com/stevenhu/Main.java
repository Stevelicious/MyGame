package com.stevenhu;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;

import java.nio.charset.Charset;

public class Main {
	
	public static void main(String[] args) throws InterruptedException {
//		TODO: Read in level files
//
		BoardLogic board = new BoardLogic();
		board.createBoard(50, 20);
		
		while (true) {
			
			board.createGame(3);
		
//		    Gameplay
			while (!board.isGameOver()) {
				board.gamePlay();
			}

//		    Game over
			board.printGameOver();
			
		}
		
	}
	
	
	
	
}
