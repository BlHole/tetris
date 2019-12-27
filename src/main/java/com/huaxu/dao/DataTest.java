package com.huaxu.dao;

import com.huaxu.dto.Player;
import com.huaxu.ui.imgge.Img;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DataTest implements Data{

	public DataTest(HashMap<String, String> param){
	}

	public List<Player> loadData() {
		List<Player> players = new ArrayList<Player>();
		players.add(new Player("刘明", 100));
		players.add(new Player("陈光", 3000));
		players.add(new Player("李同", 4000));
		return players;
	}

	public void saveData(Player players) {
		System.out.println();
	}

	public static void main(String[] args) {
		ImageIcon BIN_START = new ImageIcon(Img.class.getClassLoader().getResource("graphics/string/strat.png").getPath());
		System.out.println(BIN_START);
	}
}