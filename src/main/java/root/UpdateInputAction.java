package root;

import bean.JobBean;
import dao.JobDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class UpdateInputAction extends Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//■パラメータをメンバ変数に格納
		String code = request.getParameter("code");
		
		//■DAOのsearchメソッドを呼びだしてBDにアクセスし、searchメソッドの戻り値で検索結果を返す
		JobDAO dao = new JobDAO();
		JobBean jobBean = dao.getJob(code);
		
		System.out.printf("月収:%d%n年休:%d%n",jobBean.getBenefit(),jobBean.getHoliday());
		
		//■リクエスト属性のsetAttribute
		request.setAttribute("jobBean", jobBean);
		return "jobUpdateInput.jsp";
	}
}
