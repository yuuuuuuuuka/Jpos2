/**
 * 接続練習クラス(サーブレット出力)
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
 * Servlet implementation class TmpDb
 */
@WebServlet("/TmpDb")
public class TmpDb extends HttpServlet {
	private static final long serialVersionUID = 1L;



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		//文字コード変換
		response.setContentType("text/html; charset=Shift-JIS");

		PrintWriter rw = response.getWriter();
//		rw.println("<html>");
//		rw.println("<body>");
//		rw.println("<p>一覧を確認</p>");
//		rw.println("<form action=\"./Select\" method=\"post\">\r\n" +
//				"<input type=\"text\" name =\"name\">\r\n" +
//				"<input type=\"submit\" value =\"検索\">\r\n" +
//				"</form>");
//		rw.println("</body>");
//		rw.println("</html>");

		//getParameterで入力を取得？
		//request.setCharacterEncoding("Shift-JIS");
        System.out.println("id: " + request.getParameter("id"));
        System.out.println("name: " + request.getParameter("name"));
        System.out.println("code: " + request.getParameter("code"));
		//jspから持ってきた入力値を出力
		String osi_id = request.getParameter("id");
		String osi_name = request.getParameter("name");
		String osi_code = request.getParameter("code");

		System.out.println("id: " + osi_id);
		System.out.println("name: " + osi_name);
		System.out.println("code: " + osi_code);

        // -- ここからDBへ保存処理 --

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

     			//インサート
     			//String sql = ("insert into osi2_table values (" +  osi_id + "," +"'" + osi_name + "'," + "'"+ osi_code +"'"+");");
     			//System.out.println(sql);
     			//int num = stmt.executeUpdate(sql);
     			//セレクト
     		      String sql = "select * from osi2_table";
     		      //sql = "select * from osi2_table";
     		      ResultSet rs = stmt.executeQuery(sql);


     			// データ取得、出力
     			while (rs.next()) {

     				//①StringBuilderクラスを使った文字列の追加処理出力の場合
     				//＊対象文字列.append(追加対象 )＊
//     				response.getWriter().append(rs.getString("id"));
//     				response.getWriter().append(rs.getString("name"));
//     				response.getWriter().append(rs.getString("code"));

     				//②上記使わずHTML方式で出力の場合
     				//PrintWriter rw = response.getWriter();
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
     		}//ここまでDB処理

     	//戻るボタン
     	rw.println("<button type=\"button\" onclick=\"history.back()\">戻る</button>\r\n" +
     			"	<a href=\"http://localhost:8082/Jpos/Top.jsp\"><button type=\"button\">編集画面へ</button></a>");
     	}



}
