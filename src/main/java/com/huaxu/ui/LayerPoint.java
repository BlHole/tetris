package com.huaxu.ui;


import com.huaxu.config.GameConfig;
import com.huaxu.ui.imgge.Img;

import java.awt.*;

public class LayerPoint extends Layer {

	/*
	 * 分数最大位数
	 */
	private static final int POINT_BIT = 5;
	/*
	 * 升级行数
	 */
	private static final int LEVEL_UP = GameConfig.getSystemConfig().getLevelUp();
	/*
	 * 消行y坐标
	 */
	private final int rmLineY;
	/*
	 * 分数y坐标
	 */
	private final int pointY;
	/*
	 * 消行x坐标
	 */
	private final int comX;
	/*
	 * 经验值y坐标
	 */
	private final int expY;

	public LayerPoint(int x, int y, int w, int h) {
		super(x, y, w, h);
		//初始化共通的x坐标
		this.comX = this.w - IMG_NUMBER_W * POINT_BIT - PADDING;
		//分数显示的y坐标
		this.pointY = PADDING;
		//初始化消行显示 的y坐标
		this.rmLineY = this.pointY + Img.POINT.getHeight(null)+ PADDING;
		//初始化经验值显示的y坐标0
		this.expY = this.rmLineY + Img.RMLINE.getHeight(null) + PADDING;
	}

	/* (non-Javadoc)
	 * @see ui.Layer#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
		this.creaWindow(g);
		// 窗口标题(分数)
		g.drawImage(Img.POINT, this.x + PADDING, this.y + pointY, null);
		// 显示分数
		this.drawNumberLeftPad(comX, pointY, this.dto.getNowPoint(), POINT_BIT, g);
		// 窗口标题(消行)
		g.drawImage(Img.RMLINE, this.x + PADDING, this.y + rmLineY, null);
		// 显示消行
		this.drawNumberLeftPad(comX, rmLineY, this.dto.getNowRemoveLine(), POINT_BIT, g);
		//绘制值槽
		int rmline = this.dto.getNowRemoveLine();

		this.drawRect(expY , "下一级",null,(double)(rmline % LEVEL_UP)/(double)LEVEL_UP,g);
	}


	@Deprecated
	private Color getNowColor(double hp, double maxHp){
		int  colorR = 0;
		int  colorG = 255;
		int  colorB = 0;
		double hpHalf = maxHp / 2;
		if(hp > hpHalf){
			colorR = 255 - (int)((hp - hpHalf) / (maxHp / 2) * 255);
			colorG = 255;
		}else{
			colorR = 255;
			colorG = (int)(hp / (maxHp / 2) * 255);
		}
		return new Color(colorR , colorG , colorB);
	}
}
