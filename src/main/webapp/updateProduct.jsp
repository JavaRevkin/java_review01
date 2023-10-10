<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Product</title>
<style>
  body {
    background-color: #d3d3d3; 
    font-family: Arial, sans-serif; 
    margin: 0;
    padding: 0;
  }

  h1 {
    color: #007bff; 
  }

  h6 {
    color: red; 
  }

  .container {
    max-width: 600px; 
    margin: 0 auto; 
    padding: 20px;
    background-color: #fff; 
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); 
  }

  label {
    font-weight: bold;
  }

  input[type="text"],
  input[type="number"] {
    width: 100%;
    padding: 10px;
    margin-bottom: 20px;
    border: 1px solid #ccc; 
    border-radius: 5px; 
  }

  .search-button-div {
    text-align: right; 
    margin-top: 20px; 
  }

  .search-button-div button {
    background-color: #007bff; 
    color: #fff; 
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }

  .search-button-div button:hover {
    background-color: #0056b3; 
  }

  input[type="submit"] {
    background-color: #007bff; 
    color: #fff; 
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }

  input[type="submit"]:hover {
    background-color: #0056b3; 
  }
</style>
</head>
<body>
<div class="container">
  <h1>Product Change/Deletion</h1>
  <h6><c:out value="${errors} "/></h6>
  商品一下 <c:out value=": ${product.productCode} "/><br><br>
  <form method="post" action="update">
    <label for="productName">商品名</label>
    <input type="text" id="productName" name="productname" value="${product.productName}" maxlength="50" required><br><br>
    <label for="price">単価&nbsp;&nbsp;&nbsp;&nbsp;</label>
    <input type="number" id="s1" name="productprice" min="1" max="1000000000" required><br><br>

    <input type="hidden" id="deletefield" name="deletefield">

    <input type="submit" value="変更">

    <input type="submit" name="delete" value="削除" onclick="deleteValueSet()">&nbsp;&nbsp;
  </form>

  <!-- Search Product button moved to the bottom right -->
  <div class="search-button-div">
    <button class="btn btn-secondary search-button" onclick="location.href ='search'">Search Product</button>
  </div>
</div>

<script type="text/javascript">
  var elem = document.getElementById("s1");
  var val = "${product.price}";
  val = val.replace(/\D/g, '');
  elem.value = parseInt(val);

  function deleteValueSet() {
    var elem = document.getElementById("deletefield");
    elem.value = "Delete";
  }
</script>
</body>
</html>
