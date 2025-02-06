package root;

import java.util.List;

import bean.UserBean;
import dao.UserDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class UserDeleteListAction extends Action {
	
	/**
	 * UserDAO を使用してデータベースから利用者一覧を取得し、
	 * リクエスト属性に設定後、利用者削除一覧画面 (userDeleteList.jsp) へ遷移する

	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// UserDAO のインスタンスを生成
		UserDAO dao = new UserDAO();
		// 利用者一覧を取得
		List<UserBean> UserList = dao.getUser();

		// 利用者一覧をリクエスト属性に設定
		request.setAttribute("UserList", UserList);
		// 利用者一覧画面へ遷移
		return "userDeleteList.jsp";
	}

}
