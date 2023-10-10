<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add a Product</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body style="background-color: #d3d3d3;"> 
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-12 col-md-6">
                <h1 class="text-center">Product Registration</h1>
                <c:if test="${not empty errors}">
                    <div class="alert alert-danger">
                        <c:out value="${errors}" />
                    </div>
                </c:if>
                <form method="post" action="addproduct">
                    <div class="mb-3">
                        <label for="productname" class="form-label">商品名</label>
                        <input type="text" id="productname" name="productname" class="form-control" maxlength="50" required />
                    </div>
                    <div class="mb-3">
                        <label for="productprice" class="form-label">単価</label>
                        <input type="number" id="productprice" name="productprice" class="form-control" min="1" max="1000000000" required />
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary">Registration</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="row justify-content-center mt-3">
            <div class="col-12 col-md-6 text-center">
                <button class="btn btn-primary btn-lg" id="submit" onclick="location.href ='search'">Search Products</button>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>
