package root;

import dao.JobDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class JobDeleteAction extends Action{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//■パラメータをメンバ変数に格納
		String code = request.getParameter("code");
		
		//■DAOのsearchメソッドを呼びだしてBDにアクセスし、
		//SELECT文のLIKEなどで複数のレコードを返す場合は、List型で受け取る（searchメソッド内でArrayListに格納し戻り値で返している）
		JobDAO dao = new JobDAO();
		boolean result = dao.delete(code);

		if (result == true) {
			return "jobDeleteSuccess.jsp";//更新成功画面
		} else {
			request.setAttribute("errorMessage", "更新に失敗しました。もう一度お試しください。");
			return "jobList.jsp"; // 求人情報一覧画面に遷移
		}
	}
}
