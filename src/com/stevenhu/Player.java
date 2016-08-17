package com.stevenhu;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

import static com.stevenhu.BoardLogic.HEIGHT;
import static com.stevenhu.BoardLogic.WIDTH;

/**
 * Created by Steven Hu on 2016-08-16.
 */
public class Player {
	public int x;
	public int y;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public static boolean movePlayer(Player player, Terminal terminal) throws InterruptedException {
		
		Key key;
		do {
			Thread.sleep(5);
			key = terminal.readInput();
		}
		while (key == null);
		
		switch (key.getCharacter() + " " + key.getKind()) {
			case "D ArrowDown":
			case "s NormalKey":
				if (player.y < HEIGHT + 1) {
					player.y += 1;
				}
				break;
			case "U ArrowUp":
			case "w NormalKey":
				if (player.y > 1) {
					player.y -= 1;
				}
				break;
			case "R ArrowRight":
			case "d NormalKey":
				if (player.x < WIDTH + 1) {
					player.x += 1;
				}
				break;
			case "L ArrowLeft":
			case "a NormalKey":
				if (player.x > 1) {
					player.x -= 1;
				}
				break;
			default:
				return false;
				
			
			
			
		}
		return true;
		

	}
}
