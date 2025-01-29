package user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class JobExperienceListAction extends Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return null;
	}
}

//public class JobExperienceListAction extends Action{
//	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception{
//		
//		String comment;
//		
//	//■DAOのsearchメソッドを呼びだしてBDにアクセスし、searchメソッドの戻り値で検索結果を返す
//		//SELECT文のLIKEなどで複数のレコードを返す場合は、List型で受け取る（searchメソッド内でArrayListに格納し戻り値で返している）
//		JobExperienceDAO dao = new JobExperienceDAO();
//		List<JobExperienceBean> list = dao.search(comment);
//		
//	//■setAttribute
//		//既存のセッションIDを取得もしくは新しいセッションIDを生成する
//		HttpSession session = request.getSession();
//		//色々処理後(セッションIDはあるけど中身がない状態を想定）
//		session.setAttribute("list", list);
//		
//	//■フォワードメソッドで画面遷移させる
//		request.getRequestDispatcher("jobExperienceList.jsp").forward(request, response);
//		
//		return null;
//	}
//}
