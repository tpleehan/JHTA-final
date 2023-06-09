<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<title>erp</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<style type="text/css">
		.notice {
			table-layout:fixed;
			font-size: 13px;
		}
	
		.notice td {
			overflow: hidden;
			text-overflow: ellipsis;
			white-space: nowrap;
			cursor: pointer;
		}
	</style>
</head>
<body>
<%@ include file="common/nav.jsp" %>
<div class="container-fluid" style="position:absolute; top: 80px; margin-left: 80px;">
	<div class="row">
		<div class="col-sm-6">
			<img src="resources/images/logo/main.jpg" class="img-rounded" style="width: 100%">
		</div>
		<div class="col-sm-5" style="margin-left:40px;">
			<div class="row">
				<div class="col-md-11" style="font-weight: bold">
					<div class="panel panel-default">
						<div class="panel-heading"> ERP 프로그램 사용자
							<c:if test="${empty attendance }">
								<button id="intime-btn" type="button">출근</button>								
							</c:if>
							<c:if test="${attendance.status eq 'Y' }">					
								<button id="outtime-btn" type="button">퇴근</button>								
							</c:if>
				
							<form id="attendanceForm">
								<input id="employee-no" type="hidden" name="empNo" value="${LE_detail.no }">
								<input id="times" type="hidden" name="times" />
								<input id="employee-name" type="hidden" name="empName" value="${LE_detail.name }">
							</form>
						</div>
						<div class="media">
							<div class="media-left">
								<img class="media-object" style="width:110px; height:160px; margin: 0 10px 15px 15px;" src="resources/images/employee/${LE.imageName }">
							</div>
							<div class="media-body">
								<p class="media-heading">[${LE_detail.no }] ${LE_detail.departmentName }-${LE_detail.name }</p>
								<p>${LE_detail.gradeName }</p>
								<p>${LE_detail.email }</p>
								<p>${LE_detail.tel }</p>
								<div class="btn-group">
									<a href="my/sendmessages.erp"><button type="button">보낸메세지</button></a>
									<a href="my/getmessage.erp"><button type="button">받은메세지</button></a>								
									<a href="my/searchemployee.erp"><button type="button">직원검색</button></a>
								</div>
							</div>
						</div>
					</div>
					<div class="panel panel-default" style="margin-top: -5px;">
						<div class="panel-heading">공지사항 게시판</div>
						<div class="panel-body">
							<table class="table table-hover notice">
								<colgroup>
									<col width="45%">
									<col width="35%">
									<col width="20%">
								</colgroup>
								<thead>
									<tr>
										<th>제목</th>
										<th>부서</th>
										<th>조회수</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="notice" items="${notices }" varStatus="loop">
										<tr onclick="showNotice(${notice.no }, ${notice.departmentNo })">
											<td>${notice.title }</td>
											<td>${notice.departmentName }</td>
											<td>${notice.viewCount }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	function showNotice(noticeNo, deptNo) {
		var url = "notice/detail.erp?deptno="+deptNo+"&noticeno="+noticeNo;
		window.location = url;
	}
	
	$("#intime-btn").click(function(){
		var intime = new Date();
		$("#times").val(intime.getTime());
		
		$("#attendanceForm").attr('action', 'intimeUser.erp');
		$("#attendanceForm").submit();
	})
	
	$("#outtime-btn").click(function(){
		var outtime = new Date();
		$("#times").val(outtime.getTime());
		$("#attendanceForm").attr('action', 'outtimeUser.erp');
		$("#attendanceForm").submit();
	})
	
</script>
</body>
</html>