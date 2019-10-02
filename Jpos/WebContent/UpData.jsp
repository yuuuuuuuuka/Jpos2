<%@ page language="java" contentType="text/html; charset=Shift_JIS"
	pageEncoding="Shift_JIS"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="Shift_JIS">
<title>Delete</title>
</head>
<body>

	<H1>編集フォーム</H1>

	<p>以下情報を編集します。</p>


	<p>変更後のコードを入力してください。</p>
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