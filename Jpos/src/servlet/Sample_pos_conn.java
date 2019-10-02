package servlet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Sample_pos_conn {


			private static Connection conn = null;
	        private static Statement stmt = null;
	        private static ResultSet rset = null;
	        private static DataSource ds = null;
	        private static NamingException namingException = null;

	        //�ڑ�������
//	        String url = "jdbc:postgresql://192.168.37.60:5432/aisdb";����
//	        String user = "juser";
//	        String password = "juser";
	    static {
	        InitialContext ctx;

	        try {
	            ctx = new InitialContext();
	            ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/aisdb");
	        } catch (NamingException e) {
	            e.printStackTrace();
	            namingException = e;
	            }
	        }

	    /*** DB�ڑ��R�l�N�V�������擾���܂��B**/

	    protected Connection getConnection() throws NamingException, SQLException {
	        if (ds != null) {
	        Connection con =null;
	        	try{
	            //PostgreSQL�֐ڑ�
//	            conn = DriverManager.getConnection(url, user, password);
	        		con = ds.getConnection();

	            //SELECT���̎��s
	            stmt = conn.createStatement();
	            String sql = "select id from osi2_table; ";
	            rset = stmt.executeQuery(sql);

	            //SELECT���ʂ̎󂯎��
	            while(rset.next()){
	                String col = rset.getString(1);
	                System.out.println(col);
	            }

	            //INSERT���̎��s
	            sql = "insert into osi2_table values (13,'�R�u','codedef'); ";
	            stmt.executeUpdate(sql);
	            conn.commit();
	        }
	        catch (SQLException e){
	            e.printStackTrace();
	        }
	        finally {
	            try {
	                if(rset != null)rset.close();
	                if(stmt != null)stmt.close();
	                if(conn != null)conn.close();
	            }
	            catch (SQLException e){
	                e.printStackTrace();
	            }

	        }
	    }
			return null;

	}
}
