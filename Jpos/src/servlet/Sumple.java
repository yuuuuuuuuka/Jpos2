/**
 * @Webservletclass Sumple �o�^����T�[�u���b�g�N���X
 *
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
 * Servlet implementation class SampleClass
 */
@WebServlet("/Sumple")
public class Sumple extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sumple() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);//jsp�ŏo�͂̈�Get��Post�������ĂԕK�v������B

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//getParameter��jsp���͂��擾
		request.setCharacterEncoding("Shift-JIS");
		//jsp���玝���Ă������͒l���o��
		String osi_id = request.getParameter("id");
		String osi_name = request.getParameter("name");
		String osi_code = request.getParameter("code");

		System.out.println("id: " + osi_id);
		System.out.println("name: " + osi_name);
		System.out.println("code: " + osi_code);

		// -- ��������DB�֕ۑ����� --
		/**
		 * DB�ڑ����̎擾
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
		//�S�̂Ŏg�p����ϐ���`
		int osi2_id;
		String osi2_name;
		String osi2_code;

		try {

			// �R�l�N�V�����擾
			Connection conn = ds.getConnection();

			// �X�e�[�g�����g�̍쐬
			Statement stmt = conn.createStatement();

			/**
			 * SQL���s
			 */
			//Insert
			String sql = ("insert into osi2_table values (" + osi_id + "," + "'" + osi_name + "'," + "'" + osi_code
					+ "'" + ");");
			System.out.println(sql);
			int num = stmt.executeUpdate(sql);

			//Select
			sql = "select * from osi2_table order by id";
			ResultSet rs = stmt.executeQuery(sql);
			//ArrayList�z��Ńf�[�^���擾ArrayList<Bean�N���X��> array = new ArrayList<Bean�N���X��>();
			ArrayList<TopBean> array = new ArrayList<TopBean>();

			/**
			 * DB�f�[�^�擾��
			 */
			while (rs.next()) {
				//�����R�[�h�w��
				request.setCharacterEncoding("Shift_JIS");
				//arry��Ŏ󂯎��
				osi2_id = rs.getInt(1);
				osi2_name = rs.getString(2);
				osi2_code = rs.getString(3);
				array.add(new TopBean(osi2_id, osi2_name, osi2_code));//new��Bean�N���X��`���A�ϐ�array�Ɏ擾�����l������
			}
			//DB�����E�ڑ�������
			rs.close();
			stmt.close();

			//bean��array��Ŋۂ���setAttribute�œn��
			request.setAttribute("fromServlet", array); //request.setAttribute("������", [�n���ϐ�]);
			RequestDispatcher dispatch = request.getRequestDispatcher("./Complete.jsp");//�󂯓n����w��
			dispatch.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	//�����܂�DB����

}
