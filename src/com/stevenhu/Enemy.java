package com.stevenhu;

import java.util.Random;

import static com.stevenhu.BoardLogic.HEIGHT;
import static com.stevenhu.BoardLogic.WIDTH;

/**
 * Created by Steven Hu on 2016-08-16.
 */
public class Enemy {
	public int x;
	public int y;
	
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
	public static Enemy[] createEnemies(int numberOfEnemies) {
		Enemy[] enemies = new Enemy[numberOfEnemies];
		Random rand = new Random();
		for (int i = 0; i < enemies.length; i++) {
//			Spawn enemies at random locations
//			TODO: make locations unique
			enemies[i] = new Enemy(rand.nextInt(WIDTH) + 1, rand.nextInt(HEIGHT) + 1);
		}
		return enemies;
	}
	
	public static void enemyLogic(Player player, Enemy[] enemy, int[][] board) {
		
		for (int i = 0; i < enemy.length; i++) {
			int dx = player.x - enemy[i].x;
			int dy = player.y - enemy[i].y;
			
			if (Math.abs(dy) > 0) {
				if (dy < 0 && (board[enemy[i].x][enemy[i].y - 1] == 0 || board[enemy[i].x][enemy[i].y - 1] == 1)) {
					board[enemy[i].x][enemy[i].y] = 0;
					enemy[i].y -= 1;
					board[enemy[i].x][enemy[i].y] = 2;
				} else if (dy > 0 && (board[enemy[i].x][enemy[i].y + 1] == 0 || board[enemy[i].x][enemy[i].y + 1] == 1)) {
					board[enemy[i].x][enemy[i].y] = 0;
					enemy[i].y += 1;
					board[enemy[i].x][enemy[i].y] = 2;
				}
			} else if (Math.abs(dx) > 0) {
				if (dx < 0 && (board[enemy[i].x - 1][enemy[i].y] == 0 || board[enemy[i].x - 1][enemy[i].y] == 1)) {
					board[enemy[i].x][enemy[i].y] = 0;
					enemy[i].x -= 1;
					board[enemy[i].x][enemy[i].y] = 2;
				} else if (dx > 0 && (board[enemy[i].x + 1][enemy[i].y] == 0 || board[enemy[i].x + 1][enemy[i].y] == 1)) {
					board[enemy[i].x][enemy[i].y] = 0;
					enemy[i].x += 1;
					board[enemy[i].x][enemy[i].y] = 2;
				}
			}
		}
		
		
	}
}
