<%@ page contentType="text/html;charset=Shift_JIS"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="Bean.TopBean"%>

<%
	// Post���\�b�h�ł�Servlet�f�[�^�󂯎��
	request.setCharacterEncoding("Shift_JIS");
	java.util.List<TopBean> str = (java.util.List<TopBean>) request.getAttribute("Dele2");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=" Shift_JIS" />
<title>test2</title>
</head>
<body>

	<center>
		<p>�폜���������܂����B</p>

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
		<br>
		<button type="button" onclick="history.back()">�߂�</button>
		<a href="http://localhost:8082/Jpos/Top.jsp"><button type="button">�ҏW��ʂ�</button></a>
	</center>
</body>
</html>