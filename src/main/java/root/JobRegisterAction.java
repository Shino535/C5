package root;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.UUID;

import dao.JobDAO;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import tool.Action;

@MultipartConfig
public class JobRegisterAction extends Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if("GET".equals(request.getMethod())) return "jobRegister.jsp";
		
		// フォームから送信されたパラメータを取得
		String company = request.getParameter("company");
		String prefecture = request.getParameter("prefecture");
		String address = request.getParameter("address");
		String job_type = request.getParameter("job_type");
		String benefitStr = request.getParameter("benefit");
		String holidayStr = request.getParameter("holiday");
		String employment = request.getParameter("employment");
		String pdf = null;
		
		int benefit;
		int holiday;
		
		// 1. 空白チェック
		if (company.isBlank() || prefecture.isBlank() || address.isBlank() || job_type.isBlank() || benefitStr.isBlank() || holidayStr.isBlank() || employment.isBlank()) {
			setError(request, "全ての項目を入力してください", company, prefecture, address, job_type, benefitStr, holidayStr, employment);
			return "jobRegister.jsp"; // フォームに戻る
		}
		
		// 2. 文字数チェック
		if (company.length() > 100 || address.length() > 100 || job_type.length() > 50) {
			setError(request, "文字数が多いです", company, prefecture, address, job_type, benefitStr, holidayStr, employment);
			return "jobRegister.jsp"; // フォームに戻る
		}
		
		// 3. benefitとholidayの数字チェック
		if(benefitStr.matches("[0-9]+")) {
			benefit = Integer.parseInt(benefitStr);
			if(benefit < 1) {
				setError(request, "月収は1以上の半角数字で入力してください", company, prefecture, address, job_type, benefitStr, holidayStr, employment);
			}
		}else {
			setError(request, "月収は半角数字で入力してください", company, prefecture, address, job_type, benefitStr, holidayStr, employment);
			return "jobRegister.jsp";
		}
		if(holidayStr.matches("[0-9]+")) {
			holiday = Integer.parseInt(holidayStr);
			if(holiday > 1) {
				setError(request, "年間休日は1以上の半角数字で入力してください", company, prefecture, address, job_type, benefitStr, holidayStr, employment);
			}
		}else {
			setError(request, "年間休日は半角数字で入力してください", company, prefecture, address, job_type, benefitStr, holidayStr, employment);
			return "jobRegister.jsp";
		}
		
		// 5.PDFファイルのアップロード処理
		Part part = request.getPart("pdf");
		String fileName = part.getSubmittedFileName();
		
		String fullPath = request.getServletContext().getRealPath("/");
		
		int metaIndex = fullPath.indexOf(".metadata");
		String uploadPath = fullPath.substring(0, metaIndex) + "C5/src/main/webapp/pdf/";
		String lastFileName;
		
		// 6.PDFファイルエラーハンドリング
		if (fileName != null && !fileName.isBlank()) {
			// ファイル名被り対策
			String uuid = UUID.randomUUID().toString();
			String extension = fileName.substring(fileName.lastIndexOf("."));
			lastFileName = uuid + extension;
			File file = new File(uploadPath + lastFileName);
			while(file.exists()) {
				uuid = UUID.randomUUID().toString();
				lastFileName = uuid + extension;
				file = new File(uploadPath + lastFileName);
			}
			part.write(uploadPath + lastFileName);
			pdf = uploadPath + lastFileName;
			
			// PDFか判別
			if(!request.getServletContext().getMimeType(pdf).startsWith("application/pdf")) {
				File uploaFile = new File(pdf);
				uploaFile.delete();
				setError(request, "アップロードできるのはPDFファイルのみです", company, prefecture, address, job_type, benefitStr, holidayStr, employment);
				return "jobRegister.jsp";
			}
			
			// ファイルサイズ判別
			Path p = Paths.get(pdf);
			double d = Files.size(p) / 1048576.0;
			if(d > 10) {
				File uploFile = new File(pdf);
				uploFile.delete();
				setError(request, "PDFのファイルサイズは10MB以下です", company, prefecture, address, job_type, benefitStr, holidayStr, employment);
				return "jobRegister.jsp";
			}
		}else {
			setError(request, "PDFは必須です", company, prefecture, address, job_type, benefitStr, holidayStr, employment);
			return "jobRegister.jsp";
		}
		pdf = "pdf/" + lastFileName;
		
		// DB登録
		try {
			JobDAO jobDAO = new JobDAO();
			jobDAO.add(company, prefecture, address, job_type, benefit, holiday, employment, pdf);
			request.setAttribute("result", true);
			setError(request, "登録成功", company, prefecture, address, job_type, benefitStr, holidayStr, employment);
			return "jobRegister.jsp";
		}catch (SQLIntegrityConstraintViolationException e) {
			String error = e.getMessage();
			if(error.contains("cannot be null")) {
				setError(request, "全ての項目を入力してください", company, prefecture, address, job_type, benefitStr, holidayStr, employment);
			}
			return "jobRegister.jsp";
		}
	}
	
	// エラーメッセージ用メソッド
	private void setError(HttpServletRequest request, String error, String company, String prefecture, String address,String job_type, String benefit, String holiday, String employment) {
		request.setAttribute("error", error);
		request.setAttribute("company", company);
		request.setAttribute("prefecture", prefecture);
		request.setAttribute("address", address);
		request.setAttribute("job_type", job_type);
		request.setAttribute("benefit", benefit);
		request.setAttribute("holiday", holiday);
		request.setAttribute("employment", employment);
	}
}