package com.huaxu.ui;

import com.huaxu.config.GameConfig;
import com.huaxu.dto.Player;
import com.huaxu.ui.imgge.Img;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

public abstract class LayerData extends Layer {
	/*
	 * 最大数据行
	 */
	private static final int MAX_ROW = GameConfig.getDataConfig().getMaxRow();
	/*
	 * 起始Y坐标
	 */
	private static int START_Y = 0;
	/*
	 *
	 */
	private static final int RECT_H = IMG_RECT_H + 4;
	/*
	 * 间距
	 */
	private static int SPA = 0;

	public LayerData(int x, int y, int w, int h) {
		super(x, y, w, h);
		SPA = (this.h - RECT_H * 5 - (PADDING << 1) - Img.DB.getHeight(null))
				/ MAX_ROW;
		START_Y = PADDING + Img.DB.getHeight(null) + SPA;
	}
	/*
	 * 绘制该窗口所有值槽
	 * 标题图片
	 * 数据源
	 */
	public void showData(Image imgTitle, List<Player> players, Graphics g) {
		//绘制标题
		g.drawImage(imgTitle, this.x + PADDING, this.y + PADDING, null);
		int nowPoint = this.dto.getNowPoint();
		//循环绘制记录
		for (int i = 0; i < MAX_ROW; i++) {
			//获得一条玩家记录
			Player pla = players.get(i);
			//获得该分数
			int recodePoint = pla.getPoint();
			//计算现在分数与记录分数比值
			double percent = (double) nowPoint / recodePoint;
			//如果以破记录，比值设为100%
			percent = percent > 1 ? 1.0 : percent;
			//绘制单条记录
			String strPoint = recodePoint == 0 ? null : Integer.toString(recodePoint);
			this.drawRect(START_Y + i * (RECT_H + SPA), pla.getName(), strPoint, percent, g);
		}
	}
	abstract public void paint(Graphics g);
}
