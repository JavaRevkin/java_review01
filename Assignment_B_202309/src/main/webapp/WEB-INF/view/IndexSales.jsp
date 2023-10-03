<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.*,java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
    <style>
        /* カードのようなレイアウトのためのカスタム */
        .card-container {
            background-color: #f4f9fd;
            display: flex;
            justify-content: center;
            align-items: center;
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
            padding: 10px 20px;
            border: none;
            border-radius: 10px;
        }
        /* ディープオリーブのボタンスタイルを変更 */
        .btn-deep-olive {
            background-color: #696969;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 10px;
        }
        /* ホバー時に明るいオリーブ色になるようにボタンのスタイルを変更する */
        .btn-olive:hover {
            background-color: #A7BC8A;
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
        .table thead th {
            background-color: #dbe0e3; /* テーブルのヘッダー行の背景色を変更する */
        }
    </style>
    
	<script>
	//editButton.disabled = true;
	function checkSalesData() {
	    // Check if there are rows in the table (data exists)
	    var salesTable = document.getElementById("product-table-up");
	    var editButton = document.getElementById("edit-button");
	    var errorMessage = document.getElementById("error-message");

	    if (salesTable.rows.length > 0) {
	        var successMessage = document.getElementById("success-message");
	        if (successMessage) {
	            successMessage.style.display = "block";
	            editButton.disabled = false;
	        }
	    }
        else {
        	if (errorMessage) {
 	           errorMessage.style.display = "block";
 	           hideSuccessMessage();
        	}
        	editButton.disabled = true;
        }
	}
	
	function hideSuccessMessage() {
	       var successMessage = document.getElementById("success-message");
	       if (successMessage) {
	           successMessage.style.display = "none";
	       }
	   }
	
	
	  // エラーメッセージを表示し、成功メッセージを非表示にする機能
	  function showErrorMessage() {
	       var errorMessage = document.getElementById("error-message");
	       if (errorMessage) {
	           errorMessage.style.display = "block";
	           hideSuccessMessage(); // Hide the success message
	           document.getElementById("edit-button").disabled = true;
	       }
	   } 
	  
	  function validateForm() {	  
    	  
          var productName = document.getElementById("product-name").value;
          var saleAmount = document.getElementById("sale-amount").value;
          
          if (productName == "-1" && saleAmount === "") {
          	  showErrorMessage();
              return false; 
          }
          
          else if (productName == "-1" && saleAmount !== "") {
          	  showErrorMessage(); 
              return false; 
          }     
          
          return true; 
      }

      // Disable the "編集" button initially
      //document.getElementById("edit-button").disabled = true;

      editButton.disabled = false;
      function enableEditButton() {
    	  
          var salesTable = document.getElementById("product-table-up");
          var editButton = document.getElementById("edit-button");

          // Check if there are rows in the table (data exists)
          if (salesTable.rows.length > 0) { //
              editButton.disabled = false;
          }
         
          else {
              editButton.disabled = true;
          }
      }
      
      enableEditButton(); 
      
  	  </script>
    
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
                    <span style="background-color: lightgreen; padding: 10px; border-radius: 10px;"> 売上登録 </span>
                    </h1>
                </div>
            </div>
            <div class="row justify-content-center align-items-center">
                <div class="col-md-8">
                   <form action="Sales" method="get" onsubmit="return validateForm();">
                        <div class="input-group mb-3">
                            <h3 class="col-md-2">売上日</h3>
                            <div class="mx-4 col-md-4">
                                <jsp:useBean id="now" class="java.util.Date" />
                                <fmt:formatDate var="date" value="${now}" pattern="yyyy/MM/dd" />
                                <h4>
                                    <c:out value="${date}" />
                                </h4>
                            </div>
                        </div>
                        <div class="input-group mb-3">
                            <h3 class="col-md-2">商品名</h3>
                            <select class="form-select mx-4 col-md-3" id="product-name" name="product-id" required>
                                <option value="-1" selected>選ぶ...</option>
                                <c:forEach var="product" items="${product_list}">
                                    <option value="${product.getProductCode()}">${product.getProductName()}</option>
                                </c:forEach>
                            </select>
						<h3 class="col-md-2 text-center">数量</h3>
						<input type="number" name="sale-amount" id="sale-amount"
							class="form-control mr-4 col-md-3 " min="1" required>
						<div class="input-group-append col-md-2 mx-2">
							<button id="add-to-tableq" class="btn-cornflowerblue" type="submit">追加</button>
						</div>

					</div>

				</form>
			</div>
					
			
			<div class="col-md-8">
						<c:if test="${!empty successForSales}">
				    	
							<div id = "success-message" class="alert alert-success" role="alert">
								<c:out value="${successForSales}" />
							</div>
							
						</c:if>
						
				<div id="error-message" class="alert alert-danger" role="alert" style="display: none;">

        			エラー：商品名を選択してください。
        			
   			    </div>
   			    
				<form action="Sales" method="POST">
					<table class=" table sales-table" id="product-table">
						<thead>
							<tr>
								<th scope="col">商品名</th>
								<th scope="col">数量</th>
							</tr>
						</thead>
						<tbody class="sales-table-body" id = "product-table-up">
							<c:choose>
								<c:when test="${empty salesList}">
								</c:when>
								<c:otherwise>
									<c:forEach var="sales" items="${salesList}">
										<tr>
											<td><c:out value="${sales.value[0]}" /></td>
											<td><c:out value="${sales.value[1]}" /></td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
					<div class="form-group ">
						<div class="col-m-12 float-end ">
						
							<button type="submit" class="btn-olive" id="edit-button" onclick="checkSalesData()">登録</button>
							
						</div>
					</div>
				</form>
				<c:set var="error" value="" scope="session" />
				<c:set var="success" value="" scope="session" />
				<a href="<c:url value="/" />" class="btn btn-secondary ">メニューに戻る</a>

			</div>
			
			
			<div class="col-md-8">
				<c:choose>
					<c:when test="${empty todays_sales}">
					</c:when>
					<c:otherwise>
						<h3 class="mt-4">今日の売上</h3>
						<table class=" table sales-table" id="product-table">
							<thead>
								<tr>
									<th scope="col">商品名</th>
									<th scope="col">数量</th>
								</tr>
							</thead>
							<tbody class="sales-table-body">

								<c:forEach var="todays_sales" items="${todays_sales}">
									<tr>
										<td><c:out value="${todays_sales.getProductName()}" /></td>


										<td><c:out value="${todays_sales.getQuantity()}" /></td>
									</tr>
								</c:forEach>

							</tbody>
						</table>

					</c:otherwise>
				</c:choose>
			</div>

		</div>
	</div>



</body>
</html>