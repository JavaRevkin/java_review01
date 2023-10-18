<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>商品登録</title>
		<script type="text/javascript">
			function check() {
				if (form.name.value == "") {
					alert("商品名を入力してください");
					return false;
				} else if (form.price.value == "") {
					alert("単価を入力してください");
					return false;
				} else return true;
 			}
		</script>
	</head>
	<body>
		<h1>商品登録</h1>
		<form method="post" name="form"><table cellspacing="18">
			<tr>
				<th align="left">商品名</th>
				<td><input maxlength="32" name="name" required style="width: 170px;" type="text"></td>
			</tr>
			<tr>
				<th align="left">単価</th>
				<td>
					<input max="999999" min="1" name="price" required style="width: 74px;" type="number">
					<input style="width: 90px;" type="submit" value="登録">
				</td>
			</tr>
		</table></form>
		<% final String pdName = (String)request.getAttribute("ProductName"); %>
		<% if (pdName != null) { %>
			<% if ((boolean)request.getAttribute("Success")) { %>
				<% if (!(boolean)request.getAttribute("UniqueName")) { %>
					<h2>既に登録されている商品名は使用できません</h2>
				<% } else if (!(boolean)request.getAttribute("RangeCode")) { %>
					<h2>これ以上商品を登録できません</h2>
				<% } else { %>
					<h2>「<%= pdName %>」を登録しました</h2>
				<% } %>
			<% } else { %>
				<h2>データベースに接続できませんでした</h2>
			<% } %>
		<% } %>
		<a href="index">⇦商品検索に戻る</a>
	</body>
</html>