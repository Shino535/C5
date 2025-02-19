package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.JobExperienceBean;

public class JobExperienceDAO extends DAO {
	
	// 体験談を追加するメソッド
	public void add(String comment, String user_code, String job_code, String user_name, String job_name)throws Exception {
		String sql = "INSERT INTO jobExperience(comment, user_code, job_code, user_name, job_name) VALUES(?, ?, ?, ?, ?)";
		try(Connection connection = getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql)){
			connection.setAutoCommit(false);
			
			pstmt.setString(1, comment);
			pstmt.setString(2, user_code);
			pstmt.setString(3, job_code);
			pstmt.setString(4, user_name);
			pstmt.setString(5, job_name);
			
			int line = pstmt.executeUpdate();
			if(line==1) {
				connection.commit();
			}else {
				connection.rollback();
			}
			connection.setAutoCommit(true);
		}
	}

	// 体験談を削除するメソッド
	public boolean delete(String code)throws Exception{
		boolean result = false;
		String sql = "DELETE FROM jobExperience WHERE code=?";
		try(Connection connection = getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql)) {
			connection.setAutoCommit(false);
			
			pstmt.setString(1,code); 
			
			int line = pstmt.executeUpdate();
			if(line <= 0) {
				connection.rollback();
			}else {
				connection.commit();
				result = true;
			}
			connection.setAutoCommit(true);
		}
		return result;
	}
	
	// 体験談取得メソッド
	public List<JobExperienceBean> getExperience()throws Exception{
		List<JobExperienceBean> experienceList=new ArrayList<JobExperienceBean>();
		JobExperienceBean jobExperience=null;
		String sql="SELECT * FROM jobexperience";
		try(Connection connection=getConnection();
			PreparedStatement pstmt=connection.prepareStatement(sql)){
			try(ResultSet rs=pstmt.executeQuery()){
				while(rs.next()) {
					jobExperience = new JobExperienceBean();
					jobExperience.setCode(rs.getString("code"));
					jobExperience.setJob_code(rs.getString("job_code"));
					jobExperience.setUser_code(rs.getString("user_code"));
					jobExperience.setJob_name(rs.getString("job_name"));
					jobExperience.setUser_name(rs.getString("user_name"));
					jobExperience.setComment(rs.getString("comment"));
					jobExperience.setDate(rs.getDate("date"));
					experienceList.add(jobExperience);
				}
			}
		}
		return experienceList;
	}
	
	// 体験談をcodeを元に取得するメソッド
	public List<JobExperienceBean> getExperience(String job_code)throws Exception{
		List<JobExperienceBean> experienceList=new ArrayList<JobExperienceBean>();
		JobExperienceBean jobExperience = null;
		String sql = "SELECT * FROM jobexperience WHERE job_code = ?";
		try(Connection connection = getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql)){
			pstmt.setString(1, job_code);
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()) {
					jobExperience = new JobExperienceBean();
					jobExperience.setJob_code(rs.getString("job_code"));
					jobExperience.setUser_code(rs.getString("user_code"));
					jobExperience.setJob_name(rs.getString("job_name"));
					jobExperience.setUser_name(rs.getString("user_name"));
					jobExperience.setComment(rs.getString("comment"));
					jobExperience.setDate(rs.getDate("date"));
					experienceList.add(jobExperience);
				}
			}
		}
		return experienceList;
	}
}
