package com.emailserviceapp.springboot.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ResultDAO {

	private static final ResourceBundle bundle = ResourceBundle.getBundle("application");

	public static Connection getConnection() {

		String DB_URL = bundle.getString("clearDB.URL");
		String USER = bundle.getString("clearDB.USER");
		String PASS = bundle.getString("clearDB.PASSWORD");

		Connection con = null;
		try {
			con = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public static List<EmailResults> getRecords(int start, int total) {
		List<EmailResults> list = new ArrayList<EmailResults>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			ps = con.prepareStatement("select * from Email_Results order by SentTime DESC limit " + (start - 1) + "," + total);
			rs = ps.executeQuery();
			while (rs.next()) {
				EmailResults e = new EmailResults();
				e.setToAddress(rs.getString(1));
				e.setSentTime(rs.getTimestamp(2).toString());
				e.setSentStatus(rs.getBoolean(3));
				list.add(e);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}