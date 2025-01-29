package root;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

/**
 * 体験談削除の確認画面を表示するための Action クラスです。
 * ユーザーが選択した削除対象の体験談コードをリクエスト属性に設定し、確認画面へ遷移します。
 */
public class ExperienceConfirmAction extends Action {
	/**
	 * 削除対象の体験談コードをリクエスト属性に設定し、確認画面へフォワードします。
	 *
	 * @param request  HttpServletRequest オブジェクト
	 * @param response HttpServletResponse オブジェクト
	 * @return 遷移先 JSP のパス (ここでは experienceConfirm.jsp)
	 * @throws Exception 処理中に例外が発生した場合
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// リクエストパラメータから削除対象の体験談コード (code) を取得します。
		String[] deleteCode = request.getParameterValues("code");

		// 削除する体験談が選択されていない場合はエラーメッセージを設定し、体験談一覧画面へ戻します。
		if (deleteCode == null) {
			request.setAttribute("error", "一つ以上選択してください");
			// ExperienceListAction へフォワードします。
			request.getRequestDispatcher("ExperienceList.action").forward(request, response);
			return null; // フォワードしたので、ここでは null を返します。
		}

		// 削除対象の体験談コードをリクエスト属性に設定します。
		request.setAttribute("deleteCode", deleteCode);
		// 確認画面へ遷移します。
		return "experienceConfirm.jsp";
	}
}