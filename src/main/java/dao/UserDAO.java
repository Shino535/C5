package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.UserBean;

public class UserDAO extends DAO {
	public UserBean login(String id, String pass) throws Exception {
		UserBean user = null;
		String sql = "SELECT * FROM user WHERE id=? AND pass=?";
		try(Connection connection = getConnection();
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
		try(Connection connection = getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql);) {
			
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, pass);
			
			pstmt.executeUpdate();
		}
	}

	public boolean update(String name, String pass, String code) throws Exception {
		boolean result = false;
		String sql = "UPDATE user SET name=? , pass=? WHERE code=?";
		try(Connection connection = getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql);) {
			connection.setAutoCommit(false);
			
			pstmt.setString(1, name);
			pstmt.setString(2, pass);
			pstmt.setString(3, code);
			
			int count = pstmt.executeUpdate();
			if (count == 1) {
				result = true;
			} else {
				result = false;
			}
		}
		return result;
	}
	
	public boolean delete(String id) throws Exception{
		boolean result = false;
		String sql = "DELETE FROM user WHERE id=?";
		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql);) {
			connection.setAutoCommit(false);
			pstmt.setString(1, id);
			
			System.out.println("[INFO] UserDAO: ユーザー削除開始 - User ID: " + id);
			int line = pstmt.executeUpdate();
			
			if (line > 0) { // 1行以上削除された場合のみ成功。 ★ 修正: 削除成功判定を `line > 0` に変更
				connection.commit();
				result = true;
				System.out.println("[INFO] UserDAO: ユーザー削除成功 - User ID: " + id);
			} else {
				connection.rollback(); // 影響を受けた行が0なら削除失敗。 ★ 修正: 削除失敗時は rollback する
				System.out.println("[ERROR] UserDAO: ユーザー削除失敗 (該当ユーザーが見つからない可能性) - User ID: " + id);
			}
			connection.setAutoCommit(true);
		}
		return result;
	}

	public String getName(String code) throws Exception {
		String name = null;
		String sql = "SELECT name FROM user WHERE code=?";
		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, code);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					name = rs.getString("name");
				}
			}
		}
		return name;
	}
	
	// ユーザー一覧取得メソッド
	public List<UserBean> getUser() throws Exception {
		List<UserBean> UserList = new ArrayList<UserBean>();
		UserBean user = null;
		String sql = "SELECT * FROM user";
		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql)) {
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					user = new UserBean();
					user.setCode(rs.getString("code"));
					user.setId(rs.getString("id"));
					user.setName(rs.getString("name"));
					user.setRole(rs.getString("role"));
					UserList.add(user);
				}
			}
		}
		return UserList;
	}
	
	// 利用者を削除するメソッド（パラメータはcode）
	public boolean user_delete(String code) throws Exception {
		boolean result = false;
		String sql = "DELETE FROM user WHERE code=?";
		try (Connection connection = getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql)) {
			connection.setAutoCommit(false);
			pstmt.setString(1, code);
			int line = pstmt.executeUpdate();
			if (line <= 0) {
				connection.rollback();
			} else {
				connection.commit();
				result = true;
			}
			connection.setAutoCommit(true);
		}
		return result;
	}
}
