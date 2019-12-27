package com.huaxu.ui;

import com.huaxu.ui.imgge.Img;

import java.awt.Graphics;

public class LayerNext extends Layer {

	public LayerNext(int x, int y, int w, int h) {
		super(x, y, w, h);

	}
	public void paint(Graphics g){
		this.creaWindow(g);
		//如果是开始状态,绘制下一个方块
		if(this.dto.isStart()){
			this.drawImageOfCenter(Img.NEXT_ACT[this.dto.getNext()], g);
		}
	}
}