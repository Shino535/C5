package su;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.SuDAO;
import tool.Action;

public class AddAction extends Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if("GET".equals(request.getMethod())) return "add.jsp";
		
		String input  = request.getParameter("blog");
		String title = request.getParameter("title");
		
		//<script>タグだけリムーブ
		String blog = input.replaceAll("(?i)<script.*?>.*?</script>","[REMOVED]");
		
		HttpSession session = request.getSession();
		
		//それぞれの空文字判定
		if(input.isBlank() || title.isBlank()) {
			request.setAttribute("blog", input);
			request.setAttribute("title", title);
			session.setAttribute("add-result", false);
			return "add.jsp";
		}
		
		//DBに登録
		SuDAO suDAO = new SuDAO();
		suDAO.add(title, blog);
		
		session.setAttribute("addresult", true);
		return "add.jsp";
	}
}
