package com.stevenhu;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;

import java.nio.charset.Charset;

public class Main {
	
	public static final int WIDTH = 70; // Max 98
	public static final int HEIGHT = 20; //Max 28
	
	public static void main(String[] args) throws InterruptedException {
//		TODO: Read in level files
//		Initialize terminal
		Terminal terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
		terminal.enterPrivateMode();
		terminal.setCursorVisible(false);
		
		
//		Create player
		Player player = new Player(10, 10);
		
//		Create enemies
		Enemy[] enemy = Enemy.createNEnemies(10);
		
		
//		Gameplay
		while (!isGameOver(player, enemy)) {
			BoardLogic.updateScreen(player, enemy, terminal);
			
			Player.movePlayer(player, terminal);
			
			Enemy.enemyLogic(player, enemy);
			
			
		}
		
//		Game over
		BoardLogic.printGameOver(terminal);
		System.out.println("GAME OVER!");
	}
	
	
	
	private static boolean isGameOver(Player player, Enemy[] enemy) {
		for (int i = 0; i < enemy.length; i++) {
			if (player.x - enemy[i].x == 0 && player.y - enemy[i].y == 0) {
				return true;
			}
		}
		return false;
		
	}
	
}
