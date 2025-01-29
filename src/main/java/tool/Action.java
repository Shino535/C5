package tool;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@MultipartConfig
public abstract class Action{
	public abstract String execute(HttpServletRequest request,HttpServletResponse response)throws Exception;
}
