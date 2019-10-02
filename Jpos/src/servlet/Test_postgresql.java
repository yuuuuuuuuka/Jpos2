/**
 * 接続練習クラス
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class Test_postgresql
 */
@WebServlet("/Test_postgresql")
public class Test_postgresql extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test_postgresql() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//		文字変換？の対処
		response.setContentType("text/HTML; charset=Shift-JIS");

		// HTML ヘッダ出力
	    PrintWriter rw = response.getWriter();
	    rw.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0//EN\">");
	    rw.println("<HTML>");
	    rw.println("<HEAD>");
	    rw.println("<META http-equiv=\"Content-Type\" content=\"text/html; charset=Shift_JIS\">");
	    rw.println("<TITLE>JAVA Servlet - PostgreSQL JDBC Test</TITLE>");
	    rw.println("</HEAD>");
	    rw.println("<BODY>");
	    rw.println("<P>JAVA Servlet - PostgreSQL JDBC Test</P>");

	    try {

	      // PostgreSQL JDBC ドライバロード
	      Class.forName("org.postgresql.Driver");

	      // PostgreSQL JDBC 接続
	      String url = "jdbc:postgresql://192.168.37.60:5432/aisdb";
	        String user = "juser";
	        String password = "juser";
	      Connection cn = DriverManager.getConnection(url ,user, password);


	      // PostgreSQL JDBC 問い合わせ SQL 作成
	      String sql = "select * from osi2_table;";
	      Statement st = cn.createStatement();

	      // PostgreSQL JDBC レコードセットオープン
	      ResultSet rs = st.executeQuery(sql);

	      // PostgreSQL JDBC レコードセットリード
	      while (rs.next()) {
	    	rw.println("<P>-----------------------------------------</P>");
	        rw.println("<P> ID = " + rs.getString(1) + "</P>" );
	        rw.println("<P>NAME =" + rs.getString(2) + "</P>" );
	        rw.println("<P>PASS =" + rs.getString(3) +"</P>");
	        rw.println("<P>-----------------------------------------</P>");
	      }

	      // PostgreSQL JDBC レコードセットクローズ
	      rs.close();

	      // PostgreSQL JDBC ステートメントクローズ
	      st.close();

	      // PostgreSQL JDBC 接続クローズ
	      cn.close();

	    }
	    catch (Exception e) {

	      // エラー処理
	      rw.println("<P>error</P>");

	    }

	    // HTML テイル出力
	    rw.println("</BODY>");
	    rw.println("</HTML>");

	  }

	}