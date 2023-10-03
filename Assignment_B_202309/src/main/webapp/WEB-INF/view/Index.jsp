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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        /* メニュー」ボックスの背景を薄緑色にするための追加CSS */
        body {
            background-color: #f4f9fd;
        }
        .menu-box {
            background-color: lightgreen;
            padding: 20px;
            text-align: center;
        }
        .list-group {
            background-color: white;
            border-radius: 10px;
            margin: 20px auto;
            max-width: 400px;
            padding: 20px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
        }
        .list-group-item {
            background-color: transparent;
        }
        /* 商品検索」と「商品登録」部分の背景に#f4f9fd色を適用する。 */
        .highlight-background {
            background-color: #f4f9fd;
            border-radius: 10px;
            padding: 15px;
        }
    </style>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="justify-content-center align-items-center">
    <div class="row justify-content-center align-items-center">
        <div class="list-group w-auto">
            <div class="col-md-12 menu-box"> <!-- menu-box "クラスの追加 -->
                <h1 class="py-4 text-center">メニュー</h1>
            </div>
            <a href="/Assignment_B_202309/Product"
               class="list-group-item list-group-item-action d-flex gap-3 py-3">
                <div class="d-flex gap-2 w-100 justify-content-between highlight-background">
                    <div>
                        <h6 class="mb-0"> <i class="fas fa-search"></i> 商品検索</h6>
                        <p class="mb-0 opacity-75">ここに製品のリストが表示されます.</p>
                    </div>
                </div>
            </a>
            <a href="/Assignment_B_202309/AddProduct"
               class="list-group-item list-group-item-action d-flex gap-3 py-3">
                <div class="d-flex gap-2 w-100 justify-content-between highlight-background">
                    <div>
                        <h6 class="mb-0"> <i class="fas fa-pencil-alt"></i> 商品登録 </h6>
                        <p class="mb-0 opacity-75">ここで新しい製品を追加できます.</p>
                    </div>
                </div>
            </a>
            <a href="/Assignment_B_202309/Sales"
               class="list-group-item list-group-item-action d-flex gap-3 py-3">
                <div class="d-flex gap-2 w-100 justify-content-between highlight-background">
                    <div>
                        <h6 class="mb-0"><i class="fas fa-file"></i> 売上登録</h6>
                        <p class="mb-0 opacity-75">今日の売上を登録できます。</p>
                    </div>
                </div>
            </a>
            <a href="/Assignment_B_202309/SalesReport"
               class="list-group-item list-group-item-action d-flex gap-3 py-3">
                <div class="d-flex gap-2 w-100 justify-content-between highlight-background">
                    <div>
                        <h6 class="mb-0"> <i class="fas fa-download"></i> CSVダウンロード</h6>
                        <p class="mb-0 opacity-75">  CSVはダウンロードできます。</p>
                    </div>
                </div>
            </a>
        </div>
    </div>
</div>
</body>
</html>
