package com.huaxu.util;

public class GameFunction {

	/*
	 * 计算线程睡眠时间
	 */
	public static long getSleepTimeByLevel(int Level) {
		long sleep = (-40 * Level + 740);
		sleep = sleep < 100 ? 100 : sleep;
		return sleep;
	}
}
