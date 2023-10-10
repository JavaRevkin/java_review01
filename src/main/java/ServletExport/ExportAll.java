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

    public ExportAll() {
        super();

        try {
            dao = new DaoS();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            res.setContentType("text/csv; charset=Shift_JIS");
            res.setHeader("Content-Disposition", "attachment; filename=\"sales_" + date + ".csv\"");
            OutputStream outputStream = res.getOutputStream();

            OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.ISO_8859_1);

            PrintWriter out = new PrintWriter(writer, false); 
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
            
            int valueFound = dao.getTotalSellByProduct(outputStream);
            if (valueFound == 1) {
                res.sendRedirect("export");
            } else {
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
