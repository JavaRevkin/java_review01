<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>CSVダウンロード</title>
		<script type="text/javascript">
			function check() {
				if (form.month.value == "") {
					alert("年月を入力してください");
					return false;
				} else return true;
 			}
		</script>
	</head>
	<body>
		<h1>CSVダウンロード</h1>
		<% if ((boolean)request.getAttribute("Success")) { %>
			<form method="post" name="form" style="margin: 20px;">
				<input name="action" type=submit value="商品別売上集計CSV"><br><br>
				<input name="action" onClick="return check();" style="margin-right: 20px;" type="submit" value="指定年月商品別売上集計CSV">
				年月<input min="2020-01" name="month" type="month">
			</form>
		<% } else { %>
			<h2>データベースに接続できませんでした</h2>
		<% } %>
		<a href="index">⇦商品検索に戻る</a>
	</body>
</html>