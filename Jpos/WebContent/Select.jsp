<%@ page language="java" contentType="text/html; charset=Shift_JIS"
	pageEncoding="Shift_JIS"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="Shift_JIS">
<title>Delete</title>
</head>
<body>

	<p>検索したい人の名前を入力してください。</p>
	<form action="./Select" method="post">
		<p>
			NAME:<input type="text" name="name">
		</p>
		<input type="submit" value="検索">
	</form>
</body>
</html>