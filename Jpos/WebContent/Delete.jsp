<%@ page language="java" contentType="text/html; charset=Shift_JIS"
    pageEncoding="Shift_JIS"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="Shift_JIS">
<title>Delete</title>
</head>
<body>

<p>削除したい人の名前を入力してください。</p>
<form action="./Dele2" method="post">
<input type="text" name ="name">
<input type="submit" value ="削除">
</form>
<!-- DELETE FROM user WHERE id="1";  -->
</body>
</html>