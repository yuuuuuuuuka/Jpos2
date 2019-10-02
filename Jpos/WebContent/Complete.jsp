<%@ page contentType="text/html;charset=Shift_JIS"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="Bean.TopBean"%>
<%
	//Bean利用の指定タグ
%>
<jsp:useBean id="topBean" class="Bean.TopBean" scope="request" />

<%
	// PostメソッドでのServletデータ受け取り
	request.setCharacterEncoding("Shift_JIS");
	//request.getAttribute("fromServlet");
	java.util.List<TopBean> str = (java.util.List<TopBean>) request.getAttribute("fromServlet");
	//Integer strServlet = (Integer) request.getAttribute("fromServlet");//ID
	//String strServlet2 = (String) request.getAttribute("fromServletn");//name
	//String strServlet3 = (String) request.getAttribute("fromServletc");//code
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=" Shift_JIS" />
<title>test</title>
</head>
<body>
	<center>

		<p>登録が完了致しました。</p>
		<br>

		<h2>登録者一覧</h2>

		<!-- Postメソッド ID,name,code-->

		<table>
			<tr>
				<th>部署ID</th>
				<th>名前</th>
				<th>CODE</th>

			</tr>
			<%
				for (TopBean osi : str) {
			%>
			<tr>
				<td><%=osi.getOsi2_id()%></td>
				<td><%=osi.getOsi2_name()%></td>
				<td><%=osi.getOsi2_code()%></td>
			</tr>
			<%
				}
			%>
		</table>
		<br> <br>
		<button type="button" onclick="history.back()">戻る</button>
		<a href="http://localhost:8082/Jpos/Top.jsp"><button type="button">編集画面へ</button></a>
	</center>

</body>
</html>