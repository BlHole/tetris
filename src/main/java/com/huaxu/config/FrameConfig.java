package com.huaxu.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class FrameConfig implements Serializable{

	private final String title;

	private final int windowUp;

	private final int width;

	private final int height;

	private final int padding;

	private final int border;

	private final int sizeRol;

	private final int loseIdx;

	/*
	 * 图层属性
	 */
	private final List<LayerConfig> layersConfig;
	/*
	 * 按钮属性
	 */
	private final ButtonConfig buttonConfig;

	public FrameConfig(Element frame) {
		// 获取窗口宽度
		this.width = Integer.parseInt(frame.attributeValue("width"));
		// 获取窗口高度
		this.height = Integer.parseInt(frame.attributeValue("height"));
		// 获取粗细
		this.border = Integer.parseInt(frame.attributeValue("border"));
		// 获取边框内边距
		this.padding = Integer.parseInt(frame.attributeValue("padding"));
		// 获取标题
		this.title = frame.attributeValue("title");
		// 获取窗口拔高尺寸
		this.windowUp = Integer.parseInt(frame.attributeValue("windowUp"));
		// 图块尺寸左位移偏移量
		this.sizeRol = Integer.parseInt(frame.attributeValue("sizeRol"));
		// 游戏失败图片
		this.loseIdx = Integer.parseInt(frame.attributeValue("loseIdx"));
		// 获取窗口属性
		@SuppressWarnings("unchecked")
		List<Element> layers = frame.elements("layer");
		layersConfig = new ArrayList<LayerConfig>();
		for (Element layer : layers) {
			LayerConfig lc = new LayerConfig(layer.attributeValue("className"),
					Integer.parseInt(layer.attributeValue("x")),
					Integer.parseInt(layer.attributeValue("y")),
					Integer.parseInt(layer.attributeValue("w")),
					Integer.parseInt(layer.attributeValue("h")));
			layersConfig.add(lc);
		}
		//初始化按钮属性
		buttonConfig = new ButtonConfig(frame.element("button"));
	}

	public String getTitle() {
		return title;
	}

	public int getWindowUp() {
		return windowUp;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getPadding() {
		return padding;
	}

	public int getBorder() {
		return border;
	}

	public List<LayerConfig> getLayersConfig() {
		return layersConfig;
	}

	public int getSizeRol() {
		return sizeRol;
	}

	public int getLoseIdx() {
		return loseIdx;
	}

	public ButtonConfig getButtonConfig() {
		return buttonConfig;
	}

}
