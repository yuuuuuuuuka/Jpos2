/**
 * 接続練習クラス
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
/**
 * Servlet implementation class Tmp1
 */
@WebServlet(description = "HelloWorldの出力", urlPatterns = { "/Tmp1" })
public class Tmp1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html; charset=Shift-JIS");

		// データソース情報の取得
		Context context = null;
		DataSource ds = null;
		try {
			context = new InitialContext();
			ds = (DataSource) context.lookup("java:/comp/env/jdbc/pos");
		} catch (NamingException e) {
			e.printStackTrace();
			response.getWriter().append(e.toString());
			return;
		}

	try {
			// コネクション取得
			Connection conn = ds.getConnection();

			// ステートメントの作成
			Statement stmt = conn.createStatement();

			// SQLの実行

			//ResultSet sql = stmt.executeUpdate("insert into osi2_table values (44,'愛','sssss')");
			String sql = ("insert into osi2_table values (88,'山口','celenes')");
		      int num = stmt.executeUpdate(sql);

		      sql = "select * from osi2_table";
		      ResultSet rs = stmt.executeQuery(sql);

//			ResultSet rs = stmt.executeQuery("select * from osi2_table");




			// データ取得、出力
			while (rs.next()) {

				//①StringBuilderクラスを使った文字列の追加処理出力の場合
				//＊対象文字列.append(追加対象 )＊
//				response.getWriter().append(rs.getString("id"));
//				response.getWriter().append(rs.getString("name"));
//				response.getWriter().append(rs.getString("code"));

				//②上記使わずHTML方式で出力の場合
				PrintWriter rw = response.getWriter();
				rw.println("<P>-----------------------------------------</P>");
				rw.println("<P> ID = " + rs.getString(1) + "</P>" );
				rw.println("<P>NAME =" + rs.getString(2) + "</P>" );
				rw.println("<P>PASS =" + rs.getString(3) +"</P>");
				rw.println("<P>-----------------------------------------</P>");

				response.getWriter().append("<BR>");
			}
			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}