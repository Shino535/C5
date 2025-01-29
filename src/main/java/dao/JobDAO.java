package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.JobBean;
import bean.SearchBean;

public class JobDAO extends DAO {
	//■searchメソッド
	//Productテーブル内のレコードを検索するためのメソッド
	//引数：検索文字列)String)　戻り値：検索結果(List<Product>)
	public List<JobBean> search(String company) throws Exception {

		//検索結果を入れるArrayListを準備
		List<JobBean> list = new ArrayList<>();

		//String companyがnullとnull以外のSQL文をsql変数に代入する
		String sql = "SELECT * FROM testjob WHERE company LIKE ?";

		try ( //データベースへ接続
				Connection con = getConnection();
				//SQLの準備
				PreparedStatement st = con.prepareStatement(sql);) {
			//プレースホルダーへ値を入れる
			st.setString(1, "%" + company + "%");
			//SQLの実行、結果を取得
			ResultSet rs = st.executeQuery();
			//結果を1レコードずつBeanへ格納し、ArrayListへ追加
			while (rs.next()) {
				JobBean job = new JobBean();
				job.setCode(rs.getString("code"));
				job.setCompany(rs.getString("company"));
				job.setAddress(rs.getString("address"));
				job.setJob_type(rs.getString("Job_type"));
				job.setBenefit(rs.getInt("Benefit"));
				job.setHoliday(rs.getInt("Holiday"));
				job.setEmployment(rs.getString("Employment"));
				job.setPdf_path(rs.getString("Pdf_path"));

				list.add(job);
			}
			//DBにsearchし、その結果をBeanに格納し返す
			return list;
		} catch (Exception e) {
			throw e;
		}
	}

	// 求人を更新するメソッド
	public boolean update(String company, String prefecture, String address, String job_type, String s_benefit, String s_holiday, String employment, String pdf, String code) throws Exception {
		boolean result = false;
		String sql = "UPDATE testjob SET company=? , prefecture=? ,address=? ,job_type=? ,benefit=? ,holiday=?, employment=?,pdf_path=? WHERE code=?";
		try (Connection con = getConnection();
			PreparedStatement st = con.prepareStatement(sql);) {
			//データ型の変換
			int benefit = Integer.parseInt(s_benefit);
			int holiday = Integer.parseInt(s_holiday);
			
			//プレースホルダへ値を入れる
			st.setString(1, company); // 
			st.setString(2, prefecture); // 
			st.setString(3, address); // 
			st.setString(4, job_type); // 
			st.setInt(5, benefit); // 
			st.setInt(6, holiday); // 
			st.setString(7, employment); // 
			st.setString(8, pdf); // 
			st.setString(9, code); // 
			//SQLの実行、結果を取得
			int count = st.executeUpdate();
			if (count == 1) {
				result = true;
			} else {
				result = false;
			}
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	// 全アクターが使える求人検索用のメソッド
	public List<JobBean> search(SearchBean searchBean, int page) throws Exception {
		List<JobBean> jobList = new ArrayList<JobBean>();
		String sql = "SELECT * FROM testjob WHERE 1=1 ";
		if (searchBean.getCompany() != null && !searchBean.getCompany().isEmpty()) {
			sql += "AND company LIKE ? ";
		}
		if (searchBean.getPrefecture() != null && !searchBean.getPrefecture().isEmpty()) {
			sql += "AND prefecture LIKE ? ";
		}
		if (searchBean.getAddress() != null && !searchBean.getAddress().isEmpty()) {
			sql += "AND address LIKE ? ";
		}
		if (searchBean.getJobType() != null && !searchBean.getJobType().isEmpty()) {
			sql += "AND job_type LIKE ? ";
		}
		if (searchBean.getBenefit() != null && !searchBean.getBenefit().isEmpty()) {
			sql += "AND benefit>=? ";
		}
		if (searchBean.getHoliday() != null && !searchBean.getHoliday().isEmpty()) {
			sql += "AND holiday>=? ";
		}
		if (searchBean.getEmployment() != null && !searchBean.getEmployment().isEmpty()) {
			sql += "AND employment=? ";
		}
		sql += "LIMIT 20 OFFSET ?";
		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql)) {
			int i = 1;
			if (searchBean.getCompany() != null && !searchBean.getCompany().isEmpty()) {
				pstmt.setString(i++, "%" + searchBean.getCompany() + "%");
			}
			if (searchBean.getPrefecture() != null && !searchBean.getPrefecture().isEmpty()) {
				pstmt.setString(i++, "%" + searchBean.getPrefecture() + "%");
			}
			if (searchBean.getAddress() != null && !searchBean.getAddress().isEmpty()) {
				pstmt.setString(i++, "%" + searchBean.getAddress() + "%");
			}
			if (searchBean.getJobType() != null && !searchBean.getJobType().isEmpty()) {
				pstmt.setString(i++, "%" + searchBean.getJobType() + "%");
			}
			if (searchBean.getBenefit() != null && !searchBean.getBenefit().isEmpty()) {
				pstmt.setInt(i++, Integer.parseInt(searchBean.getBenefit()));
			}
			if (searchBean.getHoliday() != null && !searchBean.getHoliday().isEmpty()) {
				pstmt.setInt(i++, Integer.parseInt(searchBean.getHoliday()));
			}
			if (searchBean.getEmployment() != null && !searchBean.getEmployment().isEmpty()) {
				pstmt.setString(i++, searchBean.getEmployment());
			}
			
			int offset = (page - 1) * 20;
			pstmt.setInt(i++, offset);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					JobBean job = new JobBean();
					job.setCode(rs.getString("code"));
					job.setCompany(rs.getString("company"));
					job.setPrefecture(rs.getString("prefecture"));
					job.setAddress(rs.getString("address"));
					job.setJob_type(rs.getString("job_type"));
					job.setBenefit(rs.getInt("benefit"));
					job.setHoliday(rs.getInt("holiday"));
					jobList.add(job);
				}
			}
		}
		return jobList;
	}
	// 求人検索のページネーション用
	public int count(SearchBean searchBean) throws Exception {
		int count = 0;
		String sql = "SELECT COUNT(*) FROM testjob WHERE 1=1 ";
		if (searchBean.getCompany() != null && !searchBean.getCompany().isEmpty()) {
			sql += "AND company LIKE ? ";
		}
		if (searchBean.getPrefecture() != null && !searchBean.getPrefecture().isEmpty()) {
			sql += "AND address LIKE ? ";
		}
		if (searchBean.getAddress() != null && !searchBean.getAddress().isEmpty()) {
			sql += "AND address LIKE ? ";
		}
		if (searchBean.getJobType() != null && !searchBean.getJobType().isEmpty()) {
			sql += "AND job_type LIKE ? ";
		}
		if (searchBean.getBenefit() != null && !searchBean.getBenefit().isEmpty()) {
			sql += "AND benefit>=? ";
		}
		if (searchBean.getHoliday() != null && !searchBean.getHoliday().isEmpty()) {
			sql += "AND holiday>=? ";
		}
		if (searchBean.getEmployment() != null && !searchBean.getEmployment().isEmpty()) {
			sql += "AND employment=? ";
		}
		try (Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql)) {
			int i = 1;
			if (searchBean.getCompany() != null && !searchBean.getCompany().isEmpty()) {
				pstmt.setString(i++, "%" + searchBean.getCompany() + "%");
			}
			if (searchBean.getPrefecture() != null && !searchBean.getPrefecture().isEmpty()) {
				pstmt.setString(i++, "%" + searchBean.getPrefecture() + "%");
			}
			if (searchBean.getAddress() != null && !searchBean.getAddress().isEmpty()) {
				pstmt.setString(i++, "%" + searchBean.getAddress() + "%");
			}
			if (searchBean.getJobType() != null && !searchBean.getJobType().isEmpty()) {
				pstmt.setString(i++, "%" + searchBean.getJobType() + "%");
			}
			if (searchBean.getBenefit() != null && !searchBean.getBenefit().isEmpty()) {
				pstmt.setInt(i++, Integer.parseInt(searchBean.getBenefit()));
			}
			if (searchBean.getHoliday() != null && !searchBean.getHoliday().isEmpty()) {
				pstmt.setInt(i++, Integer.parseInt(searchBean.getHoliday()));
			}
			if (searchBean.getEmployment() != null && !searchBean.getEmployment().isEmpty()) {
				pstmt.setString(i++, searchBean.getEmployment());
			}
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					count = rs.getInt(1);
				}
			}
		}
		return count;
	}
	// Jobの各プロパティを送り登録して、登録できたJobBeanを返す
	public JobBean add(String company, String prefecture, String address, String job_type, int benefit, int holiday, String employment, String pdf_path)throws Exception{
		JobBean job = null;
		String sql = "INSERT INTO testjob(company, prefecture, address, job_type, benefit, holiday, employment, pdf_path) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		try(Connection connection = getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql)) {
			connection.setAutoCommit(false);
			
			pstmt.setString(1, company);
			pstmt.setString(2, prefecture);
			pstmt.setString(3, address);
			pstmt.setString(4, job_type);
			pstmt.setInt(5, benefit);
			pstmt.setInt(6, holiday);
			pstmt.setString(7, employment);
			pstmt.setString(8, pdf_path);
			
			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected == 1) {
				try(ResultSet rs = pstmt.getGeneratedKeys()){
					if(rs.next()) {
						job = new JobBean();
						job.setCompany(company);
						job.setPrefecture(prefecture);
						job.setAddress(address);
						job.setJob_type(job_type);
						job.setBenefit(benefit);
						job.setHoliday(holiday);
						job.setEmployment(employment);
						job.setPdf_path(pdf_path);
					}
				}
				connection.commit();
			} else {
				connection.rollback();
			}
			connection.setAutoCommit(true);
		} 
		return job; // 結果を返す
	}
	// codeを元に一つのjobのみ返す
	public JobBean getJob(String code) throws Exception {
		JobBean job = null;
		String sql = "SELECT * FROM testjob WHERE code=?";
		try(Connection connection = getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, code);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					job = new JobBean();
					job.setCode(rs.getString("code"));
					job.setCompany(rs.getString("company"));
					job.setPrefecture(rs.getString("prefecture"));
					job.setAddress(rs.getString("address"));
					job.setJob_type(rs.getString("job_type"));
					job.setBenefit(rs.getInt("benefit"));
					job.setHoliday(rs.getInt("holiday"));
					job.setEmployment(rs.getString("employment"));
					job.setPdf_path(rs.getString("pdf_path"));
				}
			}
		}
		return job;
	}
	//
	public boolean delete(String code) throws Exception {
		boolean result = false;
		String sql = "DELETE FROM testjob WHERE code=?";
		try (Connection connection = getConnection();
				PreparedStatement st = connection.prepareStatement(sql)) {

			st.setString(1, code);

			int count = st.executeUpdate();

			if (count >= 1) {
				result = true;
			} else {
				result = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	//codeをもとに会社名だけ抜き出す
	public String getName(String code)throws Exception{
		String name = null;
		String sql = "SELECT company FROM testjob WHERE code=?";
		try(Connection connection = getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql)){
			pstmt.setString(1, code);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					name = rs.getString("company");
				}
			}
		}
		return name;
	}
}
