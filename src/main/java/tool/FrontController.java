package tool;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@MultipartConfig
@WebServlet(urlPatterns={"*.action"})
public class FrontController extends HttpServlet {
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		try {
			System.out.printf("FrontController通過%n");
			
			String path=request.getServletPath().substring(1);
			String name=path.replace(".a","A").replace('/','.');
			Action action=(Action)Class.forName(name).getDeclaredConstructor().newInstance();
			String url=action.execute(request, response);
			request.getRequestDispatcher(url).forward(request, response);
		}catch(SQLException|NamingException e) {
//			String referer=request.getHeader("Referer");
			
			System.err.printf("%s%n",e);
			HttpSession session=request.getSession();
			
			session.setAttribute("dbConError","データベースエラー");
			response.sendRedirect("/C5");
		}catch (NullPointerException e) {
			System.err.printf("nullだけど問題ないよ%n");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		doPost(request,response);
	}
}
