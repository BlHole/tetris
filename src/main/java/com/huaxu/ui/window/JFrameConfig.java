package com.huaxu.ui.window;

import com.huaxu.control.GameControl;
import com.huaxu.util.FileUtil;
import com.huaxu.util.FrameUtil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class JFrameConfig extends JFrame{
	private JButton btnOk = new JButton("确定");

	private JButton btnCancel = new JButton("取消");

	private JButton btnUser = new JButton("应用");

	private GameControl gameControl;

	private TextCtrl[] keyText = new TextCtrl[8];

	private JLabel errorMsg = new JLabel();

	private final static Image IMG_PSP = new ImageIcon("data/psp.png").getImage();

	private final static String[] METHOD_NAMES = {
			"keyRight","keyUp","keyLeft","keyDown",
			"keyFunRight","keyFunUp","keyFunLeft","keyFunDown"
	};

	private final static String PATH = "data/control.dat";

	public JFrameConfig(GameControl gameControl){
		//获得游戏控制器对象
		this.gameControl = gameControl;
		//设置布局管理器为"边界布局"
		this.setLayout(new BorderLayout());
		this.setTitle("设置");
		//初始化按键输入框
		this.initkeyText();
		//添加主面板
		this.add(createMainPanel(),BorderLayout.CENTER);
		//添加按钮面板
		this.add(this.createButtonPanel(),BorderLayout.SOUTH);
		//设置窗口大小
		this.setSize(670,320);
		//居中
		FrameUtil.setFrameCenter(this);
	}
	/*
	 * 初始化按键输入框
	 */
	private void initkeyText() {
		int x = 0;
		int y = 40;
		int w = 50;
		int h = 20;
		for (int j = 0; j < 4; j++) {
			keyText[j] = new TextCtrl(x, y, w, h,METHOD_NAMES[j]);
			y += 34;
		}
		x = 600;
		y = 45;
		for (int j = 4; j < 8; j++) {
			keyText[j] = new TextCtrl(x, y, w, h,METHOD_NAMES[j]);
			y += 34;
		}
		try {
			ObjectInputStream ois = new ObjectInputStream(FileUtil.getInputStream(PATH));
			HashMap<Integer, String> cfgSet = (HashMap<Integer, String>)ois.readObject();
			ois.close();
			Set<Entry<Integer,String>> entryset = cfgSet.entrySet();
			for (Entry<Integer,String> e : entryset){
				for (TextCtrl tc : keyText) {
					if(tc.getMethodName().equals(e.getValue())){
						tc.setKeyCode(e.getKey());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * 创建按钮面板
	 */
	private JPanel createButtonPanel() {
		//创建按钮面板，流式布局
		JPanel jp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		//给确定按钮增加事件监听
		this.btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(writeConfig()){
					setVisible(false);
					gameControl.setOver();
				}
			}
		});
		this.errorMsg.setForeground(Color.red);
		jp.add(this.errorMsg);
		jp.add(this.btnOk);
		this.btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				gameControl.setOver();
			}
		});
		jp.add(this.btnCancel);
		this.btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeConfig();
			}
		});
		jp.add(this.btnUser);

		return jp;
	}
	/*
	 * 创建主面板(选项卡面板)
	 */
	private JTabbedPane createMainPanel() {

		JTabbedPane jtp = new JTabbedPane();
		jtp.addTab("控制设置", this.createContralPanel());


		return jtp;
	}
	/*
	 * 玩家控制设置面板
	 */
	private JPanel createContralPanel() {
		JPanel jp = new JPanel(){
			public void paintComponent(Graphics g){
				g.drawImage(IMG_PSP, 50, 0, null);
			}
		};
		//设置布局管理器
		jp.setLayout(null);
		for (int i = 0; i < keyText.length; i++) {
			jp.add(keyText[i]);
		}
		return jp;
	}

	/*
	 * 写入游戏配置
	 */
	private boolean writeConfig(){
		HashMap<Integer, String> keySet = new HashMap<Integer,String>();
		for (int i = 0; i < this.keyText.length; i++) {
			int keyCode = this.keyText[i].getKeyCode();
			if(keyCode == 0) {
				this.errorMsg.setText("错误按键");
				return false;
			}
			keySet.put(keyCode, this.keyText[i].getMethodName());
		}
		if(keySet.size() != 8){
			this.errorMsg.setText("重复按键");
			return false;
		}
		try {
			ObjectOutputStream oos =  new ObjectOutputStream(FileUtil.getOutputStream(PATH));
			oos.writeObject(keySet);
			oos.close();
		} catch (Exception e) {
			this.errorMsg.setText(e.getMessage());
			return false;
		}
		this.errorMsg.setText(null);
		return true;
	}
}