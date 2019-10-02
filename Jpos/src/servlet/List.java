/**
 * @WebServletclass List.java
 *  �ꗗ��\������T�[�u���b�g
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
@WebServlet("/List")
public class List extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public List() {
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

		response.setContentType("text/html; charset=Shift-JIS");

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

			//������Select
			String sql = "select * from osi2_table order by id";
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
			request.setAttribute("List", array); //request.setAttribute("������", [�n���ϐ�]);
			RequestDispatcher dispatch = request.getRequestDispatcher("./List2.jsp");//�󂯓n����w��
			dispatch.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	//�����܂�DB����

}
