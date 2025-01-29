package user;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.regex.Pattern;

import dao.UserDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class UserUpdateAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// フォームから送信されたパラメータを取得
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String code = request.getParameter("code");

		if (id.isBlank() || name.isBlank() || pass.isBlank()) {
			setError(request, "すべての必須項目を入力してください", id, name, pass, code);
			return "register.jsp";
		}
		if (id.length() >= 20) {
			setError(request, "IDは20文字以下で入力してください", id, name, pass, code);
			return "register.jsp";
		}
		if (name.length() >= 20) {
			setError(request, "名前は20文字以下で入力してください", id, name, pass, code);
			return "register.jsp";
		}
		if (!Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z]).{15,}$").matcher(pass).matches()) {
			setError(request, "パスワードは英数字混在、15文字以上で入力してください", id, name, pass, code);
			return "register.jsp";
		}

		try {
			UserDAO userdao = new UserDAO();
			userdao.update(id, name, pass, code);
			return "userUpdateSuccess.jsp";
		} catch (SQLIntegrityConstraintViolationException e) {
			String error = e.getMessage();
			if (error.contains("Duplicate entry"))
				setError(request, "このIDは既に使用されています", id, name, pass, code);
			else if (error.contains("cannot be null"))
				setError(request, "すべての必須項目を入力してください", id, name, pass, code);
			else
				setError(request, "", id, name, pass, code);
			return "userUpdateInput.jsp";
		}
	}

	private void setError(HttpServletRequest request, String error, String id, String name, String pass, String code) {
		request.setAttribute("error", error);
		request.setAttribute("id", id);
		request.setAttribute("name", name);
		request.setAttribute("pass", pass);
		request.setAttribute("code", code);
	}
}