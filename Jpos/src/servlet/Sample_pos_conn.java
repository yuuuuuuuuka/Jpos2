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

	        //接続文字列
//	        String url = "jdbc:postgresql://192.168.37.60:5432/aisdb";無線
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

	    /*** DB接続コネクションを取得します。**/

	    protected Connection getConnection() throws NamingException, SQLException {
	        if (ds != null) {
	        Connection con =null;
	        	try{
	            //PostgreSQLへ接続
//	            conn = DriverManager.getConnection(url, user, password);
	        		con = ds.getConnection();

	            //SELECT文の実行
	            stmt = conn.createStatement();
	            String sql = "select id from osi2_table; ";
	            rset = stmt.executeQuery(sql);

	            //SELECT結果の受け取り
	            while(rset.next()){
	                String col = rset.getString(1);
	                System.out.println(col);
	            }

	            //INSERT文の実行
	            sql = "insert into osi2_table values (13,'山丘','codedef'); ";
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
