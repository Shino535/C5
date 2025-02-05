package root;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import bean.JobBean;
import dao.JobDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import tool.Action;

public class JobUpdateAction extends Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// フォームから送信されたパラメータを取得
		String company = request.getParameter("company");
		String prefecture = request.getParameter("prefecture");
		String address = request.getParameter("address");
		String job_type = request.getParameter("job_type");
		String s_benefit = request.getParameter("benefit"); // 月収
		String s_holiday = request.getParameter("holiday"); // 年間休日
		String employment = request.getParameter("employment");
		String pdf = null;
		String code = request.getParameter("code");
		
		int benefit;
		int holiday;
		
		// 失敗時にも元の情報をセット
		JobDAO dao = new JobDAO();
		JobBean jobBean = dao.getJob(code);
		request.setAttribute("jobBean", jobBean);

		// 1. 空白チェック
		if (company.isBlank() || prefecture.isBlank() || address.isBlank() || job_type.isBlank() || s_benefit.isBlank()
				|| s_holiday.isBlank() || employment.isBlank()) {
			setError(request, "全ての項目を入力してください", company, prefecture, address, job_type, s_benefit, s_holiday,
					employment);
			return "jobUpdateInput.jsp"; // フォームに戻る
		}

		// 2. 文字数チェック
		if (company.length() > 100 || address.length() > 100 || job_type.length() > 50 || s_benefit.length() > 8 ) {
			setError(request, "文字数が多いです", company, prefecture, address, job_type, s_benefit, s_holiday, employment);
			return "jobUpdateInput.jsp"; // フォームに戻る
		}

		// 3. benefitとholidayの数字チェック
		if (s_benefit.matches("[0-9]+")) {
			benefit = Integer.parseInt(s_benefit);
			if (benefit < 1) {
				setError(request, "月収は1以上の半角数字で入力してください", company, prefecture, address, job_type, s_benefit, s_holiday,
						employment);
			}
		} else {
			setError(request, "月収は半角数字で入力してください", company, prefecture, address, job_type, s_benefit, s_holiday,
					employment);
			return "jobUpdateInput.jsp";
		}
		if (s_holiday.matches("[0-9]+")) {
			holiday = Integer.parseInt(s_holiday);
			if (holiday > 1) {
				setError(request, "年間休日は1以上の半角数字で入力してください", company, prefecture, address, job_type, s_benefit,
						s_holiday, employment);
			}
		} else {
			setError(request, "年間休日は半角数字で入力してください", company, prefecture, address, job_type, s_benefit, s_holiday,
					employment);
			return "jobUpdateInput.jsp";
		}
		
		// 4. 年間休日を366以上入力できないようにする処理
		holiday = Integer.parseInt(s_holiday);
		if (holiday >= 366 ) {
			setError(request, "年間休日は365以下の半角数字で入力してください", company, prefecture, address, job_type, s_benefit, s_holiday, employment);
			return "jobUpdateInput.jsp"; // フォームに戻る
		}

		// 5.PDFファイルのアップロード処理
		Part part = request.getPart("pdf");
		String fileName = part.getSubmittedFileName();

		String fullPath = request.getServletContext().getRealPath("/");

		//		System.out.println(fullPath);

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
			while (file.exists()) {
				uuid = UUID.randomUUID().toString();
				lastFileName = uuid + extension;
				file = new File(uploadPath + lastFileName);
			}
			part.write(uploadPath + lastFileName);
			pdf = uploadPath + lastFileName;

			// PDFか判別
			if (!request.getServletContext().getMimeType(pdf).startsWith("application/pdf")) {
				File uploaFile = new File(pdf);
				uploaFile.delete();
				setError(request, "アップロードできるのはPDFファイルのみです", company, prefecture, address, job_type, s_benefit,
						s_holiday, employment);
				return "jobUpdateInput.jsp";
			}

			// ファイルサイズ判別
			Path p = Paths.get(pdf);
			double d = Files.size(p) / 1048576.0;
			if (d > 10) {
				File uploFile = new File(pdf);
				uploFile.delete();
				setError(request, "PDFのファイルサイズは10MB以下です", company, prefecture, address, job_type, s_benefit, s_holiday,
						employment);
				return "jobUpdateInput.jsp";
			}
		} else {
			setError(request, "PDFは必須です", company, prefecture, address, job_type, s_benefit, s_holiday, employment);
			return "jobUpdateInput.jsp";
		}
		pdf = "pdf/" + lastFileName;

		// 更新処理
		boolean result = dao.update(company, prefecture, address, job_type, s_benefit, s_holiday,
				employment, pdf, code);

		if (result == true) {
			return "jobUpdateSuccess.jsp";//更新成功画面
		} else {
			request.setAttribute("errorMessage", "更新に失敗しました。もう一度お試しください。");
			return "jobList.jsp"; // 求人情報一覧画面に遷移
		}
	}

	// エラーメッセージ用メソッド
	private void setError(HttpServletRequest request, String error, String company, String prefecture, String address,
			String job_type, String benefit, String holiday, String employment) {
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