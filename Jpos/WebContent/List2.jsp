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
		<p>���O����</p>
		<form action="./Select" method="post">
			<input type="text" name="name"> <input type="submit"
				value="����">
		</form>



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

				</form>
				<td><%=osi.getOsi2_id()%></td>
				<td><%=osi.getOsi2_name()%></td>
				<td><%=osi.getOsi2_code()%></td>
				<td>
					<form action="./UpData" method="post">
						<input type="submit" value="�ҏW"> <input type="hidden"
							name="name" value="<%=osi.getOsi2_name()%>">
					</form>
				</td>
				<td>
					<form action="./Dele2" method="post">
						<input type="submit" value="�폜"> <input type="hidden"
							name="name" value="<%=osi.getOsi2_name()%>">
					</form>
				</td>

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