package com.itheima.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.itheima.domain.User;
import com.itheima.util.JDBCUtils;

public class MySqlUserDao implements UserDao {

	@Override
	public void addUser(User user) {
		String sql = "insert into users values (null,'" + user.getUsername() + "','" + user.getPassword() + "','"
				+ user.getNickname() + "','" + user.getEmail() + "')";
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConn();
			stat = conn.createStatement();
			stat.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCUtils.close(rs, stat, conn);
		}
	}

	@Override
	public User findUserByUNandPSW(String username, String password) {
		String sql = "select * from users where username='" + username + "' and password='" + password + "'";
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConn();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setNickname(rs.getString("nickname"));
				user.setEmail(rs.getString("email"));
				return user;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCUtils.close(rs, stat, conn);
		}
	}

	public User findUserByUserName(String username) {
		String sql = "select * from users where username='" + username + "'";
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConn();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setNickname(rs.getString("nickname"));
				user.setEmail(rs.getString("email"));
				return user;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCUtils.close(rs, stat, conn);
		}
	}

}
