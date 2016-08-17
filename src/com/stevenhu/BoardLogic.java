package com.stevenhu;


import com.googlecode.lanterna.terminal.Terminal;
import static com.stevenhu.Main.WIDTH;
import static com.stevenhu.Main.HEIGHT;


/**
 * Created by Steven Hu on 2016-08-17.
 */
public class BoardLogic {

	
	public static void updateScreen(Player player, Enemy[] enemy, Terminal terminal) {
		terminal.clearScreen();
		
		drawWall(terminal);
		
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
	
	private static void drawWall(Terminal terminal) {
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
	
	public static void printGameOver(Terminal terminal) {
		terminal.clearScreen();
		
		
		String[] gameOverText = new String[5];
		gameOverText[0] = "#############";
		gameOverText[1] = "#           #";
		gameOverText[2] = "# GAME OVER #";
		gameOverText[3] = "#           #";
		gameOverText[4] = "#############";
		
		for (int i = 0; i < gameOverText.length; i++) {
			for (int j = 0; j < gameOverText[i].length(); j++) {
				terminal.moveCursor(j + 40, i + 10);
				terminal.putCharacter(gameOverText[i].charAt(j));
			}
		}
		
	}
}
