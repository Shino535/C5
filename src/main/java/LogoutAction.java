import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class LogoutAction extends Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if("GET".equals(request.getMethod())) {
			request.setAttribute("result",true);
			return "logout.jsp";
		}
		
		request.getSession().invalidate();
		request.getSession().setAttribute("logout","ログアウト成功");
		response.sendRedirect("/C5/");
		return null;
	}
}
