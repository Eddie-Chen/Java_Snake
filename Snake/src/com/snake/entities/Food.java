package com.snake.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import com.snake.util.Global;

/*繼承 awt.java.point介面*/
public class Food extends Point{

	private static final long serialVersionUID = 1L;
	/*serialVersionUID用來表明類的不同版本間的兼容性。
	 * 如果你修改了此類,要修改此值。否則以前用老版本的類序列化的類恢復時會出錯。
	 * 為了在反序列化時，確保類版本的兼容性，
	 * 最好在每個要序列化的類中加入private static final long serialVersionUID這個屬性，
	 * 具體數值自己定義。*/
	
	private Color foodColor;/*食物顏色*/
		
	/*設定set/get*/
	public void setFoodColor(Color foodColor) {
		this.foodColor = foodColor;
	}

	public Color getFoodColor() {
		return foodColor;
	}

	/*新食物*/
	public void newFood(Point p) {
		setLocation(p);
	}
	
	/*食物是否與蛇頭位子相等*/
	public boolean isFoodEated(Snake snake) {			
		return 	this.equals(snake.getHead());
	}
	
	/*繪製顏色*/
	public void drawMe(Graphics g) {
		if(foodColor==null) {
			g.setColor(Color.RED);/*預設值紅色*/
		}else {
			g.setColor(foodColor);/*或者更改顏色*/
		}
		
		g.fill3DRect(x*Global.CELL_SIZE, y*Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
	}
}
