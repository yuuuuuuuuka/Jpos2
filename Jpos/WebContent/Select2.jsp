<%@ page contentType="text/html;charset=Shift_JIS"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="Bean.TopBean"%>

<%
	// Post���\�b�h�ł�Servlet�f�[�^�󂯎��
	request.setCharacterEncoding("Shift_JIS");
	java.util.List<TopBean> str = (java.util.List<TopBean>) request.getAttribute("Select");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=" Shift_JIS" />
<title>����</title>
</head>
<body>

	<center>
		<p>���O����</p>
		<form action="./Select" method="post">
			<input type="text" name="name"> <input type="submit"
				value="����">
		</form>

		<h2>��������</h2>
		<table>
			<tr>
				<th>����ID</th>
				<th>���O</th>
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
		<button type="button" onclick="history.back()">�߂�</button>
		<a href="http://localhost:8082/Jpos/Top.jsp"><button type="button">�ҏW��ʂ�</button></a>
	</center>
</body>
</html>