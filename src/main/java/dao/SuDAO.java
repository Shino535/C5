package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class SuDAO extends DAO{
	public void add(String title,String blog)throws Exception{
		String sql = "INSERT INTO blog(title,blog) VALUES(?,?)";
		try(Connection connection = getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql)){
			connection.setAutoCommit(false);
			
			pstmt.setString(1,title);
			pstmt.setString(2,blog);
			
			int line = pstmt.executeUpdate();
			if(line == 1) {
				connection.commit();
			}else {
				connection.rollback();
			}
			connection.setAutoCommit(true);
		}
	}
}
