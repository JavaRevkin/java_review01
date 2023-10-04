package ServletExport;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets; 
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

@jakarta.servlet.annotation.WebServlet("/exportall")
public class ExportAll extends jakarta.servlet.http.HttpServlet {
    private static final long serialVersionUID = 1L;
    private DaoS dao;
    public String errors = "";
    List<TotalSellByProduct> sales = new ArrayList<TotalSellByProduct>();
    LocalDate date = java.time.LocalDate.now();

    // ExportAll サーブレットのコンストラクター。
    public ExportAll() {
        super();

        try {
            dao = new DaoS();
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // ClassNotFoundException を処理します
        } catch (SQLException e) {
            e.printStackTrace(); // SQLException を処理します
        }
    }
    
    // GET リクエストを処理します。
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
        	// レスポンスのコンテンツタイプを設定し、CSV形式を指定します。
            res.setContentType("text/csv; charset=Shift_JIS");
            res.setHeader("Content-Disposition", "attachment; filename=\"sales_summary" + date + ".csv\"");
            OutputStream outputStream = res.getOutputStream();
            // Shift_JIS エンコーディングで応答出力ストリームに書き込むための OutputStreamWriter を作成します。
            OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.ISO_8859_1);

            PrintWriter out = new PrintWriter(writer, false); 
            // CSVヘッダーを書き込みます。
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
            
            // 販売副産物の合計をデータベースから取得し、応答に書き込みます。
            int valueFound = dao.getTotalSellByProduct(outputStream);
            
            // データが見つかった場合は、「エクスポート」ページにリダイレクトします。
            if (valueFound == 1) {
                res.sendRedirect("export");
            } else {
            	// データが見つからない場合は、エラー メッセージを含む "export.jsp" ページに転送します。
                RequestDispatcher view = req.getRequestDispatcher("export.jsp");
                errors = "何もデータが見つかりませんでした。\n";
                req.setAttribute("errors", errors);
                view.forward(req, res);
            }

        } catch (NullPointerException e) {
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
