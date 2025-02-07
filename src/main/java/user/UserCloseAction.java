package user;

import bean.UserBean;
import dao.UserDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

/**
 * ユーザーアカウントの退会処理を行うアクションクラス。
 */
public class UserCloseAction extends Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if("GET".equals(request.getMethod())) return "userWithdrawConfirm.jsp";
		System.out.println("[INFO] UserCloseAction: 退会処理開始");
		// セッション取得
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			System.out.println("[ERROR] セッションが存在しない、またはログイン情報が見つからない");
			return "login.jsp";
		}
		
		// セッションからユーザー情報を取得
		UserBean user = (UserBean) session.getAttribute("user");
		String userId = user.getId();
		System.out.println("[INFO] User ID: " + userId);
		
		// UserDAOを用いてデータベースから削除
		UserDAO userDAO = new UserDAO();
		boolean result = userDAO.delete(userId);
		
		if (result) {
			// 削除成功時、セッションを破棄
			session.invalidate();
			System.out.println("[INFO] 退会処理成功: User ID = " + userId);
			
			// JSPではなくトップページへリダイレクト**
			request.getSession().invalidate();
			request.getSession().setAttribute("logout","退会しました。");
			response.sendRedirect("/C5/");
			return null; // **リダイレクトを使用する場合、return null にする**
		} else {
			// 削除失敗時、エラーメッセージを設定して退会確認画面へ戻る
			System.out.println("[ERROR] 退会処理失敗: User ID = " + userId);
			request.setAttribute("error", "退会処理に失敗しました。");
			return "userWithdrawConfirm.jsp";
		}
	}
}
