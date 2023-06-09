<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<title></title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
	<style type="text/css">
		th {
			background-color: #f2f2f2;
			text-align: center;
		}
	</style>
</head>
<body>
<%@ include file="../common/nav.jsp" %>
<%@ include file="stocksidebar.jsp" %>
<div class="container-fluid col-sm-9" style="margin-left:250px; padding: 50px 0px;">
	<div class="page-header">
		<h1>창고별 재고 조회</h1>
	</div>
	
	<div class="row">
		<div class="col-sm-12">
			<select id="select-stores" class="form-control">
				<option disabled selected> --창고를 선택하세요 -- </option>
				<c:forEach var="store" items="${stores }">
					<option value="${store.storeNo }" > ${store.storeName } </option>
				</c:forEach>
			</select>
		</div>
	</div>
	<hr/>
	
	<div class="row" id="div-store-detail">
		<div class="col-sm-12">
			<div id="div-store-table">
				<table class="table table-bordered">
					<tbody>
						<tr>
							<th class="col-sm-2">지점 명</th>
							<td class="col-sm-9">{{store.name}}</td>
						</tr>
						<tr>
							<th>주소</th>
							<td>{{store.address}}</td>
						</tr>
					</tbody>
				</table>
			</div><hr/>
			<div id="div-stock">
				<table class="table table-bordered">
						<colgroup>
							<col width="5%">
							<col width="35%">
							<col width="15%">
							<col width="20%">
							<col width="15%">
							<col width="10%">
						</colgroup>
					<thead>
						<tr>
							<th></th>
							<th>제품명</th>
							<th>제품가격</th>
							<th>카테고리명</th>
							<th>출시일</th>
							<th>수량</th>
						</tr>
					</thead>
					<tbody class="text-right">
						<tr v-for="(stock, index) in stocks">
							<td>{{index +1}}</td>
							<td>{{stock.productName}}</td>
							<td>{{stock.productPrice | currency}} 원</td>
							<td>{{stock.categoryName}}</td>
							<td>{{stock.fmtProductCreateDate}}</td>
							<td>{{stock.amount | currency }} 개</td>
						</tr>
						<tr v-if="stocks.length == 0">
							<td colspan="6" class="text-center">재고가 존재하지 않습니다.</td>
						</tr>
					</tbody>
				</table>
			</div>
			
		</div>
	</div>
</div>
<script type="text/javascript">
//지점 선택시 보여줄 지점, 매니저 정보를 담을 뷰를 설정
	var storeApp = new Vue({
		el:"#div-store-detail",
		data: {
			store:{},
			stocks:[]
		},
		filters: {
			currency: function(value) {
				if(isNaN(value)) {
					return value;
				}
				return new Number(value).toLocaleString();
			}
		}
	})
	
	// 지점 선택시 ajax로 지점 정보, 매니저 정보를 불러온다.
	$("#select-stores").change(function() {
		var no = this.value;
		
		$("[name=storeEmp]").val(store.employeeName);
		
		$.getJSON("/storemanagement/storedetail.erp", {no:no}, function(result) {
			storeApp.store = result;
		}) 
		
		$.getJSON("/stock/storeStocks.erp", {storeNo:no})
			.done(function(result) {
				storeApp.stocks = result;
			})
		
	})
</script>
</body>
</html>