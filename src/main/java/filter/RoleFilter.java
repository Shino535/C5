package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

@WebFilter(urlPatterns={"/*"})
public class RoleFilter implements Filter{
	@Override
	public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)throws IOException,ServletException{
//		
//		HttpServletRequest httpRequest=(HttpServletRequest)request;
//		HttpServletResponse httpResponse=(HttpServletResponse)response;
//		
//		HttpSession session=httpRequest.getSession(false);
//		String role=null;
//		if(session!=null) {
//			UserBean user=(UserBean)session.getAttribute("user");
//			RootBean root=(RootBean)session.getAttribute("root");
//			if(user!=null) {
//				role=user.getRole();
//			}else if(root!=null){
//				role=root.getRole();
//			}
//		}
//		
//		String uri=httpRequest.getRequestURI();
//		System.out.printf("フィルター通過 : URI[%s] 権限[%s]%n",uri,(role==null?"なし":role));
//		
//		//全アクター
//		if(uri.endsWith("/C5/")||uri.contains("/C5/Search")||uri.contains("/C5/Job")||uri.contains("/C5/jobDetails")) {
//			chain.doFilter(request,response);
//			return;
//		}
//		
//		//guest
//		if(role==null) {
//			if(uri.contains("Login")||uri.contains("/user/Register")) {
//				chain.doFilter(request,response);
//				return;
//			}else {
//				httpResponse.sendRedirect("/C5/user/Login.action");
//				return;
//			}
//		}
//		
//		//user
//		if(role=="user") {
//			if(uri.contains("/user/")) {
//				chain.doFilter(request,response);
//				return;
//			}else {
//				httpResponse.sendRedirect("/C5/user/Login.action");
//				return;
//			}
//		}
//		
//		//root
//		if(role.equals("root")) {
//			if(uri.contains("/root/")) {
//				chain.doFilter(request,response);
//				return;
//			}else {
//				
//			}
//		}
		chain.doFilter(request,response);
	}
}