package com.huaxu.dao;

import com.huaxu.config.GameConfig;
import com.huaxu.dto.Player;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class DataDisk implements Data{

	private final String filePath;

	public DataDisk(HashMap<String , String> param){
		this.filePath = param.get("path");
	}

	public List<Player> loadData() {
		ObjectInputStream ois = null;
		List<Player> players = new ArrayList<Player>();
		players.add(new Player("张三丰", 5000));
		players.add(new Player("张无忌", 400));
		players.add(new Player("谢逊", 3900));
		players.add(new Player("赵敏", 100));
//		try {
////			ois = new ObjectInputStream(new FileInputStream(filePath));
//			ois = new ObjectInputStream(DataDisk.class.getClassLoader().getResourceAsStream(filePath));
//			players = (List<Player>)ois.readObject();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				ois.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		return players;
	}
	public void saveData(Player pla){
		//先取出数据
		List<Player> players = this.loadData();
		//追加新记录
		players.add(pla);
		//重新写入
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(filePath));
			oos.writeObject(players);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
