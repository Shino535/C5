import java.util.List;

import bean.JobBean;
import bean.JobExperienceBean;
import dao.JobDAO;
import dao.JobExperienceDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class JobAction extends Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try { // ★ try catch を追加
		String code = request.getParameter("code");
		JobDAO jobDAO = new JobDAO();
		JobExperienceDAO jobExperienceDAO = new JobExperienceDAO();
		JobBean job=jobDAO.getJob(code);
		List<JobExperienceBean> experienceList = jobExperienceDAO.getExperience(code);
		
		if(job == null) {
			request.setAttribute("error", ""); // ★ 求人情報が見つかりませんでした、入れる？
			return "jobSearch.jsp";
		}
		
		request.setAttribute("jobExperience", experienceList);
		request.setAttribute("job", job);
		return "jobDetails.jsp";
		
		} catch (Exception e) {
            // ★ 修正: 例外をログに出力して、FrontController に伝える
            System.err.println("[ERROR] JobAction: データベース処理中にエラー発生");
            e.printStackTrace();
            throw e; // ★ 例外を再スローすることで、FrontController に伝える
        }
	}
}

