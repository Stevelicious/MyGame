package com.stevenhu;


import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;

import java.nio.charset.Charset;

/**
 * Created by Steven Hu on 2016-08-17.
 */
public class BoardLogic {
	private Terminal terminal;
	public static int WIDTH;
	public static int HEIGHT;
	private Player player;
	private Enemy[] enemy;
	public enum Difficulty {EASY, NORMAL, HARD};
	
	
	public void createBoard(int width, int height) {
		terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
		terminal.enterPrivateMode();
		terminal.setCursorVisible(false);
		
		WIDTH = width;
		HEIGHT = height;
		
		player = new Player(10, 10);
		enemy = Enemy.createEnemies(2);
		
		
	}
	
	public void createGame(Difficulty difficulty) {
		player = new Player(10, 10);
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
		
	}
	
	public void gamePlay() throws InterruptedException {
		
		updateScreen();
		
		if (Player.movePlayer(player, terminal)) {
			
			Enemy.enemyLogic(player, enemy);
			
		}
	}
	
	
	public void updateScreen() {
		terminal.clearScreen();
		
		drawWall();

//		Draw player
		terminal.moveCursor(player.x, player.y);
		terminal.putCharacter('\u263A');

//		Draw enemies
		for (int i = 0; i < enemy.length; i++) {
			terminal.moveCursor(enemy[i].x, enemy[i].y);
			terminal.putCharacter('\u06DE');
			terminal.moveCursor(0, 0);
		}
		
	}
	
	private void drawWall() {
		for (int i = 0; i <= WIDTH; i++) {
//			Top boarder
			terminal.moveCursor(i + 1, 0);
			terminal.putCharacter('\u2550');

//			Bottom boarder
			terminal.moveCursor(i + 1, HEIGHT + 2);
			terminal.putCharacter('\u2550');
		}
		
		for (int i = 0; i <= HEIGHT; i++) {
//			Left boarder
			terminal.moveCursor(0, i + 1);
			terminal.putCharacter('\u2551');

//			Right boarder
			terminal.moveCursor(WIDTH + 2, i + 1);
			terminal.putCharacter('\u2551');
		}

//		Corners
//		Top left corner
		terminal.moveCursor(0, 0);
		terminal.putCharacter('\u2554');

//		Top right corner
		terminal.moveCursor(WIDTH + 2, 0);
		terminal.putCharacter('\u2557');

//		Bottom left corner
		terminal.moveCursor(0, HEIGHT + 2);
		terminal.putCharacter('\u255A');

//		Bottom right corner
		terminal.moveCursor(WIDTH + 2, HEIGHT + 2);
		terminal.putCharacter('\u255D');
		
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
