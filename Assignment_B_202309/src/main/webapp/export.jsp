<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Export CSV</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    <style>
        body {
            background-color: #d3d3d3;
        }

        .container {
            margin-top: 10%;
            max-width: 40%;
        }

        .card {
            margin: 0 auto;
        }

        .card-header {
            background-color: #007bff;
            color: #fff;
            text-align: center;
        }

        .card-body {
            padding-top: 0;
        }

        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
            color: #fff;
        }

        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }

        .btn-secondary {
            margin-left: 10px;
        }

        .search-button {
            position: absolute;
            top: 10px;
            right: 10px;
            background-color: #007bff;
            border-color: #007bff;
            color: #fff;
        }

        .search-button:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }

        .button-container {
            display: flex;
            flex-direction: column;
            align-items: flex-end;
        }

        .form-group {
            margin-top: 10px;
        }

        #submit {
            background-color: #fff; 
            border-color: #007bff; 
            color: #007bff; 
        }

        #submit:hover {
            background-color: #007bff; 
            border-color: #0056b3;
            color: #fff; 
        }

    </style>
</head>
<body>
<button class="btn btn-secondary search-button" onclick="location.href ='search'">製品検索</button>
<div class="container">
    <div class="card">
        <div class="card-header">
            <h1 class="display-4">CSVダウンロード</h1>
        </div>
        <div class="card-body">
            <h6><c:out value="${errors} "/></h6>

            <!-- Wrap the buttons and select in a flex container -->
            <div class="button-container">
            <div class="form-group">
                    <button class="btn btn-lg btn-block btn-primary" id="submit" onclick="location.href ='exportall'" >商品別売上集計CSV</button>
                </div>
                <div class="form-group">
                    <form method="post" action="export">
                        <label for="month">年月 &nbsp;&nbsp;  </label>
                        <input type="month" id="month" name="month" required/> &nbsp;&nbsp;
                        <button type="submit" class="btn btn-outline-primary">指定年月商品別売上集計CSV</button>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
</body>
</html>
