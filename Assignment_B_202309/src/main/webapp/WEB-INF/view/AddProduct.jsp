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
        .btn-teal {
            background-color: #2F4F4F;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 10px;
        }
        /* ディープオリーブのボタンスタイルを変更 */
        .btn-deep-teal {
            background-color: #008080;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 10px;
        }
        /* ホバー時に明るいオリーブ色になるようにボタンのスタイルを変更する */
        .btn-deep-teal:hover {
            background-color: #2F4F4F;
        }
        /* テーブルのヘッダー行の背景色を変更する */
        .table thead th {
            background-color: #dbe0e3; 
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
                     <span style="background-color: lightgreen; padding: 10px; border-radius: 10px;">商品登録 </span>
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
                    <form action="AddProduct" method="POST">
                        <div class="form-group row py-2">
                            <h3 for="product-name" class="control-label col-sm-2">商品名</h3>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" id="product-name"
                                    name="name" value="" maxlength="50" required>
                            </div>
                        </div>

                        <div class="form-group row py-2">
                            <h3 for="product-price" class="control-label col-sm-2">単価</h3>
                            <div class="col-sm-6">
                                <input type="number" class="form-control" id="product-price"
                                    name="price" value="" min="0" required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="offset-sm-2 col-sm-10 pull-right">
                                <!-- Change button class to btn-deep-olive -->
                                <button type="submit" class="btn-deep-teal">登録</button>
                            </div>
                        </div>
                    </form>
                    <a href="<c:url value="/" />" class="btn btn-secondary ">メニューに戻る</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
