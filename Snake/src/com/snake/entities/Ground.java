package com.snake.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import com.snake.util.Global;

/*地形*/
public class Ground {
	private boolean [][] rocks = new boolean[Global.WIDTH][Global.HEIGHT];
	private int mapType = 1;
	
	public int getMapType() {
		return mapType;
	}

	public void setMapType(int mapType) {
		this.mapType = mapType;
	}
	
	
	//初始化地形(清空石頭)	 
	public void clear() {
		for (int x = 0; x < Global.WIDTH; x++)
			for (int y = 0; y < Global.HEIGHT; y++)
				rocks[x][y] = false;
	}
	
	/**
	 * 產生石頭, 覆蓋方法並改變石頭在地面上的布局
	 * */
	/*地形一*/
	public void generateRocks1() {
		/*x軸的for迴圈*/
		for (int x = 0; x < Global.WIDTH; x++)
			rocks[x][0] = rocks[x][Global.HEIGHT - 1] = true;
		/*y軸的for迴圈*/
		for (int y = 0; y < Global.HEIGHT; y++)
			rocks[0][y] = rocks[Global.WIDTH - 1][y] = true;
	}
	
	/*地形二*/
	public void generateRocks2() {
		/*x軸的for迴圈*/
		for (int y = 0; y < 6; y++) { /*高度不大於6;位置在四個角*/
			rocks[0][y] = true;/*左上角;1,2,3 遞增往右移動*/
			rocks[Global.WIDTH - 1][y] = true; /*右上角; -1,-2,-3 遞減往左移動*/
			rocks[0][Global.HEIGHT - 1 - y] = true;/*左下角,-1,-2,-3遞減往上移動*/
			rocks[Global.WIDTH - 1][Global.HEIGHT - 1 - y] = true;
		}
		/*y軸的for迴圈*/
		for (int y = 6; y < Global.HEIGHT - 6; y++) { /*總高度-6=高度;位置在中間*/
			rocks[6][y] = true; /*中間左邊*/
			rocks[Global.WIDTH - 7][y] = true;/*中間右邊*/
		}
	}
	
	/*地形三*/
	public void generateRocks3() {
		for(int x=4;x<14;x++) {
			rocks[x][5] = true;
		}	
		for(int j=5;j<15;j++) {
			rocks[21][j] = true;
		}
		for(int y=13;y<20;y++) {
			rocks[14][y] = true;
		}
		for(int x=2;x<10;x++) {
			rocks[x][17] = true;
		}
		for(int i=10;i<Global.WIDTH-3;i++) {
			rocks[i][Global.HEIGHT-3] = true;
		}
	}
	
	//蛇是否吃到了石头
	public boolean isGroundEated(Snake snake) {
		for(int x=0; x<Global.WIDTH;x++) {
			for(int y=0; y<Global.HEIGHT;y++) {
				if(rocks[x][y] == true &&(x==snake.getHead().x &&y==snake.getHead().y)) {
					return true;
				}
			}
		}
		return false;	
	}
	
	//取得食物位置
	public Point getPoint() {
		Random random = new Random();
		int x=0,y=0;
		do {
			x = random.nextInt(Global.WIDTH);
			y = random.nextInt(Global.HEIGHT);
		} while (rocks[x][y]==true);

		return new Point(x,y);
	}
	
	
	//顯示方法
	public void drawMe(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		
		for(int x=0; x<Global.WIDTH;x++) {
			for(int y=0; y<Global.HEIGHT;y++) {
				if(rocks[x][y] == true) {
					g.fill3DRect(x*Global.CELL_SIZE, y*Global.CELL_SIZE, 
							Global.CELL_SIZE,Global.CELL_SIZE, true);
				}
			}
		}
	}
}
