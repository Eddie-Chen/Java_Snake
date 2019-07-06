package com.snake.controller;

//控制器:負責處理各組件的變化
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

import com.snake.entities.Food;
import com.snake.entities.Ground;
import com.snake.entities.Snake;
import com.snake.listener.SnakeListener;
import com.snake.view.BottonPanel;
import com.snake.view.GameMenu;
import com.snake.view.GamePanel;

/*繼承 KeyAdapter鍵盤按鍵事件，跟SnakeListener介面*/
public class Controller extends KeyAdapter implements SnakeListener{
	private Snake snake; /*蛇*/
	private Food food;	/*食物*/
	private Ground ground; /*地形*/
	/*視窗介面*/
	private GamePanel gamePanel; 
	private GameMenu gameMenu;
	private BottonPanel bottonPanel;
	
	/*建構子*/
	public Controller(Snake snake, Food food, Ground ground,GamePanel gamePanel,GameMenu gameMenu,BottonPanel bottonPanel) {
		this.snake = snake;
		this.food = food;
		this.ground = ground;
		this.gamePanel = gamePanel;
		this.gameMenu = gameMenu;
		this.bottonPanel = bottonPanel;
		
		init();
	}
	
	
	//初始化控制器，並對控制器的監聽
	public void init() {
		/*取得BottonPanel的各個方法，並增加ActionListener進行按鈕事件的處理*/
		bottonPanel.getStartButton().addActionListener(new startHandler());
		bottonPanel.getPauseButton().addActionListener(new pauseHandler());
		bottonPanel.getEndButton().addActionListener(new endHandler());
		/*取得GameMenu的 設定顏色 方法，並增加ActionListener進行按鈕事件的處理*/
		gameMenu.getItem1().addActionListener(new Item1Handler());
		gameMenu.getItem2().addActionListener(new Item2Handler());
		gameMenu.getItem3().addActionListener(new Item3Handler());
		gameMenu.getItem4().addActionListener(new Item4Handler());
		/*取得GameMenu的 設定速度 方法，並增加ActionListener進行按鈕事件的處理*/
		gameMenu.getSpItem1().addActionListener(new spItem1Handler());
		gameMenu.getSpItem2().addActionListener(new spItem2Handler());
		gameMenu.getSpItem3().addActionListener(new spItem3Handler());
		gameMenu.getSpItem4().addActionListener(new spItem4Handler());
		/*取得GameMenu的 設定地圖 方法，並增加ActionListener進行按鈕事件的處理*/
		gameMenu.getMapItem1().addActionListener(new mapItem1Handler());
		gameMenu.getMapItem2().addActionListener(new mapItem2Handler());
		gameMenu.getMapItem3().addActionListener(new mapItem3Handler());
		/*取得GameMenu的 幫助 方法，並增加ActionListener進行按鈕事件的處理*/
		gameMenu.getAbItem().addActionListener(new abortItemHandler());
		
		
		bottonPanel.getStartButton().setEnabled(true);
	}

	//取得各個方法 get/set
	public Snake getSnake() {
		return snake;
	}
	public Ground getGround() {
		return ground;
	}
	public GamePanel getGamePanel() {
		return gamePanel;
	}
	public GameMenu getGameMenu() {
		return gameMenu;
	}
	public BottonPanel getBottonPanel() {
		return bottonPanel;
	}
	
	
	//鍵盤的監聽
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:	/*上*/		
				snake.changeDirection(Snake.UP);
				break;
			case KeyEvent.VK_DOWN:	/*下*/				
				snake.changeDirection(Snake.DOWN);
				break;
			case KeyEvent.VK_LEFT:	/*左*/
				snake.changeDirection(Snake.LEFT);
				break;
			case KeyEvent.VK_RIGHT:	/*右*/
				snake.changeDirection(Snake.RIGHT);
				break;
			case KeyEvent.VK_W:	/*加速*/
				snake.speedUp();
				break;
			case KeyEvent.VK_S:	/*減速*/
				snake.speedDown();
				break;
			default:
				break;
		}
	}

	
	//作為蛇移動接口，處理蛇移動過程中發生的各種狀況
	@Override
	public void snakeMoved(Snake snake) {
		//每次移動，就更新一次面板
		gamePanel.display(snake, food, ground);			
		
		/*如果蛇吃到食物*/
		if(food.isFoodEated(snake)) {
			snake.eatFood();
			food.newFood(ground.getPoint());
			
			//更新得分分數
			bottonPanel.repaint();
			setScore();		
		}
		/*蛇碰到牆壁*/
		if(ground.isGroundEated(snake)) {
			snake.die();
			bottonPanel.getStartButton().setEnabled(true);
		}
		/*蛇碰到身體*/
		if(snake.isEatBody()) {
			snake.die();
			bottonPanel.getStartButton().setEnabled(true);
		}
	}
	
	
	//設定分數
	public void setScore() {
		int score = snake.getFoodCount() ;
		bottonPanel.setScore(score);
	}
	

	
	// 開始新遊戲
	public void newGame() {
		ground.clear();
		
		/*地形選擇*/
		switch (ground.getMapType()) {
			case 1:
				ground.generateRocks1();
				break;
			case 2:
				ground.generateRocks2();
				break;
			case 3:
				ground.generateRocks3();
				break;
		}
		
		
		food.newFood(ground.getPoint());	/*為新地形設定食物放置位子*/
		bottonPanel.setScore(0);	/*分數重新設定*/	
		
		bottonPanel.repaint();
	}

	

	
	//開始遊戲的按鈕監聽
	class startHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {	
			gamePanel.requestFocus(true);	//!!遊戲面板獲得焦點
			snake.clear();
			snake.init();		
			snake.begin();
			newGame();
			bottonPanel.getStartButton().setEnabled(false);
		}
		
	}
	
	
	//暫停按鈕的按鈕監聽
	class pauseHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			gamePanel.requestFocus(true);
			snake.changePause();

			if(e.getActionCommand()=="暫停遊戲")/*假如遊戲是暫停遊戲*/
				bottonPanel.getPauseButton().setText("繼續遊戲");/*按鈕就顯示 繼續遊戲*/
			else {
				bottonPanel.getPauseButton().setText("暫停遊戲");/*不是暫停就顯示 暫停遊戲*/
			}
		}
	}
	

	//結束遊戲的按鈕監聽
	class endHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			snake.die(); /*蛇死亡就結束遊戲*/
			bottonPanel.getStartButton().setEnabled(true);/*開始遊戲按鈕就啟動*/
		}
	}
	
	
	//菜单栏各按钮的监听
	class Item1Handler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Color color = JColorChooser.showDialog(gamePanel, "请选择颜色", Color.BLACK);	
			gamePanel.backgroundColor = color;
		}
	}
	
	class Item2Handler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Color color = JColorChooser.showDialog(gamePanel, "请选择颜色", Color.BLACK);	
			food.setFoodColor(color);
		}
	}
	
	
	class Item3Handler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Color color = JColorChooser.showDialog(gamePanel, "请选择颜色", Color.BLACK);	
			snake.setHeadColor(color);
		}
	}
	
	class Item4Handler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Color color = JColorChooser.showDialog(gamePanel, "请选择颜色", Color.BLACK);	
			snake.setBodyColor(color);
		}
	}
	
	class spItem1Handler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			snake.setSleepTime(600);
		}
	}
	
	class spItem2Handler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			snake.setSleepTime(350);
		}	
	}
	
	
	class spItem3Handler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			snake.setSleepTime(150);
		}	
	}
	
	class spItem4Handler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			snake.setSleepTime(80);
		}	
	}
	
	
	class mapItem1Handler	implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			ground.setMapType(1);
		}
	}
	
	class mapItem2Handler	implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			ground.setMapType(2);
			
		}
		
	}
	
	class mapItem3Handler	implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			ground.setMapType(3);
			
		}
		
	}
	
	/*幫助*/
	class abortItemHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {/*使用說明*/
			StringBuffer sb= new StringBuffer(); /*字串*/
			sb.append("方向控制\n");
			sb.append("上鍵、下鍵、左鍵、右鍵、w(加速)、s(減速)\n");
			sb.append("Eddie_Chen開發練習\n");
			String message = sb.toString();
			JOptionPane.showMessageDialog(null, message, "使用說明",JOptionPane.INFORMATION_MESSAGE);
		}
	}
}

