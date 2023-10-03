<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.*,java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
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
        
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
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
            max-width: 1300px;
            width: 100%;
        }
        
        .btn-coral {
            background-color: #ff7f50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 10px;
        }
        
        .btn-coral:hover {
            background-color: #d2691e;
        }
        
        .btn-cornflowerblue {
            background-color: #008b8b;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 10px;
        }
        
        .btn-cornflowerblue:hover {
            background-color: #6495ed;
        }
        
    </style>
    
</head>
<body style="background-color: #f4f9fd;">
    <div class="card-container">
        <div class="card">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-8">
                        <h1>
                        	<span style="background-color: lightgreen; padding: 10px; border-radius: 10px;class="py-4"> CSVダウンロード </span> 
                        </h1>
                        <c:if test="${!empty error}">
                            <div class="alert alert-danger" role="alert">
                                <c:out value="${error}" />
                            </div>
                        </c:if>
                        <form action="SalesReport" method="POST">
                            <div class="form-group row py-2">
                                <div class="col-2"></div>
                                <div class="col-4"></div>
                                <div class="col-4">
                                    <input type="hidden" name="type" value="all-by-products">
                                    <button type="submit" class="btn-coral"><i class="fas fa-download"></i> 商品別売上集計CSV</button>
                                </div>
                            </div>
                        </form>
                        <form action="SalesReport" method="POST">
                            <div class="form-group row py-2">
                                <h3 for="product-name" class="control-label col-2">年月</h3>
                                <div class="col-4">
                                    <input type="month" class="form-control" id="sales-date"
                                        name="sales-date" value="" min="2022-01" required>
                                </div>
                                <div class="col-5">
                                    <input type="hidden" name="type" value="by-date">
                                    <button type="submit" class="btn-cornflowerblue"> <i class="fas fa-download"></i>  指定年月商品別売上集CSV</button>
                                </div>
                            </div>
                        </form>
                        <a href="<c:url value="/" />" class="btn btn-secondary my-4"> <i class="fas fa-house"></i> メニューに戻る</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
