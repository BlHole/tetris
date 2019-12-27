package com.huaxu.dao;

import com.huaxu.dto.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DataDisk implements Data {

	private final Map<String, String> dataDiskMap;

	public DataDisk(HashMap<String, String> param){
		this.dataDiskMap = param;
	}

	public List<Player> loadData() {
		List<Player> players = new ArrayList();
		for (Map.Entry<String, String> entry : dataDiskMap.entrySet()) {
			players.add(new Player(entry.getKey(), Integer.parseInt(entry.getValue())));
		}
		return players;
	}
	public void saveData(Player pla){
	}
}
