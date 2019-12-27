package com.huaxu.dto;

import java.io.Serializable;

public class Player implements Comparable<Player>, Serializable {

	private static final long serialVersionUID = -8494572248923790701L;

	private String name;
	
	private int point;

	public Player(String name, int point) {
		super();
		this.name = name;
		this.point = point;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int compareTo(Player pla) {
		return pla.point - this.point;
	}
}
