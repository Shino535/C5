package filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.RootBean;
import bean.UserBean;

@WebFilter(urlPatterns={"/*"})
public class RoleFilter implements Filter{
	@Override
	public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)throws IOException,ServletException{
		
		HttpServletRequest httpRequest=(HttpServletRequest)request;
		HttpServletResponse httpResponse=(HttpServletResponse)response;
		
		HttpSession session=httpRequest.getSession(false);
		String role=null;
		String name=null;
		if(session!=null) {
			UserBean user=(UserBean)session.getAttribute("user");
			RootBean root=(RootBean)session.getAttribute("root");
			if(user!=null) {
				role=user.getRole();
				name=user.getName();
			}else if(root!=null){
				role=root.getRole();
				name=root.getName();
			}
		}
		
		String uri=httpRequest.getRequestURI();
		System.out.printf("ﾌｨﾙﾀｰ通過:名前[%s] 権限[%s] URI[%s] %n",(name==null?"ゲスト":name),(role==null?"なし":role),uri);
		
		List<String> allowedPaths = Arrays.asList(
			"/C5/Search","/C5/Job","/C5/jobDetails","/C5/pdf",
			"/C5/css","/C5/img","/C5/js","/C5/font"
		);
		
		//suのみ
		if("su".equals(role)) {
			chain.doFilter(request,response);
			return;
		}
		
		//全アクター
		if(allowedPaths.stream().anyMatch(uri::contains)||uri.endsWith("/C5/")) {
			chain.doFilter(request,response);
			return;
		}
		
		//guest
		if(session==null||role==null) {
			if(uri.contains("Login")||uri.contains("/user/Register")) {
				chain.doFilter(request,response);
				return;
			}else {
				session=httpRequest.getSession(true);
				session.setAttribute("roleerror","権限がありません");
				httpResponse.sendRedirect("/C5/user/Login.action");
				return;
			}
		}
		
		//user
		if("user".equals(role)) {
			if(uri.contains("/user/")||uri.contains("/Logout")) {
				chain.doFilter(request,response);
				return;
			}else {
				session.setAttribute("roleerror","権限がありません");
				httpResponse.sendRedirect("/C5/user/Login.action");
				return;
			}
		}
		
		//root
		if("root".equals(role)) {
			if(uri.contains("/root/")||uri.contains("/Logout")) {
				chain.doFilter(request,response);
				return;
			}else if(uri.contains("/UserClose")) {
				session.setAttribute("roleerror","権限がありません");
				httpResponse.sendRedirect("/C5/");
				return;
			}else {
				session.setAttribute("roleerror","権限がありません");
				httpResponse.sendRedirect("/C5/user/Login.action");
				return;
			}
		}
		chain.doFilter(request,response);
	}
}
