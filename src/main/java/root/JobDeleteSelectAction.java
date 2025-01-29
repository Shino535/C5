package root;

import bean.JobBean;
import dao.JobDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class JobDeleteSelectAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		//■パラメータをメンバ変数に格納
		String code = request.getParameter("code");
		System.out.println("code="+code);
		//■DAOのsearchメソッドを呼びだしてBDにアクセスし、searchメソッドの戻り値で検索結果を返す
		//SELECT文のLIKEなどで複数のレコードを返す場合は、List型で受け取る（searchメソッド内でArrayListに格納し戻り値で返している）
		JobDAO dao = new JobDAO();
		JobBean jobBean = dao.getJob(code);
		
		//■リクエスト属性のsetAttribute
		request.setAttribute("jobBean", jobBean);

		return "jobComfirm.jsp";
	}

}
