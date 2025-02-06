package user;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.regex.Pattern;

import bean.UserBean;
import dao.UserDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class UserUpdateAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("GET".equals(request.getMethod()))
			return "userUpdate.jsp";

		// フォームから送信されたパラメータを取得
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String code = request.getParameter("code");
		System.out.println(code);

		if (name.isBlank() || pass.isBlank()) {
			setError(request, "すべての必須項目を入力してください", name, pass);
			return "userUpdate.jsp";
		}

		if (name.length() >= 20) {
			setError(request, "名前は20文字以下で入力してください", name, pass);
			return "userUpdate.jsp";
		}

		if (!Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z]).{10,}$").matcher(pass).matches()) {
			setError(request, "パスワードは英数字混在、10文字以上で入力してください", name, pass);
			return "userUpdate.jsp";
		}

		try {
			UserDAO userdao = new UserDAO();

			boolean updateSuccess = userdao.update(name, pass, code);

			UserBean user = userdao.login(id, pass);
			if (user == null) {
				// 再ログイン失敗時の処理
				setError(request, "再ログインに失敗しました。もう一度お試しください。", name, pass);
				return "userUpdate.jsp";
			}

			HttpSession session = request.getSession();
			session.setAttribute("user", user);

			if (name.equals(name) && pass.equals(pass)) {
				System.out.println("更新内容に変更がありません");
				return "userUpdate.jsp"; // 更新しない
			}

			if (updateSuccess) {
				request.setAttribute("result", true);
				return "userUpdateSuccess.jsp";
			} else {
				setError(request, "更新に失敗しました。もう一度お試しください。", name, pass);
				return "userUpdate.jsp";
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace(); // 詳細なエラー出力
			String error = e.getMessage();
			if (error.contains("cannot be null")) {
				setError(request, "すべての必須項目を入力してください", name, pass);
			} else {
				setError(request, "不明なエラーです", name, pass);
			}
			return "userUpdate.jsp";
		}
	}

	private void setError(HttpServletRequest request, String error, String name, String pass) {
		request.setAttribute("error", error);
		request.setAttribute("name", name);
		request.setAttribute("pass", pass);
	}
}
