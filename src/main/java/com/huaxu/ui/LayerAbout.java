package com.huaxu.ui;

import com.huaxu.ui.imgge.Img;

import java.awt.Graphics;

public class LayerAbout extends Layer {

	public LayerAbout(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	public void paint(Graphics g){
		this.creaWindow(g);
		this.drawImageOfCenter(Img.SIGN,g);
	}

}
