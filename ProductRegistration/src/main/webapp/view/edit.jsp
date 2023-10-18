<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="pack.UpdateProduct.Product" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>商品変更・削除</title>
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
		<h1>商品変更・削除</h1>
		<% if ((boolean)request.getAttribute("Success")) { %>
			<% if ((boolean)request.getAttribute("InDatabase")) { %>
				<% final Product pd = (Product)request.getAttribute("Product"); %>
				<% if (pd != null) { %>
					<form method="post" name="form"><table cellspacing="18">
						<tr>
							<th align="left">商品コード</th>
							<td><%= pd.code() %></td>
						</tr>
						<tr>
							<th align="left">商品名</th>
							<td><input maxlength="32" name="name" type="text" style="width: 170px;" value=<%= pd.name() %>></td>
						</tr>
						<tr>
							<th align="left">単価</th>
							<td>
								<input max="999999" min="1" name="price" style="width: 74px;" type="number" value=<%= pd.price() %>>
								<input name="action" onClick="return check();" style="width: 42px;" type="submit" value="変更">
								<input name="action" style="width: 42px;" type="submit" value="削除">
							</td>
						</tr>
					</table></form>
					<% if (!(boolean)request.getAttribute("UniqueName")) { %>
						<h2>既に登録されている商品名は使用できません</h2>
					<% } else if (!(boolean)request.getAttribute("Modifiable")) { %>
						<h2>この商品の単価は変更できません</h2>
					<% } %>
				<% } else { %>
					<% if (!(boolean)request.getAttribute("Controllable")) { %>
						<h2>商品情報の変更を保存できませんでした</h2>
					<% } else { %>
						<h2>商品情報を更新しました</h2>
					<% } %>
				<% } %>
			<% } else { %>
				<h2>商品が存在しません</h2>
			<% } %>
		<% } else { %>
			<h2>データベースに接続できませんでした</h2>
		<% } %>
		<a href="index">⇦商品検索に戻る</a>
	</body>
</html>