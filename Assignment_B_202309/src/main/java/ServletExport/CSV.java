package ServletExport;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.DaoS;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import bean.TotalSellByProduct;


//これがURLです
@jakarta.servlet.annotation.WebServlet("/export")
public class CSV extends jakarta.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoS dao;
	public String errors = "";
	List<TotalSellByProduct> sales = new ArrayList<TotalSellByProduct>();
	LocalDate date = java.time.LocalDate.now();
	
	//このメソッドはデータベースを呼び出し、製品のリストを取得しようとします。
	public CSV() {
        super();
        
        try {
			dao = new DaoS();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	

	//これは取得リクエストを処理するコントローラーです
	/**
	* @param request これはクライアントから送信された http リクエストです。
	* @param response これはサーバーから送信された http 応答です
	* @return このメソッドはビュー JSP ファイルに値を返します。
	*/
	protected void doGet(HttpServletRequest req,
			HttpServletResponse res)
			throws ServletException, IOException {
		
		try {
			
			res.getWriter().append("Served at: ").append(req.getContextPath());
			//これは、取得リクエストのすべての属性とデータを設定します。
			RequestDispatcher view = req.getRequestDispatcher("export.jsp");
			//req.setAttribute("products", dao.getAllProducts());
			view.forward(req, res);
			
			} catch (NullPointerException e) {
				errors = errors + "ヌルポインタ例外発見 \n";
				req.setAttribute("errors", errors);
			} catch (NumberFormatException e) {
				errors = errors + "番号フォーマットの例外が見つかった \n";
				req.setAttribute("errors", errors);
			} catch (ServletException e) {
				errors = errors + "サーブレット例外が見つかりました \n";
				req.setAttribute("errors", errors);
			} catch (IOException e) {
				errors = errors + "IO 例外が見つかった \n";
				req.setAttribute("errors", errors);
			}
		
	}
	
	/**
	 * POSTリクエストを処理します。通常、フォーム送信に使用されます。
	 * ユーザー入力を検証し、入力が有効な場合はダウンロード用のCSVファイルを生成します。
	 *
	 * @param req クライアントから送信されたHTTPリクエスト
	 * @param res サーバーから送信されたHTTPレスポンス
	 * @throws ServletException サーブレット固有のエラーが発生した場合
	 * @throws IOException リクエストの処理中にI/Oエラーが発生した場合
	 */

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		    // リクエストから「month」パラメータを抽出します。
			String date = req.getParameter("month");
			// 'date' パラメータから 'year' コンポーネントと 'month' コンポーネントを解析します。
			int year = Integer.parseInt(date.split("-")[0]);
			int month = Integer.parseInt(date.split("-")[1]);
			
			// 「年」が 2020 より小さいかどうかを確認します。2020 より小さい場合は、エラーを表示して、export.jsp に転送します。
			if(year < 2020) {
				RequestDispatcher view = req.getRequestDispatcher("export.jsp");
				errors = "This year must be greater than or equal to 2020 \n";
				req.setAttribute("errors", errors);
				view.forward(req, res);
			}
			
			// 選択した「年」と「月」が将来のものかどうかを確認します。その場合は、エラーを表示し、export.jsp に転送します。
			if((year > Integer.parseInt(java.time.LocalDate.now().toString().split("-")[0])) || (year == Integer.parseInt(java.time.LocalDate.now().toString().split("-")[0]) && 
					month > Integer.parseInt(java.time.LocalDate.now().toString().split("-")[1]))) {
				RequestDispatcher view = req.getRequestDispatcher("export.jsp");
				errors = "Month must be less than or equal to this month \n";
				req.setAttribute("errors", errors);
				view.forward(req, res);
				
			} else {
				try{
					// CSV ファイルのダウンロードを示すように応答コンテンツ タイプを設定します。
					res.setContentType("text/csv");
				    res.setHeader("Content-Disposition", "attachment; filename=\"sales_" + date + ".csv\"");
				    
				    OutputStream outputStream = res.getOutputStream(); // CSV データを書き込むための出力ストリームを取得します。
			    	int valueFound = dao.getTotalSellByMonth(date, outputStream); // 選択した「日付」に基づいて CSV データの取得と生成を試みます。
			    	
			    	// CSV データが正常に生成された場合は、エクスポート ページにリダイレクトされます。
			    	if(valueFound == 1) {
			    		
			    		res.sendRedirect("export");
			    	} else {
			    		// CSV データが見つからない場合は、エラー ページにリダイレクトするか、問題をログに記録します。
			    		System.out.println("No data found");
			    		res.sendRedirect("error");
			    	}
			        
			    }catch (NullPointerException e) {
					errors = errors + "ヌルポインタ例外発見 \n";
					req.setAttribute("errors", errors);
				} catch (NumberFormatException e) {
					errors = errors + "番号フォーマットの例外が見つかった \n";
					req.setAttribute("errors", errors);
				} catch (IOException e) {
					errors = errors + "IO 例外が見つかった \n";
					req.setAttribute("errors", errors);
				} catch (SQLException e) {
					errors = errors + "SQL例外が見つかりました \n";
					req.setAttribute("errors", errors);
				}
			}
	}
}