<%@ page contentType="text/html;charset=Shift_JIS"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="Bean.TopBean"%>

<%
	// PostメソッドでのServletデータ受け取り
	request.setCharacterEncoding("Shift_JIS");
	java.util.List<TopBean> str = (java.util.List<TopBean>) request.getAttribute("Select");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=" Shift_JIS" />
<title>結果</title>
</head>
<body>

	<center>
		<p>名前検索</p>
		<form action="./Select" method="post">
			<input type="text" name="name"> <input type="submit"
				value="検索">
		</form>

		<h2>検索結果</h2>
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