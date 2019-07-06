package com.snake.view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GameMenu extends JMenuBar{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenu colorMenu,speeceMenu,mapMenu,abortMenu;
	private JMenuItem item1,item2,item3,item4;
	private JMenuItem spItem1,spItem2,spItem3,spItem4;
	private JMenuItem mapItem1,mapItem2,mapItem3;
	private JMenuItem abItem;
	
	
	public JMenu getColorMenu() {
		return colorMenu;
	}

	public JMenu getSpeeceMenu() {
		return speeceMenu;
	}

	public JMenu getMapMenu() {
		return mapMenu;
	}

	public JMenu getAbortMenu() {
		return abortMenu;
	}



	public JMenuItem getItem1() {
		return item1;
	}

	public JMenuItem getItem2() {
		return item2;
	}

	public JMenuItem getItem3() {
		return item3;
	}

	public JMenuItem getItem4() {
		return item4;
	}



	public JMenuItem getSpItem1() {
		return spItem1;
	}

	public JMenuItem getSpItem2() {
		return spItem2;
	}

	public JMenuItem getSpItem3() {
		return spItem3;
	}

	public JMenuItem getSpItem4() {
		return spItem4;
	}



	public JMenuItem getMapItem1() {
		return mapItem1;
	}

	public JMenuItem getMapItem2() {
		return mapItem2;
	}

	public JMenuItem getMapItem3() {
		return mapItem3;
	}


	public JMenuItem getAbItem() {
		return abItem;
	}


	public GameMenu() {
	
		colorMenu = new JMenu("設定顏色");
		add(colorMenu);
		
		speeceMenu = new JMenu("設定速度");
		add(speeceMenu);
		
		mapMenu = new JMenu("地圖");
		add(mapMenu);
		
		abortMenu = new JMenu("幫助");
		add(abortMenu);
		
		item1 = new JMenuItem("背景颜色");
		item2 = new JMenuItem("食物颜色");
		item3 = new JMenuItem("蛇头颜色");
		item4 = new JMenuItem("蛇身颜色");
		
		colorMenu.add(item1);
		colorMenu.add(item2);
		colorMenu.add(item3);
		colorMenu.add(item4);
		
		
		spItem1 = new JMenuItem("烏龜");
		spItem2 = new JMenuItem("兔子");
		spItem3 = new JMenuItem("斑馬");
		spItem4 = new JMenuItem("獵豹");
		
		speeceMenu.add(spItem1);
		speeceMenu.add(spItem2);
		speeceMenu.add(spItem3);
		speeceMenu.add(spItem4);
		
		
		mapItem1 = new JMenuItem("地圖一");
		mapItem2 = new JMenuItem("地圖二");
		mapItem3 = new JMenuItem("地圖三");
		
		mapMenu.add(mapItem1);
		mapMenu.add(mapItem2);
		mapMenu.add(mapItem3);
		
		//String s = "huowolf开发http://blog.csdn.net/huolang_vip";
		abItem = new JMenuItem("使用說明");
		abortMenu.add(abItem);
	}
	
}
