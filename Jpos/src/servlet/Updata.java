/**
 * 更新クラス
 * @author yuuka
 *
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
 * Servlet implementation class Updata
 */
@WebServlet("/Updata")
public class Updata extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Updata() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		//getParameterでjsp入力を取得
				request.setCharacterEncoding("Shift-JIS");
				//jspから持ってきた入力値を出力
				String osi_id = request.getParameter("id");
				String osi_name = request.getParameter("name");
				String osi_code = request.getParameter("code");
				//確認出力
				System.out.println("id: " + osi_id);
				System.out.println("name: " + osi_name);
				System.out.println("code: " + osi_code);



		/**
		 * DB接続情報の取得
		 */

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
			 * SQL実行
			 */

			//名前でSelect
			String sql = "select * from osi2_table where name like '%" + osi_name + "%';";
			System.out.println(sql);
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
			request.setAttribute("Up", array); //request.setAttribute("属性名", [渡す変数]);
			RequestDispatcher dispatch = request.getRequestDispatcher("./UpData.jsp");//受け渡し先指定
			dispatch.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	//ここまでDB処理

}
