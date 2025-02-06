package root;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class UserDeleteConfirmAction extends Action {
	/**
	 * 削除対象の利用者コードをリクエスト属性に設定し、確認画面へフォワードします。
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    String[] deleteCode = request.getParameterValues("code");
	    
	    if (deleteCode == null) {
	        request.setAttribute("error", "一つ以上選択してください");
	        return "UserDeleteList.action";
	    }

	    List<String> deleteNameList = new ArrayList<>();
	    for (String code : deleteCode) {
	        String name = request.getParameter("name_" + code);
	        if (name != null) {
	            deleteNameList.add(name);
	        }
	    }
	    
	    String[] deleteName = deleteNameList.toArray(new String[0]);

	    request.setAttribute("deleteCode", deleteCode);
	    request.setAttribute("deleteName", deleteName);

	    return "userConfirm.jsp";
	}

}