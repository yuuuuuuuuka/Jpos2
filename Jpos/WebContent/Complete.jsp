<%@ page contentType="text/html;charset=Shift_JIS"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="Bean.TopBean"%>
<%
	//Bean���p�̎w��^�O
%>
<jsp:useBean id="topBean" class="Bean.TopBean" scope="request" />

<%
	// Post���\�b�h�ł�Servlet�f�[�^�󂯎��
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

		<p>�o�^�������v���܂����B</p>
		<br>

		<h2>�o�^�҈ꗗ</h2>

		<!-- Post���\�b�h ID,name,code-->

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