package root;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.regex.Pattern;

import bean.RootBean;
import dao.RootDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class RegisterAction extends Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if("GET".equals(request.getMethod())) return "register.jsp";
		
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String pass=request.getParameter("pass");
		
		if(id.isBlank()||name.isBlank()||pass.isBlank()) {
			setError(request,"すべての必須項目を入力してください",id,name,pass);
			return "register.jsp";
		}
		if(id.length()>=20) {
			setError(request,"IDは20文字以下で入力してください",id,name,pass);
			return "register.jsp";
		}
		if(name.length()>=20) {
			setError(request,"名前は20文字以下で入力してください",id,name,pass);
			return "register.jsp";
		}
		if(!Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z]).{15,256}$").matcher(pass).matches()) {
			setError(request,"パスワードは英数字混在、15~256以上で入力してください",id,name,pass);
			return "register.jsp";
		}
		
		try {
			RootDAO rootDAO=new RootDAO();
			rootDAO.register(id,name,pass);
			RootBean root=rootDAO.login(id,pass);
			
			HttpSession session=request.getSession();
			session.setAttribute("root",root);
			request.setAttribute("result",true);
			
			return "register.jsp";
		}catch(SQLIntegrityConstraintViolationException e) {
			String error=e.getMessage();
			if(error.contains("Duplicate entry"))
				setError(request,"このIDは既に使用されています",id,name,pass);
			else if(error.contains("cannot be null"))
				setError(request,"すべての必須項目を入力してください",id,name,pass);
			else
				setError(request,"",id,name,pass);
			return "register.jsp";
		}
	}
	private void setError(HttpServletRequest request,String error,String id,String name,String pass) {
		request.setAttribute("error",error);
		request.setAttribute("id",id);
		request.setAttribute("name",name);
		request.setAttribute("pass",pass);
	}
}
