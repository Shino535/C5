package user;

import java.sql.SQLIntegrityConstraintViolationException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.JobDAO;
import dao.JobExperienceDAO;
import dao.UserDAO;
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
		
		String user_name = userDAO.getName(user_code);
		String job_name = jobDAO.getName(job_code);
		
		if(comment.isBlank()) {
			setError(request, "コメントは必ず入力してください");
			response.sendRedirect("/C5/Job.action?code=" + job_code);
			return null;
		}
		if(comment.length() > 255) {
			setError(request, "コメントは255文字以内で入力してください");
			response.sendRedirect("/C5/Job.action?code=" + job_code);
			return null;
		}else {
			try {
				experienceDAO.add(comment, user_code, job_code, user_name, job_name);
				setSuccess(request, "体験談投稿に成功");
				response.sendRedirect("/C5/Job.action?code=" + job_code);
				return null;
			}catch (SQLIntegrityConstraintViolationException e) {
				String error = e.getMessage();
				if(error.contains("FOREIGN KEY")) {
					setError(request, "体験談投稿に失敗しました");
				}
				response.sendRedirect("/C5/Job.action?code=" + job_code);
				return null;
			}
		}
	}
	private void setError(HttpServletRequest request,String errorMsg) {
		HttpSession session=request.getSession();
		session.setAttribute("error", errorMsg);
	}
	private void setSuccess(HttpServletRequest request,String successMsg) {
		HttpSession session=request.getSession();
		session.setAttribute("success", successMsg);
	}
}
