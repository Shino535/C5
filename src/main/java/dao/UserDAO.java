package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.UserBean;

public class UserDAO extends DAO {
	public UserBean login(String id, String pass) throws Exception {
		UserBean user = null;
		String sql = "SELECT * FROM user WHERE id=? AND pass=?";
		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql);) {

			pstmt.setString(1, id);
			pstmt.setString(2, pass);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				user = new UserBean();
				user.setCode(rs.getString("code"));
				;
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setRole(rs.getString("role"));
			}
		}
		return user;
	}

	public void register(String id, String name, String pass) throws Exception {
		String sql = "INSERT INTO user(id,name,pass) VALUE(?,?,?)";
		try (Connection connection = getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql);) {
			
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, pass);
			
			pstmt.executeUpdate();
		}
	}

	public boolean update(String id, String name, String pass, String code) throws Exception {
		boolean result = false;
		String sql = "UPDATE user SET id=? name=? pass=? WHERE code=?";
		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql);) {
			connection.setAutoCommit(false);
			
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, pass);
			pstmt.setString(4, code);
			
			int line = pstmt.executeUpdate();
			if (line > 0) {
				connection.commit();
				result = true;
			} else {
				connection.rollback();
			}
			connection.setAutoCommit(true);
		} catch (Exception e) {
			result = false;
		}
		return result;
	}
	
	public boolean delete(String id) {
		boolean result = false;
		String sql = "DELETE FROM user WHERE id=?";
		try (Connection connection = getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql);) {
			connection.setAutoCommit(false);
			pstmt.setString(1, id);
			int line = pstmt.executeUpdate();
			if (line == 0) {
				connection.commit();
				result = true;
			} else {
				connection.rollback();
			}
			connection.setAutoCommit(true);
		} catch (Exception e) {
			result = false;
		}
		return result;
	}
	
	public String getName(String code)throws Exception {
		String name = null;
		String sql = "SELECT name FROM user WHERE code=?";
		try(Connection connection = getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql)){
			pstmt.setString(1, code);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					name = rs.getString("name");
				}
			}
		}
		return name;
	}
}
