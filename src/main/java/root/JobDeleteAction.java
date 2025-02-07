package root;

import dao.JobDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class JobDeleteAction extends Action{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//■パラメータをメンバ変数に格納
		String code = request.getParameter("code");
		
		//■DAOのsearchメソッドを呼びだしてBDにアクセスし、
		//SELECT文のLIKEなどで複数のレコードを返す場合は、List型で受け取る（searchメソッド内でArrayListに格納し戻り値で返している）
		JobDAO dao = new JobDAO();
		boolean result = dao.delete(code);
		HttpSession session=request.getSession();
		if (result == true) {
			session.setAttribute("jobdelete_t","求人の削除に成功");
			response.sendRedirect("/C5/root/JobList.action");
			return null;
		} else {
			session.setAttribute("jobdelete_f","求人の削除に失敗");
			response.sendRedirect("/C5/root/JobList.action");
			return null;
		}
	}
}
