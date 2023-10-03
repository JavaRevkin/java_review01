package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Sales;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import dao.SalesDao;

/**
 * サーブレットの実装クラスで、売上レポートを生成する機能を提供します。
 */
@WebServlet("/SalesReport")
public class SalesReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SalesDao saleDao;
	
	/**
     * デフォルトコンストラクター
     */
	public SalesReportServlet() {
		super();
		saleDao = new SalesDao();
		// TODO 自動生成メソッドスタブ
	}

    /**
     * HTTP GET リクエストを処理します。
     *
     * @param request  HttpServletRequest オブジェクト。
     * @param response HttpServletResponse オブジェクト。
     * @throws ServletException サーブレット固有のエラーが発生した場合。
     * @throws IOException      I/O エラーが発生した場合。
     */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO 自動生成メソッドスタブ
		RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/view/SalesReport.jsp");		// フォワード先の指定
		dispatcher.forward(request, response);			// フォワードの実行
	}

	
	 /**
     * HTTP POST リクエストを処理します。
     *
     * @param request  HttpServletRequest オブジェクト。
     * @param response HttpServletResponse オブジェクト。
     * @throws ServletException サーブレット固有のエラーが発生した場合。
     * @throws IOException      I/O エラーが発生した場合。
     */
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");

        if (type.equals("all-by-products")) {
            ArrayList<Sales> sales = saleDao.getAllSalesForCSV();
            generateCSVResponse(request, response, "CSV_Report_For_All_Sales.csv", sales);
        } else {
            String date = request.getParameter("sales-date");

            if (!date.isEmpty() || date != "") {
                ArrayList<Sales> sales = saleDao.getSalesByDateForCSV(date);
                generateCSVResponse(request, response, "CSV_Report_For_" + date + ".csv", sales);
            }
        }
    }

    /**
     * CSV形式のレスポンスを生成し、クライアントに送信します。
     *
     * @param request  HttpServletRequest オブジェクト。
     * @param response HttpServletResponse オブジェクト。
     * @param filename 生成されるCSVファイルの名前。
     * @param sales    CSVに含める売上データのリスト。
     */
    
    private void generateCSVResponse(HttpServletRequest request, HttpServletResponse response, String filename, ArrayList<Sales> sales) {
        if (sales == null || sales.isEmpty()) {
            request.setAttribute("error", "データが存在しません。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/view/SalesReport.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
            return;
        }

        response.setHeader("Content-Type", "text/csv; charset=Shift_JIS");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

        try (PrintWriter out = response.getWriter()) {
            out.append("商品コード");
            out.append(',');
            out.append("商品名");
            out.append(',');
            out.append("単価");
            out.append(',');
            out.append("数量");
            out.append(',');
            out.append("金額");
            out.append('\n');

            for (Sales sale : sales) {
                out.append("\"" + String.format("%03d", sale.getProductCode()) + "\"");
                out.append(',');
                out.append("\"" + sale.getProductName().replace("\"", "\"\"") + "\"");
                out.append(',');
                out.append("\"" + String.valueOf(sale.getPrice()) + "\"");
                out.append(',');
                out.append("\"" + String.valueOf(sale.getQuantity()) + "\"");
                out.append(',');
                out.append("\"" + String.valueOf(sale.getQuantity() * sale.getPrice()) + "\"");
                out.append('\n');
            }
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
