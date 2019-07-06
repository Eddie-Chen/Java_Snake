package com.snake.game;


import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.snake.controller.Controller;
import com.snake.entities.Food;
import com.snake.entities.Ground;
import com.snake.entities.Snake;
import com.snake.util.Global;
import com.snake.view.BottonPanel;
import com.snake.view.GameMenu;
import com.snake.view.GamePanel;

public class GameFrame extends JFrame {

	/*serialVersionUID用來表明類的不同版本間的兼容性。
	 * 如果你修改了此類,要修改此值。否則以前用老版本的類序列化的類恢復時會出錯。
	 * 為了在反序列化時，確保類版本的兼容性，
	 * 最好在每個要序列化的類中加入private static final long serialVersionUID這個屬性，
	 * 具體數值自己定義。*/
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		//GameFrame 實作 Controller。
		//Controller 實作 Snake, Food, Ground, GamePanel, GameMenu, BottonPanel.
		new GameFrame(new Controller(new Snake(), new Food(), new Ground(), 
				new GamePanel(), new GameMenu(),new BottonPanel()));

	}

	
	//各對象
	private GamePanel gamePanel;
	private GameMenu gameMenu;
	private Snake snake;
	//private Food food;
	//private Ground ground;
	private Controller controller;	
	private JPanel buttonPanel;

	
	/*建構子*/
	public GameFrame(Controller c) {
		this.controller = c;
		snake = controller.getSnake();
		gameMenu = controller.getGameMenu();
		gamePanel = controller.getGamePanel();
		buttonPanel = controller.getBottonPanel();
		
		setTitle("貪食蛇");
		setBounds(300,100,Global.WIDTH*Global.CELL_SIZE+250,Global.HEIGHT*Global.CELL_SIZE+60);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = this.getContentPane(); 		
		this.setJMenuBar(gameMenu);
		
		contentPane.add(gamePanel);
		contentPane.add(buttonPanel);
		
		setResizable(false);//不可調整尺寸
		setVisible(true);

		
		/* 使窗口居中 */
		this.setLocation(
				this.getToolkit().getScreenSize().width / 2 - this.getWidth() / 2, 
				this.getToolkit().getScreenSize().height / 2 - this.getHeight() / 2);
		
	
		gamePanel.addKeyListener(controller);
		snake.addSnakeListener(controller);	
		controller.newGame();
		
		
	}
	
}
