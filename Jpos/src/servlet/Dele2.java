/**
 * @WebServletclass Dele2 データを削除するサーブレットクラス
 * @author yuuka
 */
package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import Bean.TopBean;

/**
 * Servlet implementation class Dele2
 */
@WebServlet("/Dele2")
public class Dele2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Dele2() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//getParameterでjsp入力を取得
		request.setCharacterEncoding("Shift-JIS");
		String osi_name = request.getParameter("name");
		System.out.println(osi_name);
		//DB接続
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
		//全体で使用する変数定義
		int osi2_id;
		String osi2_name;
		String osi2_code;

		try {

			// コネクション取得
			Connection conn = ds.getConnection();

			// ステートメントの作成
			Statement stmt = conn.createStatement();

			/**
			 * SQL実行delete from osi2_table where id='12';
			 */
			//Delete
			String sql = ("delete from osi2_table where name='" + osi_name + "';");
			System.out.println(sql);
			int num = stmt.executeUpdate(sql);
			//Select削除確認
			sql = "select * from osi2_table order by id";
			ResultSet rs = stmt.executeQuery(sql);
			//ArrayList配列でデータを取得ArrayList<Beanクラス名> array = new ArrayList<Beanクラス名>();
			ArrayList<TopBean> array = new ArrayList<TopBean>();

			/**
			 * DBデータ取得回す
			 */
			while (rs.next()) {
				//文字コード指定
				request.setCharacterEncoding("Shift_JIS");
				//arry列で受け取る
				osi2_id = rs.getInt(1);
				osi2_name = rs.getString(2);
				osi2_code = rs.getString(3);
				array.add(new TopBean(osi2_id, osi2_name, osi2_code));
				//				array.add(new TopBean(osi2_id, osi2_name, osi2_code));//newでBeanクラス定義し、変数arrayに取得した値を入れる
			}
			//DBを閉じる・接続も閉じる
			rs.close();
			stmt.close();

			//beanはarray列で丸ごとsetAttributeで渡す
			request.setAttribute("Dele2", array); //request.setAttribute("属性名", [渡す変数]);
			RequestDispatcher dispatch = request.getRequestDispatcher("./Delcom.jsp");//受け渡し先指定
			dispatch.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	//ここまでDB処理

}
