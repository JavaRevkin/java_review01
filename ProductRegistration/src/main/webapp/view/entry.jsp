<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>売上登録</title>
		<script type="text/javascript">
			function check() {
				if (form.code.value == "") {
					alert("商品を選択してください");
					return false;
				} else if (form.quantity.value == "") {
					alert("数量を入力してください");
					return false;
				} else return true;
 			}
		</script>
	</head>
	<body>
		<h1>売上登録</h1>
		<% if ((boolean)request.getAttribute("Success")) { %>
			<% String currentDate = new SimpleDateFormat("yyyy/MM/dd").format((Date)session.getAttribute("CurrentDate")); %>
			<% if ((boolean)request.getAttribute("Unregistered")) { %>
				<% if (!(boolean)request.getAttribute("Completed")) { %>
					<form method="post" name="form"><table cellspacing="18">
						<tr>
							<th align="left">売上日</th>
							<td><%= currentDate %></td>
						</tr>
						<tr>
							<th align="left">商品名</th>
							<td><select name="code" style="width: 178px;">
			  					<option value="" selected>商品を選択してください</option>
								<% final Map<String, String> pdNames = (Map<String, String>)session.getAttribute("ProductNames"); %>
								<% for (Map.Entry<String, String> product : pdNames.entrySet()) { %>
									<option value=<%= product.getKey() %>><%= product.getValue() %></option>
								<% } %>
							</select></td>
						</tr>
						<tr>
							<th align="left">数量</th>
							<td>
								<input max="999999" min="1" name="quantity" required style="width: 74px;" type="number">
								<input name="action" onClick="return check();" style="width: 90px;" type="submit" value="追加">
							</td>
						</tr>
					</table></form>
					<hr>
					<table border="1" style="border-collapse: collapse; margin: 20px;">
				   		<tr>
							<th style="min-width: 200px;">商品名</th>
							<th style="width: 70px;">数量</th>
							<th>操作</th>
						</tr>
						<% final Map<String, Integer> pdQuantities = (Map<String, Integer>)session.getAttribute("ProductQuantities"); %>
						<% for (Map.Entry<String, Integer> sales : pdQuantities.entrySet()) { %>
							<tr>
								<td><%= pdNames.get(sales.getKey()) %></td>
								<td align="right"><%= sales.getValue() %></td>
								<td><form method="post">
									<input name="code" type="hidden" value=<%= sales.getKey() %>>
									<input name="action" type="submit" value="削除">
								</form></td>
							</tr>
						<% } %>
					</table>
					<form method="post" style="margin: 20px;">
						<input name="action" style="width: 90px;" type="submit" value="登録">
					</form>
				<% } else { %>
					<h2><%= currentDate %>の売上の登録が完了しました</h2>
				<% } %>
			<% } else { %>
				<h2><%= currentDate %>の売上は既に登録されています</h2>
			<% } %>
		<% } else { %>
			<h2>データベースに接続できませんでした</h2>
		<% } %>
		<a href="index">⇦商品検索に戻る</a>
	</body>
</html>