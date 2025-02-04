package root;

import java.util.Arrays;
import java.util.List;

import bean.JobExperienceBean;
import dao.JobExperienceDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

/**
 * 体験談削除処理を行う Action クラスです。
 * ユーザーが選択した体験談をデータベースから削除します。
 */
public class ExperienceDeleteAction extends Action {
	/**
	 * リクエストパラメータから削除対象の体験談コードを取得し、
	 * JobExperienceDAO を使用してデータベースから削除します。
	 * 削除後は、成否に応じて異なる画面へ遷移します。
	 *
	 * @param request  HttpServletRequest オブジェクト
	 * @param response HttpServletResponse オブジェクト
	 * @return 遷移先 JSP のパス (成功時は experienceDeleteSuccess.jsp, 失敗時は experienceList.jsp)
	 * @throws Exception 処理中に例外が発生した場合
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// リクエストパラメータから削除対象の体験談コード (code) を取得します。
		String[] codes = request.getParameterValues("code");
		// JobExperienceDAO のインスタンスを生成します。
		JobExperienceDAO experienceDAO = new JobExperienceDAO();
		
		boolean result = false;
		
		// 削除対象のコードが存在する場合、削除処理を実行します。
		if (codes != null && codes.length > 0) {
			List<String> codeList = Arrays.asList(codes);
			for (String code : codeList) {
				// 削除処理を実行し、結果を result に格納します。
				result = experienceDAO.delete(code); // 修正: deleteExperience メソッドは削除件数を返す
			}
		}
		
		// 削除に失敗した場合の処理
		if (result == false) {
			// 体験談一覧を取得します。
			List<JobExperienceBean> experienceList = experienceDAO.getExperience();
			// 体験談一覧をリクエスト属性に設定します。
			request.setAttribute("experienceList", experienceList);
			// エラーメッセージをリクエスト属性に設定します。
			request.setAttribute("error", "削除に失敗しました");
			// 体験談一覧画面へ遷移します。
			return "experienceList.jsp";
		}
		
		// 削除に成功した場合、削除完了画面へ遷移します。
		return "experienceDeleteSucces.jsp";
	}
}