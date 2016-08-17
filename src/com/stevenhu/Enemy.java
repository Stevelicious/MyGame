package com.stevenhu;

import java.util.Random;

import static com.stevenhu.Main.HEIGHT;
import static com.stevenhu.Main.WIDTH;

/**
 * Created by Steven Hu on 2016-08-16.
 */
public class Enemy {
	public int x;
	public int y;
	
	public Enemy(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	
	
	public static Enemy[] createNEnemies(int n){
		Enemy[] enemies = new Enemy[n];
		Random rand = new Random();
		for (int i = 0; i < enemies.length; i++) {
//			Spawn enemies at random locations
//			TODO: make locations unique
			enemies[i] = new Enemy(rand.nextInt(WIDTH)+1, rand.nextInt(HEIGHT)+1);
		}
		return enemies;
	}
	
	public static void enemyLogic(Player player, Enemy[] enemy) {
		
		for (int i = 0; i < enemy.length; i++) {
			int dx = player.x - enemy[i].x;
			int dy = player.y - enemy[i].y;
			
			if (Math.abs(dx) <= Math.abs(dy)) {
				if (dy < 0) {
					enemy[i].y -= 1;
				} else if (dy > 0){
					enemy[i].y += 1;
				}
			} else {
				if (dx < 0) {
					enemy[i].x -= 1;
				} else if (dx > 0){
					enemy[i].x += 1;
				}
			}
		}
		
		
	}
}
