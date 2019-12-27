package com.huaxu.ui;

import com.huaxu.ui.imgge.Img;

import java.awt.Graphics;

public class LayerDataBase extends LayerData {

	public LayerDataBase(int x, int y, int w, int h) {
		super(x, y, w, h);
		
	}
	public void paint(Graphics g){
		this.creaWindow(g);
		this.showData(Img.DB, this.dto.getDbRecode(), g);
	}
	
}
