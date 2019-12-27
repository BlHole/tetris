package com.huaxu.util;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class FrameUtil {
	/*
	 * 窗口居中
	 */
	public static void setFrameCenter(JFrame jf){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		int  x = screen.width - jf.getWidth() >> 1;
		int  y = (screen.height - jf.getHeight() >> 1) - 32;
		jf.setLocation(x, y);

	}
}
