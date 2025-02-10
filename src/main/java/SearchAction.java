import java.util.ArrayList;
import java.util.List;

import bean.JobBean;
import bean.SearchBean;
import dao.JobDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SearchAction extends Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception {
		String company=request.getParameter("company");
		String prefecture=request.getParameter("prefecture");
		String address=request.getParameter("address");
		String jobType=request.getParameter("job_type");
		String benefit=request.getParameter("benefit");
		String holiday=request.getParameter("holiday");
		String employment=request.getParameter("employment");
		
		SearchBean search=new SearchBean();
		search.setCompany(company);
		search.setPrefecture(prefecture);
		search.setAddress(address);
		search.setJobType(jobType);
		search.setBenefit(benefit);
		search.setHoliday(holiday);
		search.setEmployment(employment);
		
		JobDAO jobDAO=new JobDAO();
		int totalPage=(int)Math.ceil((double)jobDAO.count(search)/20);
		int thisPage=Integer.parseInt(request.getParameter("page"));
		List<JobBean> joblist=jobDAO.search(search,thisPage);
		if(joblist==null) {
			joblist=new ArrayList<JobBean>();
		}
		
		System.out.printf("現在のページ:%d%n全ページ数:%d%n",thisPage,totalPage);
		
		request.setAttribute("joblist",joblist);
		request.setAttribute("searchList",search);
		
		request.setAttribute("totalPage",totalPage);
		request.setAttribute("thisPage",thisPage);
		
		return "jobSearch.jsp";
	}
}
