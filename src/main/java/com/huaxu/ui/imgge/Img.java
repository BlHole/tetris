package com.huaxu.ui.imgge;

import com.huaxu.config.GameConfig;
import com.huaxu.util.FileUtil;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;



public class Img {

	private Img(){}
	/*
	 * 个人签名
	 */
	public static Image SIGN = new ImageIconDiy("graphics/string/sign.png").getImage();
	/*
	 * 阴影
	 */
	public static Image SHODOW = new ImageIconDiy("graphics/game/shodow.png").getImage();
	/*
	 * 窗口图片
	 */
	public static Image WINDOW = new ImageIconDiy("graphics/window/Window.png").getImage();
	/*
	 * 矩形值槽
	 */
	public static Image RECT = new ImageIconDiy("graphics/window/rect.jpg").getImage();
	/*
	 * 数字图片 尺寸：260 36
	 */
	public static Image NUMBER = new ImageIconDiy("graphics/string/num.png").getImage();
	/*
	 * 数据库窗口标题
	 */
	public static Image DB = new ImageIconDiy("graphics/string/db.png").getImage();
	/*
	 * 本地记录窗口标题
	 */
	public static Image DISK = new ImageIconDiy("graphics/string/disk.png").getImage();
	/*
	 * 方块图片
	 */
	public static Image ACT = new ImageIconDiy("graphics/game/rect.jpg").getImage();
	/*
	 * 标题图片
	 */
	public static Image LEVEL = new ImageIconDiy("graphics/string/level.png").getImage();
	/*
	 * 标题图片(分数)
	 */
	public static Image POINT = new ImageIconDiy("graphics/string/point.png").getImage();
	/*
	 * 标题图片(消行)
	 */
	public static Image RMLINE = new ImageIconDiy("graphics/string/rmline.png").getImage();
	/*
	 * 暂停
	 */
	public static Image PAUSE = new ImageIconDiy("graphics/string/pause.png").getImage();
	/*
	 * 开始按钮
	 */
	public static ImageIcon BIN_START = new ImageIconDiy("graphics/string/strat.png");
	/*
	 * 设置按钮
	 */
	public static ImageIcon BIN_CONFIG = new ImageIconDiy("graphics/string/config.png");
	/*
	 * 下一个图片数组
	 */
	public static  Image[] NEXT_ACT;

	public static List<Image>  BG_LIST;

	static{
		// 下一个方块图片
		NEXT_ACT = new Image[GameConfig.getSystemConfig().getTypeConfig().size()];
		for (int i = 0; i < NEXT_ACT.length; i++) {
			NEXT_ACT[i] = new ImageIconDiy("graphics/game/"+ i + ".png").getImage();
		}
		//背景图片数组
		File dir = new File(FileUtil.getPath("graphics/background"));
		File[] files = dir.listFiles();
		BG_LIST = new ArrayList<Image>();
		for (File file : files) {
			if(file.isDirectory()){
				continue;
			}
			BG_LIST.add(new ImageIcon(file.getPath()).getImage());
		}
	}
}