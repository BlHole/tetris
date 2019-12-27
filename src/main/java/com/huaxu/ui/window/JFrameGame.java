package com.huaxu.ui.window;

import com.huaxu.config.FrameConfig;
import com.huaxu.config.GameConfig;
import com.huaxu.util.FrameUtil;

import javax.swing.JFrame;



public class JFrameGame extends JFrame{

	public JFrameGame(JPanelGame panelGame){
		//获得游戏配置
		FrameConfig fCfg = GameConfig.getFrameConfig();
		//设置标题
		this.setTitle(fCfg.getTitle());
		//设置默认关闭属性(程序结束)
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置窗口大小
		this.setSize(fCfg.getWidth(), fCfg.getHeight());
		//不允许用户改变窗口大小
		this.setResizable(false);
		//居中
		FrameUtil.setFrameCenter(this);
		//设置默认Panel
		this.setContentPane(panelGame);
		/*
		 * 默认窗口为显示
		 */
		this.setVisible(true);
	}
}
