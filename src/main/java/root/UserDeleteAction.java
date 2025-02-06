package root;

import java.util.Arrays;
import java.util.List;

import bean.UserBean;
import dao.UserDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class UserDeleteAction extends Action {
	/**
	 * リクエストパラメータから削除対象の利用者コードを取得し、
	 * UserDAO を使用してデータベースから削除します。
	 * 削除後は、成否に応じて異なる画面へ遷移します。
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// リクエストパラメータから削除対象の利用者コード (code) を取得します。
		String[] codes = request.getParameterValues("code");
		// UserDAO のインスタンスを生成します。
		UserDAO UserDAO = new UserDAO();
		
		boolean result = false;
		
		// 削除対象のコードが存在する場合、削除処理を実行します。
		if (codes != null && codes.length > 0) {
			List<String> codeList = Arrays.asList(codes);
			for (String code : codeList) {
				// 削除処理を実行し、結果を result に格納します。
				result = UserDAO.user_delete(code); 
			}
		}
		
		// 削除に失敗した場合の処理
		if (result == false) {
			// 体験談一覧を取得します。
			List<UserBean> UserList = UserDAO.getUser();
			// 体験談一覧をリクエスト属性に設定します。
			request.setAttribute("userList", UserList);
			// エラーメッセージをリクエスト属性に設定します。
			request.setAttribute("error", "削除に失敗しました");
			// 体験談一覧画面へ遷移します。
			return "userDeleteList.jsp";
		}
		
		// 削除に成功した場合、削除完了画面へ遷移します。
		return "userDeleteSucces.jsp";
	}
}