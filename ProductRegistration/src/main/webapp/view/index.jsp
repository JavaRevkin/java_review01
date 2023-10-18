<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="pack.UpdateProduct.Product" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>商品検索</title>
		<script type="text/javascript">
			function check() {
				if (form.query.value == "") {
					alert("商品名を入力してください");
					return false;
				} else return true;
			}
		</script>
	</head>
	<body>
		<h1><a href="index">商品検索</a></h1>
		<form method="get" name="form" style="margin: 20px;">
  			商品名：<input maxlength="32" name="query" required type="text" value=<%= (String)request.getAttribute("Query") %>>
			<input type="submit" value="検索">
		</form>
		<% if ((boolean)request.getAttribute("Success")) { %>
			<% final List<Product> products = (List<Product>)request.getAttribute("Products"); %>
			<% if (!products.isEmpty()) { %>
				<table border="1" style="border-collapse: collapse; margin: 20px;">
			   		<tr>
						<th>商品コード</th>
						<th style="min-width: 200px;">商品名</th>
						<th style="width: 70px;">単価</th>
						<th>操作</th>
					</tr>
					<%! final NumberFormat ni = NumberFormat.getNumberInstance(); %>
					<% for (Product pd : products) { %>
						<tr>
							<td align="middle"><%= pd.code() %></td>
							<td><%= pd.name() %></td>
							<td align="right"><%= ni.format(pd.price()) %></td>
							<td><form action="edit" method="get">
								<input name="code" type="hidden" value=<%= pd.code() %>>
								<input type="submit" value="編集">
							</form></td>
						</tr>
					<% } %>
				</table>
			<% } else { %>
				<h2>該当する商品が見付かりませんでした</h2>
			<% } %>
		<% } else { %>
			<h2>データベースに接続できませんでした</h2>
		<% } %>
		<a href="add">商品を登録する</a><br>
		<a href="entry">売上を登録する</a><br>
		<a href="output">データをダウンロードする</a>
	</body>
</html>