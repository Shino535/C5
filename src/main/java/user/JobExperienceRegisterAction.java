package user;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import bean.JobBean;
import bean.JobExperienceBean;
import dao.JobDAO;
import dao.JobExperienceDAO;
import dao.UserDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class JobExperienceRegisterAction extends Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String job_code = request.getParameter("job_code");
		String user_code = request.getParameter("user_code");
		String comment = request.getParameter("comment");
		
		System.out.println(job_code);
		System.out.println(user_code);
		System.out.println(comment);
		
		JobExperienceDAO experienceDAO = new JobExperienceDAO();
		JobDAO jobDAO = new JobDAO();
		UserDAO userDAO = new UserDAO();
		JobBean job = jobDAO.getJob(job_code);
		
		String user_name = userDAO.getName(user_code);
		String job_name = jobDAO.getName(job_code);
		
		List<JobExperienceBean> experienceList = experienceDAO.getExperience(job_code);
		
		if(comment.isBlank()) {
			setError(request, "コメントは必ず入力してください", comment, job, experienceList);
			request.setAttribute("job_name", job_name);
			response.sendRedirect("/C5/Job.action?code=" + job_code);
			return null;
		}
		if(comment.length() > 255) {
			setError(request, "コメントは255文字以内で入力してください", comment, job, experienceList);
			request.setAttribute("job_name", job_name);
			response.sendRedirect("/C5/Job.action?code=" + job_code);
			return null;
		}else {
			try {
				experienceDAO.add(comment, user_code, job_code, user_name, job_name);
				experienceList = experienceDAO.getExperience(job_code);
				setError(request, "体験談投稿に成功", comment, job, experienceList);
				request.setAttribute("job_name", job_name);
				response.sendRedirect("/C5/Job.action?code=" + job_code);
				return null;
			}catch (SQLIntegrityConstraintViolationException e) {
				String error = e.getMessage();
				if(error.contains("FOREIGN KEY")) {
					setError(request, "体験談投稿エラー", comment, job, experienceList);
				}
				response.sendRedirect("/C5/Job.action?code=" + job_code);
				return "/jobDetails.jsp";
			}
		}
		
	}
	private void setError(HttpServletRequest request, String error, String comment, JobBean job, List<JobExperienceBean> experienceList) {
		request.setAttribute("error", error);
		request.setAttribute("comment", comment);
		request.setAttribute("job", job);
		request.setAttribute("jobExperience", experienceList);
	}
}
