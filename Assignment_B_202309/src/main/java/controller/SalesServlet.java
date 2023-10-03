package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Product;
import model.Sales;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import dao.ProductDao;
import dao.SalesDao;

/**
 * 販売データを管理するサーブレットの実装クラスです。
 */

@WebServlet("/Sales")
public class SalesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<Sales> saleList = new ArrayList<Sales>();

	 /**
     * デフォルトコンストラクタ
     */
	public SalesServlet() {
		super();
		// TODO Auto-generated constructor stub

	}

	/**
     * HTTP GET リクエストを処理するメソッドです。
     *
     * @param request  HttpServletRequest オブジェクト。
     * @param response HttpServletResponse オブジェクト。
     * @throws ServletException サーブレットの例外。
     * @throws IOException      入出力例外。
     */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) // GET リクエストの処理
	        throws ServletException, IOException {
	    // TODO Auto-generated method stub

	    // セッション内の販売リストを初期化
	    Map<String, ArrayList<String>> salesList = new HashMap<String, ArrayList<String>>();

	    // クエリ文字列がない場合、セッション内の販売リストを初期化し、今日の販売データを取得し、すべての商品情報を取得してから"IndexSales.jsp" にフォワード
	    if (request.getQueryString() == null) {
	        request.getSession().setAttribute("salesList", salesList);
	        getAllTodaysSale(request, response);
	        getAllProducts(request, response);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/view/IndexSales.jsp");
	        dispatcher.forward(request, response);
	        return;
	    } else {
	        // クエリ文字列がある場合、商品 ID と販売数量を取得
	        String productId = request.getParameter("product-id");
	        String saleAmount = request.getParameter("sale-amount");

	        // セッション内の販売リストを取得
	        Map<String, ArrayList<String>> sessionSaleList = new HashMap<String, ArrayList<String>>();
	        sessionSaleList = (HashMap<String, ArrayList<String>>) request.getSession().getAttribute("salesList");
	        if (productId != null && !productId.isEmpty() && saleAmount != null && !saleAmount.isEmpty()) {
	            // 商品 ID と販売数量が有効な値の場合、販売データをセッション内の販売リストに追加

	            System.out.print(productId + " " + saleAmount);
	            int productCode = Integer.parseInt(productId);
	            ProductDao productDao = new ProductDao();
	            Product currentProduct = productDao.findOne(productCode);

	            if (sessionSaleList != null) {
	                // セッション内の販売リストを取得
	                salesList.putAll(sessionSaleList);
	            }

	            if (salesList.containsKey(productId)) {
	                // 商品 ID がすでに存在する場合、販売数量を追加
	                int existingSaleAmount = Integer.parseInt(salesList.get(productId).get(1));
	                int newSaleAmount = Integer.parseInt(saleAmount);
	                int totalSaleAmount = existingSaleAmount + newSaleAmount;
	                salesList.get(productId).set(1, Integer.toString(totalSaleAmount));
	            } else {
	                // 新しい商品 ID をキーとして、商品名と販売数量をリストに追加
	                salesList.put(productId, new ArrayList<String>());
	                salesList.get(productId).add(currentProduct.getProductName());
	                salesList.get(productId).add(saleAmount);
	            }

	            // 販売リストをセッションに保存
	            request.getSession().setAttribute("salesList", salesList);

	            // 今日の販売データとすべての商品情報を取得してから "IndexSales.jsp" にフォワード
	            getAllTodaysSale(request, response);
	            getAllProducts(request, response);
	            RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/view/IndexSales.jsp");
	            dispatcher.forward(request, response);
	            return;

	        } else {
	            // 有効な販売数量が指定されていない場合、フォワード
	            getAllTodaysSale(request, response);
	            getAllProducts(request, response);
	            RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/view/IndexSales.jsp");
	            dispatcher.forward(request, response);
	        }
	    }
	}

		
	
	/**
     * HTTP POST リクエストを処理するメソッドです。
     *
     * @param request  HttpServletRequest オブジェクト。
     * @param response HttpServletResponse オブジェクト。
     * @throws ServletException サーブレットの例外。
     * @throws IOException      入出力例外。
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// POST リクエストの処理
		request.setCharacterEncoding("UTF-8");
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
		
		// セッション内の販売リストを取得
		Map<String, ArrayList<String>> sessionSaleList = new HashMap<String, ArrayList<String>>();
		sessionSaleList = (HashMap<String, ArrayList<String>>) request.getSession().getAttribute("salesList");

		if (sessionSaleList != null) {
			 // セッション内の販売リストをループ処理
			sessionSaleList.forEach((key, value) -> {
				SalesDao saleDao = new SalesDao();
				ArrayList<Sales> currentSaleList = saleDao.getAllTodaysSales();
				int productCode = Integer.parseInt(key);
	            int newSaleAmount = Integer.parseInt(value.get(1));
				//Sales singleSale = new Sales(Integer.parseInt(key), Integer.parseInt(value.get(1)), timestamp.toString());
	            Sales singleSale = new Sales(productCode, newSaleAmount, timestamp.toString());
				if (currentSaleList == null || currentSaleList.size() == 0) {
					// 今日の販売データがない場合、新しい販売データを挿入
					saleDao.insertOne(singleSale);
				} else {
					boolean matchFound = false;
					for (Sales sale : currentSaleList) {
						if (sale.getProductCode() == productCode) {
							matchFound = true;
							sale.setQuantity(sale.getQuantity() + newSaleAmount);
		                    saleDao.UpdateOne(sale);
		                    break; // Exit the loop once updated
						}
					}
					if (!matchFound) {
						 // 既存の販売データがある場合、販売データを更新
						Sales singleOnlySale = new Sales(productCode, newSaleAmount, timestamp.toString());
						saleDao.insertOne(singleOnlySale);
					}
				}

			});
			
			// セッション内の販売リストを空のリストで初期化
			Map<String, ArrayList<String>> salesList = new HashMap<String, ArrayList<String>>();
			request.getSession().setAttribute("salesList", salesList);
			
			// 今日の販売データとすべての商品情報を取得
			getAllTodaysSale(request, response);
			getAllProducts(request, response);
			
			// 成功メッセージを設定
			request.setAttribute("successForSales", "販売はデータベースに正常に保存されます。");
			
			// "IndexSales.jsp" にフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/view/IndexSales.jsp");
			dispatcher.forward(request, response);
		} else {
			// セッション内の販売リストが空の場合、または処理に失敗した場合
			
			// 今日の販売データとすべての商品情報を取得
			getAllTodaysSale(request, response);
			getAllProducts(request, response);
			
			//request.setAttribute("failure", "販売はデータベースに正常に保存されせん。");
			
			// "IndexSales.jsp" にフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/view/IndexSales.jsp");
			dispatcher.forward(request, response);
		}

	}

	 /**
     * すべての商品情報をデータベースから取得し、リクエスト属性に設定します。
     *
     * @param request  HttpServletRequest オブジェクト。
     * @param response HttpServletResponse オブジェクト。
     */
	
	private void getAllProducts(HttpServletRequest request, HttpServletResponse response) {
		ProductDao productDao = new ProductDao();
		ArrayList<Product> productList = productDao.getAllProduct();
		request.setAttribute("product_list", productList);
	}
	
	/**
     * 今日の販売データをデータベースから取得し、リクエスト属性に設定します。
     *
     * @param request  HttpServletRequest オブジェクト。
     * @param response HttpServletResponse オブジェクト。
     */
	
	private void getAllTodaysSale(HttpServletRequest request, HttpServletResponse response) {
		SalesDao saleDao = new SalesDao();
		saleList = saleDao.getAllTodaysSales();
		request.setAttribute("todays_sales", saleList);
	}
}
