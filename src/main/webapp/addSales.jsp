<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Sales</title>
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

    <!-- Custom CSS -->
    <style>
        body {
            background-color: #d3d3d3;
        }

        h1 {
            text-align: center;
            margin-top: 20px;
        }

        .container {
            margin-top: 10px;
        }

        .narrow-container {
            max-width: 50%;
            margin: 0 auto;
        }

        .btn-primary {
            margin-top: 10px;
        }

        .form-label {
            font-weight: bold;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .table th,
        .table td {
            margin: 5px;
        }

        .table-bordered {
            border: 1px solid #dee2e6;
        }

        .table-responsive {
            max-width: 500px;
            margin: 20px auto;
        }

        .search-button {
            float: right;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Daily Sales Record</h1>

    <h3>売上日: ${todaysdate} <button class="btn btn-primary search-button" onclick="location.href ='search'">Product Search</button>
    </h3>

    <!-- Narrow container for the content -->
    <div class="narrow-container">
        <form method="post" action="addsales">
            <div class="form-group">
                <label class="form-label" for="productcode">商品名</label>
                <select name="productcode" id="productcode" class="form-control">
                    <c:forEach items="${products}" var="product">
                        <option value="${product.productCode} ${product.productName}">${product.productName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label class="form-label" for="productquantity">数量</label>
                <input type="number" id="productquantity" name="productquantity" min="1" max="1000000000" required
                       class="form-control">
            </div>
            <button type="submit" class="btn btn-outline-primary">Add</button>
        </form>

        <h3><c:out value="${errors} "/></h3>

        <table class="table table-bordered">
            <thead class="thead-dark">
            <tr>
                <th scope="col" class="col-6">商品名</th>
                <th scope="col" class="col-6">数量</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${sales}" var="sale">
                <tr>
                    <td>${sale.productName}</td>
                    <td>${sale.quantity}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <form method="post" action="addsales">
            <input type="hidden" id="savefield" name="savefield">
            <input type="submit" name="save" value="Registration" onclick="saveValueSet()" class="btn btn-outline-primary">
        </form>

        <table class="table table-bordered">
            <thead class="thead-dark">
            <tr>
                <th scope="col" class="col-6">商品コード</th>
                <th scope="col" class="col-6">商品名</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${todayssales}" var="todayssale">
                <tr>
                    <td>${todayssale.productName}</td>
                    <td>${todayssale.quantity}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script type="text/javascript">
    function saveValueSet() {
        var elem = document.getElementById("savefield");
        elem.value = "Save";
    }
</script>

</body>
</html>
