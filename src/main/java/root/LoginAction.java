package root;

import bean.RootBean;
import dao.RootDAO;
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
		
		RootDAO rootDAO=new RootDAO();
		RootBean root=rootDAO.login(id,pass);
		
		if(root==null) {
			request.setAttribute("error","ログイン失敗、IDかパスワードが間違っています");
			return "login.jsp";
		}
		
		HttpSession session=request.getSession();
		session.setAttribute("root",root);
		
		response.sendRedirect("/C5");
		return null;
	}
}
