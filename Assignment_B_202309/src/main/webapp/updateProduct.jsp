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
    width: 50%;
    padding: 10px;
    margin-bottom: 20px;
    border: 1px solid #ccc; 
    border-radius: 5px; 
  }

  .search-button-div {
    text-align: left; 
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
  
  
.input-container {
    display: flex;
    align-items: center;
  }

  /* Style for the label within the container */
  .input-container label {
    flex: 1;
    font-weight: bold;
  }

  /* Style for the input field within the container */
  .input-container input[type="text"],
  .input-container input[type="number"] {
    flex: 4;
    width: auto;
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 10px;
  }
  .button-container {
    display: flex;
    justify-content: flex-end; /* Align buttons to the right */
  }

  /* Style for the buttons within the container */
  .button-container input[type="submit"] {
    background-color: #CCCCCC; 
    color: #000; 
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    margin-left: 10px; /* Add margin to separate the buttons */
  }

  .button-container input[type="submit"]:hover {
    background-color: #666666; 
  }
</style>
</head>
<body>
<div class="container">
  <h1>商品変更・削除</h1>
  <h6><c:out value="${errors} "/></h6>
  <b>商品コード <span style="margin-right: 30px;"></span><c:out value="${product.productCode}" /></b><br><br>
  <form method="post" action="update">
    <!-- Add div containers for each label and input -->
    <div class="input-container">
      <label for="productName">商品名</label>
      <input type="text" id="productName" name="productname" value="${product.productName}" maxlength="50" required>
    </div>
    <div class="input-container">
      <label for="price">単価</label>
      <input type="number" id="s1" name="productprice" required>
    </div>

    <input type="hidden" id="deletefield" name="deletefield">


    <!-- Use the button-container for the buttons -->
    <div class="button-container">
  <input type="submit" name="delete" value="削除" onclick="deleteValueSet()">
  <input type="submit" value="変更">
</div>

  <div class="search-button-div">
    <button class="btn btn-secondary search-button" onclick="location.href ='search'">製品を検索</button>
  </div>
</div>

<script type="text/javascript">
  var elem = document.getElementById("s1");
  var val = "${product.price}";
  elem.value = val;

  function deleteValueSet() {
    var elem = document.getElementById("deletefield");
    elem.value = "Delete";
  }
</script>

</body>
</html>