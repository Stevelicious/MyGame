package com.stevenhu;


import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * Created by Steven Hu on 2016-08-17.
 */
public class BoardLogic {
	private Terminal terminal;
	public static int WIDTH; //max 97
	public static int HEIGHT; //max 27
	private Player player;
	private Enemy[] enemy;
	public int[][] board = new int[100][30];
	
	public enum Difficulty {EASY, NORMAL, HARD}
	
	
	
	public void readFile() throws FileNotFoundException {
		Scanner file = new Scanner(new File("level_1.txt"));
		int j = 0;
		
		while (file.hasNext()) {
			String row = file.nextLine();
			
			for (int i = 0; i < row.length(); i++) {
				if (row.charAt(i) == '\u2588') {
					board[i][j] = -1;
				} else {
					board[i][j] = 0;
				}
				
			}
			j++;
		}
	}
	
	private void drawBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == -1) {
					terminal.moveCursor(i, j);
					terminal.putCharacter('\u2588');
				}
			}
		}
	}
	
	
	public void createBoard(int width, int height) throws FileNotFoundException {
		terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
		terminal.enterPrivateMode();
		terminal.setCursorVisible(false);
		
		readFile();
		
		
		WIDTH = width;
		HEIGHT = height;
		
		drawBoard();
		
	}
	
	
	public void createGame(Difficulty difficulty) {
		player = new Player(10, 10);
		board[player.x][player.y] = 1;
		switch (difficulty) {
			case EASY:
				enemy = Enemy.createEnemies(1);
				break;
			case NORMAL:
				enemy = Enemy.createEnemies(5);
				break;
			case HARD:
				enemy = Enemy.createEnemies(10);
				break;
		}
		
		for (Enemy e :
				enemy) {
			board[e.x][e.y] = 2;
		}
		
	}
	
	public void gamePlay() throws InterruptedException {
		
		updateScreen();
		
		if (Player.movePlayer(player, terminal, board)) {
			
			Enemy.enemyLogic(player, enemy, board);
			
		}
	}
	
	
	public void updateScreen() {
		terminal.clearScreen();
		
		drawBoard();

//		Draw player
		terminal.moveCursor(player.x, player.y);
		terminal.putCharacter('\u263A');

//		Draw player info


//		Draw enemies
		for (int i = 0; i < enemy.length; i++) {
			terminal.moveCursor(enemy[i].x, enemy[i].y);
			terminal.putCharacter('\u06DE');
			terminal.moveCursor(0, 0);
		}
		
	}
	
	public boolean isGameOver() {
		for (int i = 0; i < enemy.length; i++) {
			if (player.x - enemy[i].x == 0 && player.y - enemy[i].y == 0) {
				return true;
			}
		}
		return false;
		
	}
	
	public void printGameOver() throws InterruptedException {
		terminal.clearScreen();
		
		
		String[] gameOverText = new String[5];
		gameOverText[0] = "##############";
		gameOverText[1] = "#            #";
		gameOverText[2] = "# GAME  OVER #";
		gameOverText[3] = "#            #";
		gameOverText[4] = "##############";
		
		for (int i = 0; i < gameOverText.length; i++) {
			for (int j = 0; j < gameOverText[i].length(); j++) {
				terminal.moveCursor(j + 42, i + 12);
				terminal.putCharacter(gameOverText[i].charAt(j));
			}
		}

//		Spiral in
		int k = 1, c1 = 0, c2 = 30 - 1, r1 = 0, r2 = 100 - 1;
		
		while (k <= 100 * 30) {
			for (int i = c1; i <= c2; i++) {
				Thread.sleep(3);
				k++;
				terminal.moveCursor(r1, i);
				terminal.putCharacter('#');
			}
			
			for (int j = r1 + 1; j <= r2; j++) {
				Thread.sleep(3);
				k++;
				terminal.moveCursor(j, c2);
				terminal.putCharacter('#');
			}
			
			for (int i = c2 - 1; i >= c1; i--) {
				Thread.sleep(3);
				k++;
				terminal.moveCursor(r2, i);
				terminal.putCharacter('#');
			}
			
			for (int j = r2 - 1; j >= r1 + 1; j--) {
				Thread.sleep(3);
				k++;
				terminal.moveCursor(j, c1);
				terminal.putCharacter('#');
			}
			
			c1++;
			c2--;
			r1++;
			r2--;
		}
		
	}
}
