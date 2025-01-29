package root;

import java.util.List;

import bean.JobBean;
import dao.JobDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class JobListAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		//■パラメータをメンバ変数に格納
		String company = request.getParameter("company");

		//■DAOのsearchメソッドを呼びだしてBDにアクセスし、searchメソッドの戻り値で検索結果を返す
		//SELECT文のLIKEなどで複数のレコードを返す場合は、List型で受け取る（searchメソッド内でArrayListに格納し戻り値で返している）
		JobDAO dao = new JobDAO();
		List<JobBean> list = dao.search(company);

		//■setAttribute
		//既存のセッションIDを取得もしくは新しいセッションIDを生成する
		HttpSession session = request.getSession();
		//色々処理後(セッションIDはあるけど中身がない状態を想定）
		session.setAttribute("list", list);
		
		//画面遷移先
		return "jobList.jsp";
	}

}