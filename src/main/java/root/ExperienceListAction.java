package root;

import java.util.List;

import bean.JobExperienceBean;
import dao.JobExperienceDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

/**
 * 体験談一覧画面を表示する Action クラス
 * データベースから体験談一覧を取得し画面に表示
 */
public class ExperienceListAction extends Action {
	/**
	 * JobExperienceDAO を使用してデータベースから体験談一覧を取得し、
	 * リクエスト属性に設定後、体験談一覧画面 (experienceList.jsp) へ遷移する
	 *
	 * @param request  HttpServletRequest オブジェクト
	 * @param response HttpServletResponse オブジェクト
	 * @return 遷移先 JSP のパス (ここでは experienceList.jsp)
	 * @throws Exception 処理中に例外が発生した場合
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// JobExperienceDAO のインスタンスを生成
		JobExperienceDAO experienceDAO = new JobExperienceDAO();
		// 体験談一覧を取得
		List<JobExperienceBean> experienceList = experienceDAO.getExperience();

		// 体験談一覧をリクエスト属性に設定
		request.setAttribute("experienceList", experienceList);
		// 体験談一覧画面へ遷移
		return "experienceList.jsp";
	}
}