import java.util.List;

import bean.JobBean;
import bean.JobExperienceBean;
import dao.JobDAO;
import dao.JobExperienceDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class JobAction extends Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			// クライアントから渡された求人コードを取得
			String code = request.getParameter("code");
			JobDAO jobDAO = new JobDAO();
			JobExperienceDAO jobExperienceDAO = new JobExperienceDAO();
			
			// ★ 求人情報を取得
			JobBean job = jobDAO.getJob(code);
			List<JobExperienceBean> experienceList = jobExperienceDAO.getExperience(code);
			
			if (job == null) {
				// ★ 削除済みの求人の場合、検索結果ページにリダイレクト
				System.err.println("[ERROR] JobAction: 求人情報が見つかりません - code: " + code);
				request.getSession().setAttribute("jobNotFound", "選択した求人は既に削除されました。");
				response.sendRedirect("/C5/Search.action?page=1"); // 検索結果ページに戻る
				// 正常に処理された場合は、求人情報詳細ページに遷移
				return null;
			}
			
			// 取得した求人情報および関連経験情報をリクエスト属性にセット
			request.setAttribute("jobExperience", experienceList);
			request.setAttribute("job", job);
			return "jobDetails.jsp";
			
		} catch (Exception e) {
			// データベースエラーが発生した場合、エラーメッセージをログに出力し、トップページにリダイレクト
			System.err.println("[ERROR] JobAction: データベース処理中にエラー発生");
			e.printStackTrace();
			request.getSession().setAttribute("dbConError", "データベースになんらかの問題が発生しました");
			response.sendRedirect("/C5/");
			return null;
		}
	}
}

