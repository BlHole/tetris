package com.huaxu.ui.window;

import com.huaxu.config.FrameConfig;
import com.huaxu.config.GameConfig;
import com.huaxu.config.LayerConfig;
import com.huaxu.control.GameControl;
import com.huaxu.control.PlayerControl;
import com.huaxu.dto.GameDto;
import com.huaxu.ui.imgge.Img;
import com.huaxu.ui.Layer;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

public class JPanelGame extends JPanel {

	private static final int BIN_SIZE_W = GameConfig.getFrameConfig()
			.getButtonConfig().getButtonW();

	private static final int BIN_SIZE_H = GameConfig.getFrameConfig()
			.getButtonConfig().getButtonH();

	private List<Layer> layers = null;

	private JButton btnStart;

	private JButton btnConfig;

	private GameControl gameControl = null;

	public JPanelGame(GameControl gameControl, GameDto dto) {
		// 连接游戏控制器
		this.gameControl = gameControl;
		// 设置布局管理器为自由布局
		this.setLayout(null);
		// 初始化组件
		this.initComponent();
		// 初始化层
		this.initLayer(dto);
		// 安装键盘监听器
		this.addKeyListener(new PlayerControl(gameControl));
	}

	/*
	 * 安装玩家控制器
	 */
	public void setGameControl(PlayerControl control) {
		this.addKeyListener(control);
	}

	/*
	 * 初始化组件
	 */
	private void initComponent() {
		// 初始化开始按钮
		this.btnStart = new JButton(Img.BIN_START);
		// 设置开始按钮位置
		btnStart.setBounds(GameConfig.getFrameConfig().getButtonConfig()
				.getStartX(), GameConfig.getFrameConfig().getButtonConfig()
				.getStartY(), BIN_SIZE_W, BIN_SIZE_H);
		//给开始按钮增加事件监听
		this.btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameControl.start();
			}
		});
		// 添加按钮到面板
		this.add(btnStart);
		// 初始化设置按钮
		this.btnConfig = new JButton(Img.BIN_CONFIG);
		// 设置设置按钮位置
		this.btnConfig.setBounds(GameConfig.getFrameConfig().getButtonConfig()
				.getUserConfigX(), GameConfig.getFrameConfig()
				.getButtonConfig().getUserConfigY(), BIN_SIZE_W, BIN_SIZE_H);
		//给设置按钮增加事件监听
		this.btnConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameControl.showUserConfig();
			}
		});
		// 添加按钮到面板
		this.add(btnConfig);
	}

	/*
	 * 初始化层
	 */
	private void initLayer(GameDto dto) {
		try {
			// 获得游戏配置
			FrameConfig fCfg = GameConfig.getFrameConfig();
			// 获得层配置
			List<LayerConfig> LayersCfg = fCfg.getLayersConfig();
			// 创建游戏层数组
			layers = new ArrayList<Layer>(LayersCfg.size());
			// 创建所有层对象
			for (LayerConfig LayerCfg : LayersCfg) {
				// 获得类对象
				Class<?> cls = Class.forName(LayerCfg.getClassName());
				// 获得构造函数
				Constructor<?> ctr = cls.getConstructor(int.class, int.class,
						int.class, int.class);
				// 调用构造函数创建对象
				Layer layer = (Layer) ctr.newInstance(LayerCfg.getX(),
						LayerCfg.getY(), LayerCfg.getW(), LayerCfg.getH());
				// 设置游戏数据对象
				layer.setDto(dto);
				// 吧创建的Layer对象放入集合
				layers.add(layer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		// 调用基类方法
		super.paintComponent(g);
		// 绘制游戏界面
		for (int i = 0; i < layers.size(); layers.get(i++).paint(g))
			;
		// 返回焦点
		this.requestFocus();
	}
	/*
	 * 控制按钮是否可点击
	 */
	public void buttonSwitch(boolean onOff){
		this.btnConfig.setEnabled(onOff);
		this.btnStart.setEnabled(onOff);
	}
}
