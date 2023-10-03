<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.*,java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <style>
        /* カードのようなレイアウトのためのカスタムスタイル */
        .card-container {
            background-color: #f4f9fd;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 70vh;
        }
        .card {
            background-color: white;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
            padding: 20px;
            max-width: 1000px;
            width: 100%;
        }
        
        .btn-olive {
            background-color: #587E52;
            color: white;
            padding: 9px 20px;
            border: none;
            border-radius: 10px;
        }
        
        .btn-olive:hover {
            background-color: #A7BC8A;
        }
		
		 .btn-red {
            background-color: #D81B1B;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 10px;
        }
        
        .btn-red:hover {
            background-color: #9F3C3C;
        }

    </style>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Web課題</title>
    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
        crossorigin="anonymous">
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
        crossorigin="anonymous"></script>
</head>
<body style="background-color: #f4f9fd;">
    <div class="card-container">
        <div class="card">
            <div class="row justify-content-center align-items-center">
                <div class="col-md-8">
                    <h1 class="py-4">
                        商品変更・削除
                    </h1>
                </div>
            </div>
            <div class="row justify-content-center align-items-center">
                <div class="col-md-8">
                    <c:if test="${!empty error}">
                        <div class="alert alert-danger" role="alert">
                            <c:out value="${error}" />
                        </div>
                    </c:if>
                    <form action="EditProduct" method="POST">
                        <div class="form-group row py-2">
                            <h3 for="product-name" class="control-label col-sm-3">商品コード</h3>
                            <input type="hidden" class="form-control" id="current-update-time"
                                    name="current-update-time" value="${product.getUpdateDatetime()}">
                            <div class="col-sm-6">
                                <input type="hidden" class="form-control" id="product-id"
                                    name="id" value="${product.getProductCode()}">
                                <fmt:formatNumber value="${product.getProductCode()}"
                                    pattern="000" />
                            </div>
                        </div>
                        <div class="form-group row py-2">
                            <h3 for="product-name" class="control-label col-sm-2">商品名</h3>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" id="product-name"
                                    name="name" value="${product.getProductName()}" maxlength="50"
                                    required>
                            </div>
                        </div>
                        <div class="form-group row py-2">
                            <h3 for="product-price" class="control-label col-sm-2">単価</h3>
                            <div class="col-sm-6">
                                <c:choose>
                                    <c:when test="${isSold}">
                                        <p>
                                            <c:out value="${product.getPrice()}" />
                                            <input type="hidden" class="form-control" id="product-price"
                                                name="price" value="${product.getPrice()}">
                                        </p>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="number" class="form-control" id="product-price"
                                            name="price" value="${product.getPrice()}" min="1" required>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <div class="form-group  ">
                            <div class="offset-sm-2 col-sm-10 pull-right">
                                <button type="submit" class="btn-olive">変更</button>
                                <button type="button" onclick="deleteFormSubmit()"
                                        class="btn-red">削除</button>
                            </div>
                        </div>
                    </form>
                    <a href="<c:url value="/Product" />" class="btn btn-secondary">商品リストに戻る</a>
                </div>
            </div>
        </div>
    </div>

    <form id="delete-from" action="ProductDelete" method="POST">
        <input type="hidden" class="form-control" id="product-id" name="id"
            value="${product.getProductCode()}">
    </form>
    <script>
        function deleteFormSubmit() {
            document.getElementById("delete-from").submit();
        }
    </script>
</body>
</html>
