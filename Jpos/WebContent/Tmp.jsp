<%@ page language="java" contentType="text/html; charset=Shift-JIS"
	pageEncoding="Shift-JIS"%>
<!DOCTYPE html>

<html>
<head>
<title>会員登録</title>

</head>
<body>

	<H1>会員登録フォーム</H1>

	<FORM ACTION="./Sumple" method="post">
		<!--<FORM ACTION="./TmpDB" method="post"> -->
		<P>ユーザーID</P>
		<INPUT type="text" name="id">
		<P>コード</P>
		<INPUT type="text" name="code">
		<P>名前</P>
		<INPUT type="text" name="name"> <INPUT type="reset"> <INPUT
			type="submit" name="送信">
	</FORM>
</body>
</html>