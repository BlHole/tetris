package com.huaxu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.huaxu.dto.Player;
import com.mysql.jdbc.PreparedStatement;


public class DataBase implements Data{

	private final String dbUrl;
	
	private final String dbUser;
	
	private final String dbPwd;
	
	private static String LOAD_SQL = "SELECT user_name,point FROM user_point WHERE type_id = 1 ORDER BY point DESC";
	
	private static String SAVE_SQL = "INSERT INTO user_point(user_name,point,type_id) VALUES(?,?,?)";
	
	public DataBase(HashMap<String , String> param){
		this.dbUrl = param.get("dbUrl");
		this.dbUser = param.get("dbUser");
		this.dbPwd = param.get("dbPwd");
		try {
			Class.forName(param.get("driver"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Player> loadData() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Player> players = new ArrayList<Player>();
//		try {
//			conn = DriverManager.getConnection(dbUrl,dbUser,dbPwd);
//			stmt = (PreparedStatement) conn.prepareStatement(LOAD_SQL);
//			rs = stmt.executeQuery();
//			while(rs.next()){
//				players.add(new Player(rs.getString(1), rs.getInt(2)));
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally{
//			if(conn != null)
//				try {
//					conn.close();
//					stmt.close();
//					rs.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//		}
		return players;
	}

	public void saveData(Player players) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(dbUrl,dbUser,dbPwd);
			stmt = (PreparedStatement)conn.prepareStatement(SAVE_SQL);
			stmt.setObject(1, players.getName());
			stmt.setObject(2, players.getPoint());
			stmt.setObject(3, 1);
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(conn != null)
				try {
					conn.close();
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}
}
