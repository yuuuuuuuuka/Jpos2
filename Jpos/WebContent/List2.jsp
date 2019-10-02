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
	java.util.List<TopBean> str = (java.util.List<TopBean>) request.getAttribute("List");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=" Shift_JIS" />
<title>test</title>
</head>
<body>
	<center>
		<p>名前検索</p>
		<form action="./Select" method="post">
			<input type="text" name="name"> <input type="submit"
				value="検索">
		</form>



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

				</form>
				<td><%=osi.getOsi2_id()%></td>
				<td><%=osi.getOsi2_name()%></td>
				<td><%=osi.getOsi2_code()%></td>
				<td>
					<form action="./UpData" method="post">
						<input type="submit" value="編集"> <input type="hidden"
							name="name" value="<%=osi.getOsi2_name()%>">
					</form>
				</td>
				<td>
					<form action="./Dele2" method="post">
						<input type="submit" value="削除"> <input type="hidden"
							name="name" value="<%=osi.getOsi2_name()%>">
					</form>
				</td>

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