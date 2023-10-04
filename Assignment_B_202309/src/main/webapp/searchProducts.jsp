<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Search</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <style>
        body {
            background-color: #d3d3d3;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            color: #007bff;
        }

        .button-container {
            text-align: center;
            margin-top: 20px;
        }

        .form-container {
            text-align: center;
            margin-top: 10px;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .form-group {
            margin-right: 30px;
            position: relative;
        }

        .form-group input {
            padding-right: 30px; /* Add some space for the clear icon */
        }

        .clear-icon {
            position: absolute;
            right: 10px; /* Adjust the position as needed */
            top: 50%;
            transform: translateY(-50%);
            cursor: pointer;
        }

        .table-container {
            margin: 0 auto;
            margin-top: 10px;
            max-width: 800px;
        }

        .col-code {
            width: 20%;
            text-align: center;
        }

        .col-name {
            width: 20%;
            text-align: left;
        }

        .col-price {
            width: 20%;
            text-align: right;
        }
        .col-price-data {
            text-align: right; 
        }

        .col-action {
            width: 20%;
            text-align: center;
        }

        .upper-section {
            display: flex;
            justify-content: center;
        }

        #search {
            max-width: 200px;
        }
    </style>
    <script>
        // JavaScript function to clear the search input
        function clearSearch() {
            document.getElementById('search').value = '';
        }
    </script>
</head>

<body>
<h1>製品検索</h1>
<div class="button-container">
    <button class="btn btn-lg btn-primary" id="add-product-button" onclick="location.href='addproduct'">製品登録</button>
    <button class="btn btn-lg btn-primary" id="daily-sales-button" onclick="location.href='addsales'">売上登録</button>
    <button class="btn btn-lg btn-primary" id="export-button" onclick="location.href='export'">CSVのエクスポート</button>
</div>
<br>
<div class="form-container">
    <form method="get" action="search" class="form-inline">
        <div class="form-group mb-2">
            <label for="search">商品名 &nbsp;&nbsp;</label>
            <input type="text" id="search" name="search" value="${search}" class="form-control" maxlength="50"/>
            <span class="clear-icon" onclick="clearSearch()">✖</span> 
        </div>
        <button type="submit" class="btn btn-outline-primary">検索</button>
    </form>
</div>
<h3 style="text-align: center;"><c:out value="${errors} "/></h3>
<div class="table-container">
    <table class="table table-bordered">
        <thead class="thead-dark">
        <tr>
            <th scope="col" class="col-code">商品コード</th>
            <th scope="col" class="col-name text-center">商品名</th>
            <th scope="col" class="col-price text-center">単価</th>
            <th scope="col" class="col-action">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${products}" var="product">
            <tr>
                <td scope="col" class="col-code"><c:out value="${product.productCode} "/></td>
                <td scope="col" class="col-name"><c:out value="${product.productName} "/></td>
                <td scope="col" class="col-price"><fmt:formatNumber value="${product.price}" pattern="#,##0" /></td>
                
                <td scope="col" class="col-action">
                    <form method="get" action="update">
                        <button type="submit" class="btn btn-outline-primary" name="product_code"
                                value="${product.productCode}">編集
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
