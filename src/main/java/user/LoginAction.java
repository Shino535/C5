package user;

import bean.UserBean;
import dao.UserDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class LoginAction extends Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if("GET".equals(request.getMethod())) return "login.jsp";
		
		String id=request.getParameter("id");
		String pass=request.getParameter("pass");
		
		UserDAO userDAO=new UserDAO();
		UserBean user=userDAO.login(id,pass);
		
		if(user==null) {
			request.setAttribute("error","ログイン失敗、IDかパスワードが間違っています");
			return "login.jsp";
		}
		
		HttpSession session=request.getSession();
		session.setAttribute("user",user);
		
		response.sendRedirect("/C5");
		return null;
	}
}
