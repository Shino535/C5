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
		String code = request.getParameter("code");
		JobDAO jobDAO = new JobDAO();
		JobExperienceDAO jobExperienceDAO = new JobExperienceDAO();
		JobBean job=jobDAO.getJob(code);
		List<JobExperienceBean> experienceList = jobExperienceDAO.getExperience(code);
		
		if(job == null) {
			request.setAttribute("error", "");
			return "jobSearch.jsp";
		}
		
		request.setAttribute("jobExperience", experienceList);
		request.setAttribute("job", job);
		return "jobDetails.jsp";
	}
}
