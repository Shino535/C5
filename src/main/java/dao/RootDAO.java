package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.RootBean;

public class RootDAO extends DAO {
	public RootBean login(String id,String pass)throws Exception{
		RootBean root=null;
		String sql="SELECT * FROM root WHERE BINARY id=? AND BINARY pass=?";
		try(Connection connection=getConnection();
			PreparedStatement pstmt=connection.prepareStatement(sql);){
			
			pstmt.setString(1,id);
			pstmt.setString(2,pass);
			
			ResultSet rs=pstmt.executeQuery();
			
			if(rs.next()) {
				root=new RootBean();
				root.setCode(rs.getString("code"));;
				root.setId(rs.getString("id"));
				root.setName(rs.getString("name"));
				root.setRole(rs.getString("role"));
			}
		}
		return root;
	}
	public void register(String id,String name,String pass)throws Exception{
		String sql="INSERT INTO root(id,name,pass) VALUE(?,?,?)";
		try(Connection connection=getConnection();
			PreparedStatement pstmt=connection.prepareStatement(sql);){
			
			pstmt.setString(1,id);
			pstmt.setString(2,name);
			pstmt.setString(3,pass);
			
			pstmt.executeUpdate();
		}
	}
	public boolean update(String fieldName, String updateField, String id) {
		boolean result=false;
		String sql="UPDATE user SET "+fieldName+"=? WHERE id=?";
		try(Connection connection=getConnection();
			PreparedStatement pstmt=connection.prepareStatement(sql);){
			connection.setAutoCommit(false);
			
			pstmt.setString(1,updateField);
			pstmt.setString(2,id);
			
			int line=pstmt.executeUpdate();
			if(line>0) {
				connection.commit();
				result=true;
			}else {
				connection.rollback();
			}
			connection.setAutoCommit(true);
		}catch (Exception e) {
			result=false;
		}
		return result;
	}
	public boolean updateID(String newid,String id) {
		return update("id",newid,id);
	}
	public boolean updateName(String newName,String id) {
		return update("name",newName,id);
	}
	public boolean updatePass(String newPass,String id) {
		return update("pass",newPass,id);
	}
	public boolean delete(String id)throws Exception {
		boolean result=false;
		String selectSql="SELECT COUNT(*) FROM root";
		String deleteSql="DELETE FROM root WHERE id=?";
		try(Connection connection=getConnection();
			PreparedStatement selectPstmt=connection.prepareStatement(selectSql);
			PreparedStatement deletePstmt=connection.prepareStatement(deleteSql)){
			connection.setAutoCommit(false);
			
			ResultSet rs=selectPstmt.executeQuery();
			if(rs.getInt(1)<=1) {
				connection.rollback();
				return false;
			}
			
			deletePstmt.setString(1,id);
			int line=deletePstmt.executeUpdate();
			if(line==0) {
				connection.commit();
				result=true;
			}else {
				connection.rollback();
			}
			
			connection.setAutoCommit(true);
		}
		return result;
	}
	public int count(String keyword)throws Exception{
		int count=0;
		String sql="SELECT * FROM root name=?";
		try(Connection connection=getConnection();
			PreparedStatement pstmt=connection.prepareStatement(sql)){
			pstmt.setString(1,"%"+keyword+"%");
			try(ResultSet rs=pstmt.executeQuery()){
				while(rs.next()) {
					count=rs.getInt(1);
				}
			}
		}
		return count;
	}
	public List<RootBean> search(String keyword,int page){
		List<RootBean> rootList=new ArrayList<RootBean>();
		String sql="DELETE FROM root WHERE name=?";
		try(Connection connection=getConnection();
			PreparedStatement pstmt=connection.prepareStatement(sql);){
			pstmt.setString(1,keyword);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				RootBean root=new RootBean();
				root.setId(rs.getString("id"));
				root.setName(rs.getString("name"));
				root.setRole(rs.getString("role"));
				rootList.add(root);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rootList;
	}
 }
