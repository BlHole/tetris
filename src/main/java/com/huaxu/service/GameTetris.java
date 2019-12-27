package com.huaxu.service;

import com.huaxu.config.GameConfig;
import com.huaxu.dto.GameDto;
import com.huaxu.entity.GameAct;

import java.awt.Point;
import java.util.Map;
import java.util.Random;



public class GameTetris implements GameService {
	/*
	 * 游戏
	 */
	private GameDto dto;
	/*
	 * 随机数生成器
	 */
	private Random random = new Random();
	/*
	 * 方块种类个数
	 */
	private static final int MAX_TYPE = GameConfig.getSystemConfig().getTypeConfig().size() - 1;
	/*
	 * 升级行数
	 */
	private static final int LEVEL_UP = GameConfig.getSystemConfig().getLevelUp();
	/*
	 * 连续消行分数表
	 */
	private static final Map<Integer, Integer> PLUS_POINT = GameConfig.getSystemConfig().getPlusPoint();

	public GameTetris(GameDto dto) {
		this.dto = dto;
	}
	/*
	 * 控制键方向 上
	 */
	public boolean keyUp() {
		if(this.dto.isPause()) {
			return false;
		}
		synchronized (this.dto) {
			this.dto.getGameAct().round(this.dto.getGameMap());
		}
		return true;
	}
	/*
	 * 方块操作
	 */
	public boolean keyDown() {
		if(this.dto.isPause()) {
			return false;
		}
		synchronized (this.dto) {
			//方块向下移动，并判断是否移动成功
			if(this.dto.getGameAct().move(0, 1, this.dto.getGameMap())){
				return false;
			}
			//获得游戏地图对象
			boolean[][] map = this.dto.getGameMap();
			//获得方块对象
			Point[] act = this.dto.getGameAct().getActPoints();
			//将方块堆积到地图数组
			for (int i = 0; i < act.length; i++) {
				map[act[i].x][act[i].y] = true;
			}
			//判断消行，并计算获得经验值
			int plusExp = this.plusExp();
			//如果发生消行
			if(plusExp > 0){
				//增加经验值
				this.plusPoint(plusExp);
			}
			//刷新新的方块
			this.dto.getGameAct().init(this.dto.getNext());
			// 随机生成在下一个方块
			this.dto.setNext(random.nextInt(MAX_TYPE));
			//检查游戏是否失败
			if(this.isLose()) {
				//结束游戏
				this.dto.setStart(false);
			}
		}
		return true;
	}
	/*
	 * 检查游戏是否失败
	 */
	private boolean isLose() {
		//获得现在的活动方块
		Point[] actPoints = this.dto.getGameAct().getActPoints();
		//获得现在的游戏地图
		boolean[][] map = this.dto.getGameMap();
		for (int i = 0; i < actPoints.length; i++) {
			if(map[actPoints[i].x][actPoints[i].y]) {
				return true;
			}
		}
		return false;
	}
	/*
	 * 加分升级操作
	 */
	private void plusPoint(int plusExp) {
		int lv = this.dto.getNowlevel();
		int rmLine = this.dto.getNowRemoveLine();
		int point = this.dto.getNowPoint();
		if(rmLine % LEVEL_UP + plusExp >= LEVEL_UP){
			this.dto.setNowlevel(++lv);
		}
		this.dto.setNowRemoveLine(rmLine + plusExp);
		this.dto.setNowPoint(point + PLUS_POINT.get(plusExp));
	}
	/*
	 * 消行操作
	 */
	private int plusExp() {
		//获得游戏地图
		boolean[][] map = this.dto.getGameMap();
		int exp = 0;
		//扫描游戏，查看是否有可消行
		for (int y = 0; y < GameDto.GAMEZONE_H; y++) {
			//判断是否可以消行
			if(this.isCanRemoveLine(y, map)){
				//如果可以消行，消之
				this.removeLine(y,map);
				//增加经验值
				exp++;
			}
		}
		return exp;
	}
	/*
	 * 消行处理
	 */
	private void removeLine(int rowNumber, boolean[][] map) {
		for (int x = 0; x < GameDto.GAMEZONE_W; x++) {
			for (int y = rowNumber; y > 0; y--) {
				map[x][y] = map[x][y-1];
			}
			map[x][0] = false;
		}
	}
	/*
	 * 判断某一行是否可消除
	 */
	private boolean isCanRemoveLine(int y,boolean[][] map){
		//单行内对每一个单元格进行扫描
		for (int x = 0; x < GameDto.GAMEZONE_W; x++) {
			if(!map[x][y]){
				return false;
			}
		}
		return true;
	}
	/*
	 * 控制键方向 左
	 */
	public boolean keyLeft() {
		if(this.dto.isPause()) {
			return false;
		}
		synchronized (this.dto) {
			this.dto.getGameAct().move(-1,0,this.dto.getGameMap());
		}
		return true;
	}
	/*
	 * 控制键方向 右
	 */
	public boolean keyRight() {
		if(this.dto.isPause()) {
			return false;
		}
		synchronized (this.dto) {
			this.dto.getGameAct().move(1,0,this.dto.getGameMap());
		}
		return true;
	}
	public boolean keyFunUp() {
		//作弊键
		this.dto.setCheat(true);
		this.plusPoint(4);
		return true;
	}
	public boolean keyFunDown() {
		//快速下落
		if(this.dto.isPause()) {
			return false;
		}
		while(!this.keyDown());
		return true;
	}
	public boolean keyFunLeft() {
		//显示阴影
		this.dto.changeShowShadow();
		return true;
	}
	public boolean keyFunRight() {
		//游戏暂停
		if(this.dto.isStart()){
			this.dto.changePause();
		}
		return true;
	}

	public void startGame() {
		//随机生成下一个方块
		this.dto.setNext(random.nextInt(MAX_TYPE));
		//随机生成现在方块
		dto.setGameAct(new GameAct(random.nextInt(MAX_TYPE)));
		//把游戏状态设为开始
		this.dto.setStart(true);
		//dto初始化
		this.dto.dtoInit();
	}

	public void mainAction() {
		this.keyDown();
	}
}