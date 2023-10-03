<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*,java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <style>
        .btn-olive {
            background-color: #A7BC8A;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 10px;
        }
        /* ディープオリーブのボタンスタイルを変更 */
        .btn-deep-olive {
            background-color: #587E52;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 10px;
        }
        .btn-deep-olive:hover {
            background-color: #A7BC8A;
        }
        .card-container {
            background-color: #f4f9fd;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .card {
            background-color: white;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
            padding: 20px;
            max-width: 1000px;
            width: 100%; 
        }
        .table thead th {
            background-color: #dbe0e3; /* テーブルのヘッダー行の背景色を変更する */
        }
              
		.table tbody tr:hover {
 		   background-color: #A7BC8A; /* ホバー時の緑色 */
  		  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
  		  transition: background-color 0.3s, box-shadow 0.3s;
		}

    </style>
</head>
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
<body>
    <div class="card-container">
        <div class="card">
            <div class="row justify-content-center align-items-center">
                <div class="col-md-8">
                    <h1 class="py-4">
                        <span style="background-color: lightgreen; padding: 10px; border-radius: 10px;">商品検索</span>
                    </h1>
                </div>
            </div>
            <div class="row justify-content-center align-items-center">
                <div class="col-md-8">
                    <form action="Product" method="GET">
                        <div class="input-group mb-3">
                            <h3 style="background-color: #BFEE90; padding: 5px; border-radius: 10px;" class="col-md-2">商品名</h3>
                            <input type="text" name="search-params"
                                class="form-control mx-4 col-md-4" value="" maxlength="50">
                                
                                 <!-- required> -->
                            <!-- Change button class to btn-deep-olive -->
                            <div class="input-group-append col-md-4">
                                <button class="btn-deep-olive" type="submit">検索</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-md-8">
                    <c:if test="${!empty error}">
                        <div class="alert alert-danger" role="alert">
                            <c:out value="${error}" />
                        </div>
                    </c:if>
                    <c:if test="${empty error}">
                        <c:choose>
                            <c:when test="${empty product_list}">
                            </c:when>
                            <c:otherwise>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col">商品コード</th>
                                            <th scope="col">商品名</th>
                                            <th scope="col">単価</th>
                                            <th scope="col">操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="product" items="${product_list}">
                                            <tr>
                                                <th><fmt:formatNumber
                                                    value="${product.getProductCode()}" pattern="000" /></th>
                                                <td><c:out value="${product.getProductName()}" /></td>
                                               <td><fmt:formatNumber value="${product.getPrice()}" pattern="¥ #,###" /></td>
                                                <td><a
                                                    href="<c:url value="/EditProduct?id=" />${product.getProductCode()}"
                                                    class="btn btn-success" name="editBtn">編集</a></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    <a href="<c:url value="/" />" class="btn btn-secondary ">メニューに戻る</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
