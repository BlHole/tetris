package com.huaxu.dto;

import com.huaxu.config.GameConfig;
import com.huaxu.entity.GameAct;
import com.huaxu.util.GameFunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class GameDto {
	/*
	 * 游戏宽度
	 */
	public static final int GAMEZONE_W = GameConfig.getSystemConfig().getMaxX() + 1;
	/*
	 * 游戏高度
	 */
	public static final int GAMEZONE_H = GameConfig.getSystemConfig().getMaxY() + 1;

	/*
	 * 数据库记录
	 */
	private List<Player> dbRecode;
	/*
	 * 本地记录
	 */
	private List<Player> diskRecode;
	/*
	 * 游戏地图
	 */
	private boolean[][]  gameMap;
	/*
	 * 下落方块
	 */
	private GameAct gameAct;
	/*
	 * 游戏是否是开始状态
	 */
	private boolean start;
	/*
	 * 是否显示阴影
	 */
	private boolean showShadow;
	/*
	 * 暂停
	 */
	private boolean pause;
	/*
	 * 线程睡眠时间
	 */
	private long sleepTime;
	/*
	 * 是否使用作弊键
	 */
	private boolean cheat;

	private int next;

	private int nowlevel;

	private int nowPoint;

	private int nowRemoveLine;

	public List<Player> getDbRecode() {
		return dbRecode;
	}

	public void setDbRecode(List<Player> dbRecode) {
		this.dbRecode = this.setFillRecode(dbRecode);
	}
	/*
	 * 构造函数
	 */
	public GameDto(){
		dtoInit();
	}
	/*
	 * dto初始化
	 */
	public void dtoInit(){
		this.gameMap = new boolean[GAMEZONE_W][GAMEZONE_H];
		this.nowlevel = 1;
		this.nowPoint = 0;
		this.nowRemoveLine = 0;
		this.pause = false;
		this.cheat = false;
		this.sleepTime = GameFunction.getSleepTimeByLevel(this.nowlevel);
	}

	public List<Player> getDiskRecode() {
		return diskRecode;
	}

	public void setDiskRecode(List<Player> diskRecode) {
		this.diskRecode = this.setFillRecode(diskRecode);
	}

	private List<Player> setFillRecode(List<Player> players){
		//如果进来的是空，那么就创建
		if(players == null){
			players = new ArrayList<Player>();
		}
		//如果记录数小于5 那么久加到5条为止
		while(players.size() < 5){
			players.add(new Player("No Data", 0));
		}
		Collections.sort(players);
		return players;
	}

	public boolean[][] getGameMap() {
		return gameMap;
	}

	public void setGameMap(boolean[][] gameMap) {
		this.gameMap = gameMap;
	}

	public GameAct getGameAct() {
		return gameAct;
	}

	public void setGameAct(GameAct gameAct) {
		this.gameAct = gameAct;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public int getNowlevel() {
		return nowlevel;
	}

	public void setNowlevel(int nowlevel) {
		this.nowlevel = nowlevel;
		//计算线程睡眠时间(下落速度)
		this.sleepTime = GameFunction.getSleepTimeByLevel(this.nowlevel);
	}

	public int getNowPoint() {
		return nowPoint;
	}

	public void setNowPoint(int nowPoint) {
		this.nowPoint = nowPoint;
	}

	public int getNowRemoveLine() {
		return nowRemoveLine;
	}

	public void setNowRemoveLine(int nowRemoveLine) {
		this.nowRemoveLine = nowRemoveLine;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public boolean isShowShadow() {
		return showShadow;
	}

	public void changeShowShadow() {
		this.showShadow = !this.showShadow;
	}

	public boolean isPause() {
		return pause;
	}

	public void changePause() {
		this.pause = !this.pause;
	}

	public boolean isCheat() {
		return cheat;
	}

	public void setCheat(boolean cheat) {
		this.cheat = cheat;
	}

	public long getSleepTime() {
		return sleepTime;
	}

}
