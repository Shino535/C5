package root;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

		// エラーメッセージリスト
		List<String> errorMessages = new ArrayList<>();

		// 1. 空白チェック
		checkEmptyFields(new String[] { "会社名", "県", "住所", "職種", "月収", "年間休日", "雇用形態" },
				new String[] { company, prefecture, address, job_type, s_benefit, s_holiday, employment },
				errorMessages);

		if (!errorMessages.isEmpty()) {
			request.setAttribute("errorMessages", errorMessages);
			return "jobList.jsp"; // 求人情報一覧画面に遷移
		}

		//		// 2. 県と住所合体
		//		address = (prefecture != null && !prefecture.trim().isEmpty() ? prefecture : "") +
		//				(address != null && !address.trim().isEmpty() ? address : "");

		// 3. 文字数制限チェック
		checkFieldLength(new String[] { "会社名", "住所", "職種" }, new String[] { company, address, job_type },
				errorMessages);

		if (!errorMessages.isEmpty()) {
			request.setAttribute("errorMessages", errorMessages);
			return "jobList.jsp"; // 求人情報一覧画面に遷移
		}

		// 4. 月収（benefit）と年間休日（holiday）の数値チェック
		validatePositiveIntegers(new String[] { "月収", "年間休日" }, new String[] { s_benefit, s_holiday }, errorMessages);

		if (!errorMessages.isEmpty()) {
			request.setAttribute("errorMessages", errorMessages);
			return "jobList.jsp"; // 求人情報一覧画面に遷移
		}

		// 5. PDFファイルのアップロード処理
		Part part = request.getPart("pdf");
		String fileName = part.getSubmittedFileName();

		if (fileName != null && !fileName.isBlank()) {

			//アップロードパス処理
			String uploadPath = request.getServletContext().getRealPath("/");
			if (uploadPath.indexOf(".metadata") != -1) {
				String resultPath = uploadPath.substring(0, uploadPath.indexOf(".metadata"))
						+ "C5/src/main/webapp/pdf/";
				uploadPath = resultPath.replace("\\", "/");
			}

			//ファイル名被り対策処理
			String uuid = UUID.randomUUID().toString();
			String extension = fileName.substring(fileName.lastIndexOf("."));
			String LastFileName = uuid + extension;
			File file = new File(uploadPath + LastFileName);
			while (file.exists()) {
				uuid = UUID.randomUUID().toString();
				LastFileName = uuid + extension;
				file = new File(uploadPath + LastFileName);
			}
			part.write(uploadPath + LastFileName);
			pdf = uploadPath + LastFileName;

			//PDFファイルか判別
			if (!request.getServletContext().getMimeType(pdf).startsWith("application/pdf")) {
				File uploadFile = new File(pdf);
				uploadFile.delete();

				request.setAttribute("error", "PDFのみ選択出来ます");
				return "jobList.jsp"; // 求人情報一覧画面に遷移
			}

			//ファイルサイズ判別
			Path path = Paths.get(pdf);
			Double d = (double) (Files.size(path) / 1048576);
			if (d > 10.0) {
				File uploadFile = new File(pdf);
				uploadFile.delete();

				request.setAttribute("error", "10MB以下のファイルのみ選択できます");
				return "jobList.jsp"; // 求人情報一覧画面に遷移
			}
		}

		// 更新処理
		//■DAOのsearchメソッドを呼びだしてBDにアクセスし、searchメソッドの戻り値で検索結果を返す
		//SELECT文のLIKEなどで複数のレコードを返す場合は、List型で受け取る（searchメソッド内でArrayListに格納し戻り値で返している）

		JobDAO dao = new JobDAO();
		
		boolean result = dao.update(company, prefecture, address, job_type, s_benefit, s_holiday,
				employment, pdf, code);

		if (result == true) {
			return "jobUpdateSuccess.jsp";//更新成功画面
		} else {
			request.setAttribute("errorMessage", "更新に失敗しました。もう一度お試しください。");
			return "jobList.jsp"; // 求人情報一覧画面に遷移
		}
	}


	// 空白またはnull判定用の共通メソッド
	private void checkEmptyFields(String[] fieldNames, String[] fieldValues, List<String> errorMessages) {
		for (int i = 0; i < fieldValues.length; i++) {
			if (isNullOrEmpty(fieldValues[i])) {
				errorMessages.add(fieldNames[i] + "は必須項目です。"); // どのフィールドが空白かも指摘
			}
		}
	}

	// 空白またはnull判定用のメソッド
	private boolean isNullOrEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}

	// 文字数制限をチェックするメソッド
	private void checkFieldLength(String[] fieldNames, String[] fieldValues, List<String> errorMessages) {
		int[] maxLengths = { 100, 100, 50 }; // 会社名: 100, 住所: 100, 職種: 50
		for (int i = 0; i < fieldValues.length; i++) {
			if (fieldValues[i].length() > maxLengths[i]) {
				errorMessages.add(fieldNames[i] + "は" + maxLengths[i] + "文字以内で入力してください。");
			}
		}
	}

	// 数値として有効かつ1以上の整数を確認するヘルパーメソッド
	private void validatePositiveIntegers(String[] fieldNames, String[] fieldValues, List<String> errorMessages) {
		for (int i = 0; i < fieldValues.length; i++) {
			try {
				int value = Integer.parseInt(fieldValues[i]);
				if (value <= 0) {
					errorMessages.add(fieldNames[i] + "は1以上の数値で入力してください。");
				}
			} catch (NumberFormatException e) {
				errorMessages.add(fieldNames[i] + "は数値で入力してください。");
			}
		}
	}

}